package com.example.appprogm_lhj;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;
import com.example.appprogm_lhj.databinding.ActivityAddDiaryBinding;
import android.content.pm.PackageManager;

public class AddDiaryActivity extends AppCompatActivity {
    private ActivityAddDiaryBinding binding;
    private AppDatabase db;
    private Uri photoUri;
    private static final int STORAGE_PERMISSION_CODE = 100;

    private final ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    photoUri = result.getData().getData();
                    if (photoUri != null) {
                        binding.ivPhoto.setImageURI(photoUri);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddDiaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "ingredient-database")
                .allowMainThreadQueries()
                .addMigrations(AppDatabase.MIGRATION_1_2)
                .build();

        binding.btnSelectPhoto.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        STORAGE_PERMISSION_CODE);
            } else {
                openGallery();
            }
        });

        binding.btnSave.setOnClickListener(v -> {
            String comment = binding.etComment.getText().toString().trim();
            if (photoUri == null || comment.isEmpty()) {
                Toast.makeText(this, "사진과 코멘트를 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            Diary diary = new Diary();
            diary.photoUri = photoUri.toString();
            diary.comment = comment;
            db.diaryDao().insert(diary);
            Toast.makeText(this, "요리일기가 저장되었습니다.", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickImageLauncher.launch(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Toast.makeText(this, "갤러리 접근 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
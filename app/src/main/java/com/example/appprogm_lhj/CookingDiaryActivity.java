package com.example.appprogm_lhj;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.example.appprogm_lhj.databinding.ActivityCookingDiaryBinding;

public class CookingDiaryActivity extends AppCompatActivity {
    private ActivityCookingDiaryBinding binding;
    private AppDatabase db;
    private List<Diary> diaries;
    private DiaryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCookingDiaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "ingredient-database")
                .allowMainThreadQueries()
                .addMigrations(AppDatabase.MIGRATION_1_2)
                .build();

        RecyclerView recyclerView = binding.diaryRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        diaries = db.diaryDao().getAllDiaries();
        adapter = new DiaryAdapter(diaries);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemLongClickListener((diary, position) -> {
            new AlertDialog.Builder(this)
                    .setTitle("삭제 확인")
                    .setMessage("이 요리일기를 삭제하시겠습니까?")
                    .setPositiveButton("삭제", (dialog, which) -> {
                        db.diaryDao().delete(diary);
                        diaries.remove(position);
                        adapter.notifyItemRemoved(position);
                    })
                    .setNegativeButton("취소", null)
                    .show();
        });

        binding.btnAddDiary.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddDiaryActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        diaries.clear();
        diaries.addAll(db.diaryDao().getAllDiaries());
        adapter.notifyDataSetChanged();
    }
}
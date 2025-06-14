package com.example.appprogm_lhj;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder> {
    private List<Diary> diaries;
    private OnItemLongClickListener longClickListener;

    public DiaryAdapter(List<Diary> diaries) {
        this.diaries = diaries;
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(Diary diary, int position);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.longClickListener = listener;
    }

    @NonNull
    @Override
    public DiaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diary, parent, false);
        return new DiaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryViewHolder holder, int position) {
        Diary diary = diaries.get(position);
        if (diary.photoUri != null) {
            holder.ivPhoto.setImageURI(Uri.parse(diary.photoUri));
        }
        holder.tvComment.setText(diary.comment);

        holder.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                longClickListener.onItemLongClick(diary, position);
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return diaries.size();
    }

    static class DiaryViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        TextView tvComment;

        DiaryViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.ivDiaryPhoto);
            tvComment = itemView.findViewById(R.id.tvDiaryComment);
        }
    }
}
package com.example.appprogm_lhj;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    private List<Ingredient> ingredientList;
    private OnItemLongClickListener longClickListener;
    private OnItemClickListener clickListener;

    // 롱클릭 인터페이스 (삭제)
    public interface OnItemLongClickListener {
        void onItemLongClick(Ingredient ingredient, int position);
    }

    // 클릭 인터페이스 (수정)
    public interface OnItemClickListener {
        void onItemClick(Ingredient ingredient, int position);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.longClickListener = listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    public IngredientAdapter(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, quantityText, expiryText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.nameText);
            quantityText = itemView.findViewById(R.id.quantityText);
            expiryText = itemView.findViewById(R.id.expiryText);

            itemView.setOnLongClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && longClickListener != null) {
                    longClickListener.onItemLongClick(ingredientList.get(pos), pos);
                    return true;
                }
                return false;
            });

            itemView.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && clickListener != null) {
                    clickListener.onItemClick(ingredientList.get(pos), pos);
                }
            });
        }
    }

    @NonNull
    @Override
    public IngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.ViewHolder holder, int position) {
        Ingredient ingredient = ingredientList.get(position);
        holder.nameText.setText(ingredient.name);
        holder.quantityText.setText("수량: " + ingredient.quantity);
        holder.expiryText.setText("유통기한: " + ingredient.expiryDate);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }
}

package com.example.cropmate.fieldManagement;
import com.example.cropmate.R;

// FieldViewHolder.java
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class FieldViewHolder extends RecyclerView.ViewHolder {
    public TextView fieldNameTextView;
    public TextView harvestAmountTextView;

    public FieldViewHolder(View itemView) {
        super(itemView);
        fieldNameTextView = itemView.findViewById(R.id.fieldNameTextView);
        harvestAmountTextView = itemView.findViewById(R.id.harvestAmountTextView);
    }
}

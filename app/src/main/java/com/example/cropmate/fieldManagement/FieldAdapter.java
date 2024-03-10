package com.example.cropmate.fieldManagement;
import com.example.cropmate.R;


// FieldAdapter.java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FieldAdapter extends RecyclerView.Adapter<FieldViewHolder> {
    private List<Field> fieldList;
    private Context context;

    public FieldAdapter(Context context, List<Field> fieldList) {
        this.context = context;
        this.fieldList = fieldList;
    }

    @NonNull
    @Override
    public FieldViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FieldViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    // ... (rest of the adapter code)
}

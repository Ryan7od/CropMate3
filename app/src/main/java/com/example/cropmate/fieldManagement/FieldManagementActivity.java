package com.example.cropmate.fieldManagement;
import com.example.cropmate.R;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class FieldManagementActivity extends AppCompatActivity {

    private List<Field> fieldList;
    private LinearLayout fieldsLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_management);

        fieldsLinearLayout = findViewById(R.id.fieldsLinearLayout); // Assume you have a LinearLayout with this ID

        fieldList = new ArrayList<>();

        Button addFieldButton = findViewById(R.id.addFieldButton);
        addFieldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddFieldDialog();
            }
        });

        Button removeFieldButton = findViewById(R.id.removeFieldButton);
        removeFieldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove the last field entry (implement your logic here)
                if (!fieldList.isEmpty()) {
                    fieldList.remove(fieldList.size() - 1);
                    updateFieldViews();
                }
            }
        });

        updateFieldViews(); // Update views initially
    }

    private void showAddFieldDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialogue_add_field, null);
        builder.setView(dialogView);

        EditText editFieldID = dialogView.findViewById(R.id.editFieldID);
        EditText editName = dialogView.findViewById(R.id.editName);
        EditText editArea = dialogView.findViewById(R.id.editArea);
        EditText editCrop = dialogView.findViewById(R.id.editCrop);
        EditText editPlanted = dialogView.findViewById(R.id.editPlanted);
        EditText editSoilHealth = dialogView.findViewById(R.id.editSoilHealth);
        EditText editLocation = dialogView.findViewById(R.id.editLocation);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String fieldID = editFieldID.getText().toString();
            String name = editName.getText().toString();
            Double area = Double.parseDouble(editArea.getText().toString());
            String crop = editCrop.getText().toString();
            Editable planted = editPlanted.getText();
            String soilHealth = editSoilHealth.getText().toString();
            String location = editLocation.getText().toString();

            fieldList.add(new Field(fieldID, name, area, crop, planted, soilHealth, location));
            updateFieldViews();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
            // Handle cancellation
        });

        builder.create().show();
    }

    private void updateFieldViews() {
        fieldsLinearLayout.removeAllViews(); // Clear existing views

        for (Field field : fieldList) {
            View fieldView = LayoutInflater.from(this).inflate(R.layout.item_field, fieldsLinearLayout, false);

            TextView fieldNameTextView = fieldView.findViewById(R.id.fieldNameTextView);
            // Set other field data...
            fieldNameTextView.setText(field.getName());

            fieldsLinearLayout.addView(fieldView);
        }
    }
}

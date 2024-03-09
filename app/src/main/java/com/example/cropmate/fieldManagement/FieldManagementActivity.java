package com.example.cropmate.fieldManagement;
import com.example.cropmate.R;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FieldManagementActivity extends AppCompatActivity {

    private List<Field> fieldList;
    private FieldAdapter fieldAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_management);

        // Initialize RecyclerView and layout manager
        RecyclerView fieldsRecyclerView = findViewById(R.id.fieldsRecyclerView);
        fieldsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize data and adapter
        fieldList = new ArrayList<>();
        fieldAdapter = new FieldAdapter(this, fieldList);
        fieldsRecyclerView.setAdapter(fieldAdapter);

        // Add sample data
       // fieldList.add(new Field("Field 1", 100));

        // Notify the adapter that the data set has changed
        fieldAdapter.notifyDataSetChanged();

        // Add Field Button Click Listener
        Button addFieldButton = findViewById(R.id.addFieldButton);
        addFieldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddFieldDialog();
            }
        });

        // Remove Field Button Click Listener
        Button removeFieldButton = findViewById(R.id.removeFieldButton);
        removeFieldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove the last field entry (you can implement your own logic here)
                if (!fieldList.isEmpty()) {
                    fieldList.remove(fieldList.size() - 1);
                    // Notify the adapter that the data set has changed
                    fieldAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void showAddFieldDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialogue_add_field, null);
        builder.setView(dialogView);

        // Get references to EditText fields in the dialog
        EditText editFieldID = dialogView.findViewById(R.id.editFieldID);
        // Add references for other EditText fields
        EditText editName = dialogView.findViewById(R.id.editName);
        EditText editArea = dialogView.findViewById(R.id.editArea);
        EditText editCrop = dialogView.findViewById(R.id.editCrop);
        EditText editPlanted = dialogView.findViewById(R.id.editPlanted);
        EditText editSoilHealth = dialogView.findViewById(R.id.editSoilHealth);
        EditText editLocation = dialogView.findViewById(R.id.editLocation);
        // Add references for other EditText fields

        // Set up the "Add" button in the dialog
        builder.setPositiveButton("Add", (dialog, which) -> {
            // Retrieve values from EditText fields
            String fieldID = editFieldID.getText().toString();
            String name = editName.getText().toString();
            // Retrieve values for other parameters
            Double area = Double.parseDouble(editArea.getText().toString());

            String crop = editCrop.getText().toString();

            Editable planted = editPlanted.getText();

            String soilHealth = editSoilHealth.getText().toString();

            String location = editLocation.getText().toString();

            // Add a new field entry
            fieldList.add(new Field(fieldID, name, area, crop, planted, soilHealth, location));
            // Notify the adapter that the data set has changed
            fieldAdapter.notifyDataSetChanged();
        });

        // Set up the "Cancel" button in the dialog
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            // Do nothing or handle cancellation
        });

        // Show the dialog
        builder.create().show();
    }

}

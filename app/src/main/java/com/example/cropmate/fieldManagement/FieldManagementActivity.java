package com.example.cropmate.fieldManagement;
import com.example.cropmate.MainActivity;
import com.example.cropmate.R;
import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class FieldManagementActivity extends AppCompatActivity {

    private ArrayList<Field> fieldList;
    private LinearLayout fieldsLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_management);

        fieldsLinearLayout = findViewById(R.id.fieldsLinearLayout);
        fieldList = new ArrayList<>();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Fields");
        ValueEventListener valueEventListener2 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fieldList.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    Field data2 = data.getValue(Field.class);
                    if (data2 != null) {
                        fieldList.add(data2);
                    }
                }
                updateFieldViews();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };

        db.addValueEventListener(valueEventListener2);

        Button addFieldButton = findViewById(R.id.addFieldButton);
        addFieldButton.setOnClickListener(v -> showAddFieldDialog());

        // Initialize the home button and set its click listener
        ImageButton homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> {
            Intent homeIntent = new Intent(FieldManagementActivity.this, MainActivity.class);
            startActivity(homeIntent);
            finish();
        });

        updateFieldViews();
    }


    private void showAddFieldDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialogue_add_field, null);
        builder.setView(dialogView);

        EditText editName = dialogView.findViewById(R.id.editName);
        EditText editArea = dialogView.findViewById(R.id.editArea);
        EditText editCrop = dialogView.findViewById(R.id.editCrop);
        EditText editPlanted = dialogView.findViewById(R.id.editPlanted);
        EditText editSoilHealth = dialogView.findViewById(R.id.editSoilHealth);
        EditText editLocation = dialogView.findViewById(R.id.editLocation);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String name = editName.getText().toString();
            String area = editArea.getText().toString();
            String crop = editCrop.getText().toString();
            String planted = editPlanted.getText().toString();
            String soilHealth = editSoilHealth.getText().toString();
            String location = editLocation.getText().toString();

            DatabaseReference db = FirebaseDatabase.getInstance().getReference("Fields");
            Field field = new Field(name, area, crop, planted, soilHealth, location);
            db.child(field.fieldID).setValue(field);
//            fieldList.add(field);
//            updateFieldViews();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
            // Handle cancellation
        });

        builder.create().show();
    }

    private void updateFieldViews() {
        fieldsLinearLayout.removeAllViews();
        for (int i = 0; i < fieldList.size(); i++) {
            final int index = i; // 'final' keyword makes the variable effectively final
            final Field field = fieldList.get(i); // 'final' keyword is used here

            View fieldView = LayoutInflater.from(this).inflate(R.layout.item_field, fieldsLinearLayout, false);
            Button fieldDetailsButton = fieldView.findViewById(R.id.fieldDetailsButton);
            fieldDetailsButton.setText(field.getName());
            fieldDetailsButton.setOnClickListener(v -> showFieldDetails(field));

            Button editButton = fieldView.findViewById(R.id.editButton);
            editButton.setOnClickListener(v -> editField(field, index));

            Button removeButton = fieldView.findViewById(R.id.removeButton);
            removeButton.setOnClickListener(v -> {
                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Fields");
                db.child(field.fieldID).removeValue();
                fieldList.remove(index);
                updateFieldViews();
            });

            fieldsLinearLayout.addView(fieldView);
        }
    }


    private void editField(Field field, int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialogue_add_field, null); // Reusing the add field layout
        builder.setView(dialogView);

        // Get references to EditText fields in the dialog
        EditText editName = dialogView.findViewById(R.id.editName);
        EditText editArea = dialogView.findViewById(R.id.editArea);
        EditText editCrop = dialogView.findViewById(R.id.editCrop);
        EditText editPlanted = dialogView.findViewById(R.id.editPlanted);
        EditText editSoilHealth = dialogView.findViewById(R.id.editSoilHealth);
        EditText editLocation = dialogView.findViewById(R.id.editLocation);

        // Prepopulate fields with current values
        editName.setText(field.getName());
        editArea.setText(field.getArea());
        editCrop.setText(field.getCrop());
        editPlanted.setText(field.getPlanted());
        editSoilHealth.setText(field.getSoilHealth());
        editLocation.setText(field.getLocation());

        builder.setPositiveButton("Save", (dialog, which) -> {
            // Update field with new values
            field.setName(editName.getText().toString());
            field.setArea(editArea.getText().toString());
            field.setCrop(editCrop.getText().toString());
            field.setPlanted(editPlanted.getText().toString());
            field.setSoilHealth(editSoilHealth.getText().toString());
            field.setLocation(editLocation.getText().toString());

            fieldList.set(index, field); // Replace the field in the list
            updateFieldViews(); // Update the UI
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }
    private void showFieldDetails(Field field) {
        // Create and show a dialog or start an activity to display field details
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Field Details for field: "+ field.getName());
        builder.setMessage(
                "\nField ID: " + field.getFieldID() +
                "\n\n\nArea: " + field.getArea() +
                "\n\n\nCrop: " + field.getCrop() +
                "\n\n\nPlanted: " + field.getPlanted() +
                "\n\n\nSoil Health: " + field.getSoilHealth() +
                "\n\n\nLocation: " + field.getLocation());
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.show();


        };
    }



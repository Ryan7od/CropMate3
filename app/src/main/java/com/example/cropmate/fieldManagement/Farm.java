package com.example.cropmate.fieldManagement;

import java.util.ArrayList;

public class Farm {

    private ArrayList<Field> fields;

    public Farm(ArrayList<Field> fields) {
        if (fields == null) {
            throw new IllegalArgumentException("Fields cannot be null");
        }
        this.fields = fields;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Field> fields) {
        if (fields == null) {
            throw new IllegalArgumentException("Fields cannot be null");
        }
        this.fields = fields;
    }

    public void deleteField(Field field) {
        if (field == null) {
            throw new IllegalArgumentException("Field cannot be null");
        }
        this.fields.remove(field);
    }
}
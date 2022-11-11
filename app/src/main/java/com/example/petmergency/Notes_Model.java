package com.example.petmergency;

public class Notes_Model {
    private String note, description, id, date;

    public Notes_Model() {
    }

    public Notes_Model(String note, String description, String id, String date) {
        this.note = note;
        this.description = description;
        this.id = id;
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

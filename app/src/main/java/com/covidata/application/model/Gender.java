package com.covidata.application.model;

public class Gender {
    private String key;
    private int doc_count;

    public Gender(String key, int doc_count) {
        this.key = key;
        this.doc_count = doc_count;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getDoc_count() {
        return doc_count;
    }

    public void setDoc_count(int doc_count) {
        this.doc_count = doc_count;
    }
}

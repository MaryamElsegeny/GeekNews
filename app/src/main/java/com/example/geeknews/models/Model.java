package com.example.geeknews.models;

public class Model {

   private String category ;

    public Model(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Model{" +
                "category='" + category + '\'' +
                '}';
    }
}

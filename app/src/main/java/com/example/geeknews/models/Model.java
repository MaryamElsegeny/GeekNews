package com.example.geeknews.models;

public class Model {

   private String category ;
    private String NameCategory ;
    private String user ;


    public Model(String category, String nameCategory) {
        this.category = category;
        NameCategory = nameCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNameCategory() {
        return NameCategory;
    }

    public void setNameCategory(String nameCategory) {
        NameCategory = nameCategory;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Model{" +
                "category='" + category + '\'' +
                ", NameCategory='" + NameCategory + '\'' +
                '}';
    }
}

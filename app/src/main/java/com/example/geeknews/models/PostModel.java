package com.example.geeknews.models;

import com.google.gson.annotations.SerializedName;

public class PostModel {

    @SerializedName("contenttype")
   private String postType ;
   @SerializedName("title")
    private String postAbstract ;
   @SerializedName("publicationdate")
    private String PostDate ;
    @SerializedName("count")
   private String count ;
    @SerializedName("items_id")
    private int id ;


    private String postAutherName ;

    public PostModel() {
    }

    public PostModel(String postType, String postAbstract, String postDate) {
        this.postType = postType;
        this.postAbstract = postAbstract;
        PostDate = postDate;
    }


    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getPostAbstract() {
        return postAbstract;
    }

    public void setPostAbstract(String postAbstract) {
        this.postAbstract = postAbstract;
    }

    public String getPostDate() {
        return PostDate;
    }

    public void setPostDate(String postDate) {
        PostDate = postDate;
    }

    public String getPostAutherName() {
        return postAutherName;
    }

    public void setPostAutherName(String postAutherName) {
        this.postAutherName = postAutherName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PostModel{" +
                "postType='" + postType + '\'' +
                ", postAbstract='" + postAbstract + '\'' +
                ", PostDate='" + PostDate + '\'' +
                ", postAutherName='" + postAutherName + '\'' +
                '}';
    }
}

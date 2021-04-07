package com.example.geeknews.models;

public class PostModel {

   private String postType ;
    private String postAbstract ;
    private String PostDate ;
    private String postAutherName ;

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

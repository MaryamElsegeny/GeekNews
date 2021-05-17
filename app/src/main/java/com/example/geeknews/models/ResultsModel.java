package com.example.geeknews.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultsModel {

    @SerializedName("count")
    private int count ;
    @SerializedName("results")
    private List<PostModel> postModelList;


    public List<PostModel> getPostModelList() {
        return postModelList;
    }

    public void setPostModelList(List<PostModel> postModelList) {
        this.postModelList = postModelList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}

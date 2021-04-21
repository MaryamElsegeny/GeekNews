package com.example.geeknews.retrofit;


import com.example.geeknews.models.ResultsModel;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
//    @POST("login")
//    Call<PointsModel> postLogin(@Body PointsModel pointsModel);
//
    @GET("api/")
    Call<ResultsModel> getPosts(@Query("category") String Category);
//
//    @GET("getClient")
//    Call<PointsModel> getClient(@Query("id") String id);
//
//    @POST("addClient")
//    Call<PointsModel> postClintDetails(@Body PointsModel pointsModel);

}

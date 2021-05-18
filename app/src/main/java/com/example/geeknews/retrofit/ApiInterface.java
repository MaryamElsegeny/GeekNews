package com.example.geeknews.retrofit;


import com.example.geeknews.models.PostDetails;
import com.example.geeknews.models.ResultsModel;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("api/")
    Call<ResultsModel> getPosts(@Query("category") String category );

    @GET("api/")
    Call<ResultsModel> getSearch(@Query("search") String search , @Query("category") String category);

    @GET("api/")
    Call<ResultsModel> getFilter(@Query("search") String search , @Query("category") String category ,@Query("publicationdate__gte") String startDate , @Query("publicationdate__lte") String endDate , @Query("contenttype") String contenttype);

    @GET("api/details/{items_id}")
    Call<PostDetails> getDetails(@Path("items_id") int id);


}
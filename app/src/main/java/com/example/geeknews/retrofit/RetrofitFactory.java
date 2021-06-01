package com.example.geeknews.retrofit;

import com.example.geeknews.classes.MyApplication;

import java.io.IOException;

import androidx.constraintlayout.solver.widgets.Chain;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private static final String BASE_URL = "https://geeksnews.herokuapp.com/";

    private static Retrofit retrofit;


    public static Retrofit getRetrofit(){

        if (retrofit == null){

            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        }

        return retrofit ;

    }


}



package com.tekbeast.pollster;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Virus on 28-03-2018.
 */


public class ApiClient {
    
    public static final String BASE_URL="http://pollapplication.000webhostapp.com/android/";
    public static Retrofit retrofit = null;

    public static Retrofit getApiClient(){
        if(retrofit==null){
            retrofit =new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}

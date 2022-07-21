package com.example.covi19_apps.network;

import com.example.covi19_apps.model.ProvinsiSumbar;
import com.example.covi19_apps.model.ResponseDaerah;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface Service {

    @GET("getArrayCoronaLast/sumatera_barat")
    Call<List<ProvinsiSumbar>> getCoronaSum();

    @Headers("Accept-Encoding: identity")
    @GET("getDayData/sumatera_barat")
    Call<List<ProvinsiSumbar>> getData();

    @GET("getDayEachKab")
    Call<ResponseDaerah> getDaerah();
}

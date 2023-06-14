package com.example.al_quran.retrofit;

import com.example.al_quran.AyatModel.Verses;
import com.example.al_quran.SurahModel.Chapters;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndpoint {
    @GET("chapters?language=id")
    Call<Chapters> getSurah();

    @GET("quran/verses/uthmani")
    Call<Verses> getAyat(@Query("chapter_number") int id);
}

package com.example.al_quran;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.al_quran.SurahModel.Chapters;
import com.example.al_quran.SurahModel.ChaptersItem;
import com.example.al_quran.retrofit.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityBackup extends AppCompatActivity {
    private final String TAG = "MainActivity";

    RecyclerView recyclerView;
    AdapterSurahs adapterSurahs;
    ArrayList<surahmodel> objSurah = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        objSurah.add(new surahmodel(1, "Al-Fatihah", "الفاتحة",new TranslatedName("Pembukaan")));
        objSurah.add(new surahmodel(2, "Al-Fatihah", "الفاتحة",new TranslatedName("Pembukaan")));
        objSurah.add(new surahmodel(3, "Al-Fatihah", "الفاتحة",new TranslatedName("Pembukaan")));

        adapterSurahs = new AdapterSurahs(objSurah);
        recyclerView.setAdapter(adapterSurahs);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivityBackup.this));
        getDataFromApi();
    }

    private void getDataFromApi (){
        ApiService.endpoint().getSurah().enqueue(new Callback<Chapters>() {
            @Override
            public void onResponse(Call<Chapters> call, Response<Chapters> response) {
                if (response.isSuccessful()) {
                    List<ChaptersItem> result = response.body().getChapters();
                    Log.d(TAG, result.toString());
                }
            }


            @Override
            public void onFailure(Call<Chapters> call, Throwable t) {
                Log.d(TAG, t.toString());
            }
        });
    }
}
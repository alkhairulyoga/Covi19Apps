package com.example.covi19_apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.covi19_apps.adapter.DaerahAdapter;
import com.example.covi19_apps.model.ResponseDaerah;
import com.example.covi19_apps.model.ResultsItem;
import com.example.covi19_apps.network.Client;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SwipeRefreshLayout bg_eror;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        recyclerView = findViewById(R.id.rcv_item);
        bg_eror = findViewById(R.id.bg_eror);

        bg_eror.setOnRefreshListener(() -> {
            getData();
        });

        getData();
    }

    private void getData() {
        Client.getInstance().getDaerah().enqueue(new Callback<ResponseDaerah>() {
            @Override
            public void onResponse(Call<ResponseDaerah> call, Response<ResponseDaerah> response) {
                if(response.isSuccessful()){
                    setData(response.body().getResults());

                }else{
                    bg_eror.setRefreshing(false);
                    bg_eror.setVisibility(View.VISIBLE);
                    Toast.makeText(DetailActivity.this, "Response unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDaerah> call, Throwable t) {
                bg_eror.setRefreshing(false);
                bg_eror.setVisibility(View.VISIBLE);
                Toast.makeText(DetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setData(List<ResultsItem> results) {
        bg_eror.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DaerahAdapter daerahAdapter = new DaerahAdapter(results,this);
        recyclerView.setAdapter(daerahAdapter);

    }


}
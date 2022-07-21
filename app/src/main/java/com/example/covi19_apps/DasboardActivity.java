package com.example.covi19_apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.covi19_apps.model.ProvinsiSumbar;
import com.example.covi19_apps.network.Client;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DasboardActivity extends AppCompatActivity {

    SliderLayout sliderLayout;
    TextView sembuh,positif,meninggal;

    private List<Entry> yValues = new ArrayList<>();
    private LineChart lineChart;
    private List<Entry> deathValues = new ArrayList<>();
    private List<Entry> recoveryValues = new ArrayList<>();
    private ArrayList<String> xValues = new ArrayList<>();
    private TextView detail;
    SwipeRefreshLayout bg_eror;
    NestedScrollView bg_success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasboard);
        sliderLayout = findViewById(R.id.slider);
        sembuh = findViewById(R.id.tv_sembuh);
        meninggal = findViewById(R.id.tv_meninggal);
        positif = findViewById(R.id.tv_Positif);
        lineChart = findViewById(R.id.lineChart);
        detail = findViewById(R.id.detail);
        lineChart.getAxisRight().setEnabled(false);
        bg_eror = findViewById(R.id.bg_eror);
        bg_success = findViewById(R.id.bg_succes);

        detail.setOnClickListener(v -> startActivity(new Intent(DasboardActivity.this,DetailActivity.class)));

        bg_eror.setOnRefreshListener(() -> {
            setData();
            setChart();
        });

        setData();
        setChart();
        displayImage();
    }

    private void setChart() {
        Client.getInstance().getData().enqueue(new Callback<List<ProvinsiSumbar>>() {
            @Override
            public void onResponse(Call<List<ProvinsiSumbar>> call, Response<List<ProvinsiSumbar>> response) {
                if(response.isSuccessful()){
                    setUpdataChart(response.body());
                }else{
                    Log.d("TAG", "onResponse: eror");
                }
            }

            @Override
            public void onFailure(Call<List<ProvinsiSumbar>> call, Throwable t) {
                Log.d("TAG", "onResponse: "+t.getMessage());

            }
        });
    }

    private void setUpdataChart(List<ProvinsiSumbar> pasienList) {
        if(pasienList != null){
            int time =0;
            for(int i= pasienList.size()-7 ; i<pasienList.size(); i++){
                Log.i("TAG",pasienList.get(i).getPositif());
                String[] parts = pasienList.get(i).getWaktu().split(" ");
                String[] parts1 = parts[0].split("-");
                yValues.add(new Entry(time,Float.parseFloat(pasienList.get(i).getPositif())));
                deathValues.add(new Entry(time,Float.parseFloat(pasienList.get(i).getCovidMeninggal())));
                recoveryValues.add(new Entry(time,Float.parseFloat(pasienList.get(i).getCovidSembuh())));
                xValues.add(parts1[2]);
                time++;
            }
            lineChart.setPinchZoom(false);
            lineChart.setBackgroundColor(Color.WHITE);
            lineChart.getDescription().setEnabled(false);
            lineChart.animateX(1500);

            MyMarkerView mv = new MyMarkerView(this, R.layout.custome_marker_view);

            // Set the marker to the chart
            mv.setChartView(lineChart);
            lineChart.setMarker(mv);

            LineDataSet d1 = new LineDataSet(yValues, "Positif : " + pasienList.get(pasienList.size()-1).getPositif());
            d1.setLineWidth(2.5f);
            d1.setCircleRadius(4.5f);
            d1.setHighLightColor(Color.rgb(244, 117, 117));
            d1.setDrawFilled(true);
            d1.setDrawValues(true);


            LineDataSet d2 = new LineDataSet(deathValues, "Meninggal : " + pasienList.get(pasienList.size()-1).getCovidMeninggal());
            d2.setLineWidth(2.5f);
            d2.setCircleRadius(4.5f);
            d2.setHighLightColor(Color.rgb(244, 117, 117));
            d2.setColor(Color.RED);
            d2.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
            d2.setFillColor(Color.RED);
            d2.setDrawFilled(true);
            d2.setDrawValues(true);
            d2.setValueTextColor(Color.RED);

            LineDataSet d3 = new LineDataSet(recoveryValues, "sembuh : " + pasienList.get(pasienList.size()-1).getCovidSembuh());
            d3.setLineWidth(2.5f);
            d3.setCircleRadius(4.5f);
            d3.setHighLightColor(Color.rgb(244, 117, 117));
            d3.setColor(Color.GREEN);
            d3.setFillColor(Color.GREEN);
            d3.setDrawFilled(true);
            d3.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
            d3.setDrawValues(true);


            ArrayList<ILineDataSet> sets = new ArrayList<>();
            sets.add(d1);
            sets.add(d2);
            sets.add(d3);
            LineData data = new LineData(sets);
            lineChart.setData(data);

            XAxis xAxis = lineChart.getXAxis();
            xAxis.setValueFormatter(new MyAxisFormat(xValues));
            xAxis.setPosition(XAxis.XAxisPosition.TOP);

        }else{
            Toast.makeText(this, "Data null", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        Client.getInstance().getCoronaSum().enqueue(new Callback<List<ProvinsiSumbar>>() {
            @Override
            public void onResponse(Call<List<ProvinsiSumbar>> call, Response<List<ProvinsiSumbar>> response) {
                if(response.isSuccessful()){
                    bg_eror.setRefreshing(false);
                    bg_eror.setVisibility(View.GONE);
                    bg_success.setVisibility(View.VISIBLE);
                    sembuh.setText(response.body().get(0).getCovidSembuh());
                    meninggal.setText(response.body().get(0).getCovidMeninggal());
                    positif.setText(response.body().get(0).getPositif());

                }else{
                    Toast.makeText(DasboardActivity.this, "Response is not success", Toast.LENGTH_SHORT).show();
                    bg_eror.setRefreshing(false);
                    bg_eror.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<ProvinsiSumbar>> call, Throwable t) {
                Toast.makeText(DasboardActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                bg_eror.setRefreshing(false);
                bg_eror.setVisibility(View.VISIBLE);
            }
        });

    }

    private class MyAxisFormat extends ValueFormatter {
        ArrayList<String> mValues;

        public MyAxisFormat(ArrayList<String> mValues) {
            this.mValues = mValues;
        }

        @Override
        public String getFormattedValue(float value) {
            return mValues.get((int) value);
        }
    }

    private void displayImage() {
        List<Integer> list = new ArrayList<>();

        list.add(R.drawable.ss1);
        list.add(R.drawable.ss2);

        for (int i =0; i<list.size(); i++){
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .description("")
                    .image(list.get(i))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            sliderLayout.addSlider(textSliderView);
        }
    }
}
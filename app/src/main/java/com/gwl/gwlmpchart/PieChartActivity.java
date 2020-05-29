package com.gwl.gwlmpchart;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.gwl.chartlibrary.view.PieChartView;

import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity {

    private ArrayList<Float>  listData ;
    private ArrayList<Integer> listColor ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart);
        PieChartView pieChartView=findViewById(R.id.pieChartView);
        listData = new ArrayList<>();
        listColor = new ArrayList<>();
        listData.add(12.4f);
        //listData.add(15.4f);
        //listData.add(2.4f);
        listData.add(32.4f);
        listData.add(42.4f);
        listData.add(37.4f);
        listData.add(29.4f);

        listColor.add(ContextCompat.getColor(this, com.gwl.chartlibrary.R.color.color_E60019));
        //listColor.add(ContextCompat.getColor(this, R.color.color_F8800F));
        //listColor.add(ContextCompat.getColor(this, R.color.color_C6EE11));
        listColor.add(ContextCompat.getColor(this, com.gwl.chartlibrary.R.color.color_00B0F0));
        listColor.add(ContextCompat.getColor(this, com.gwl.chartlibrary.R.color.color_002987));
        listColor.add(ContextCompat.getColor(this, com.gwl.chartlibrary.R.color.color_7C1BBE));
        listColor.add(ContextCompat.getColor(this, com.gwl.chartlibrary.R.color.color_808080));
        pieChartView.setData(listData,listColor);
    }
}

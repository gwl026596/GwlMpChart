package com.gwl.gwlmpchart;

import android.graphics.PointF;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gwl.chartlibrary.view.BarChartView;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchart);
        BarChartView barChartView=findViewById(R.id.barChartView);
        ArrayList<PointF>  pointFs = new ArrayList<PointF>();
        pointFs.add(new PointF(1F, 2.7F));
        pointFs.add(new PointF(2F, 3.5F));
        pointFs.add(new PointF(3F, 3.2F));
        pointFs.add(new PointF(4F, 1.8F));
        pointFs.add(new PointF(5F, 1.5F));
        pointFs.add(new PointF(6F, 2.2F));
        pointFs.add(new PointF(7F, 5.2F));
        pointFs.add(new PointF(8F, 1.8F));
        pointFs.add(new PointF(9F, 1.5F));
        pointFs.add(new PointF(10F, 2.2F));
        pointFs.add(new PointF(11F, 5.2F));
        pointFs.add(new PointF(12F, 1.5F));
        barChartView.setData(pointFs);
    }
}

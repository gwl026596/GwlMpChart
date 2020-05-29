package com.gwl.gwlmpchart;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PointF;
import android.os.Bundle;

import com.gwl.chartlibrary.view.LineChartView;

import java.util.ArrayList;

public class LineChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linechart);
        LineChartView lineChartView=findViewById(R.id.lineChartView);
        ArrayList<PointF>  pointFs = new ArrayList<PointF>();
        pointFs.add(new PointF(0.3F, 0.5F));
        pointFs.add(new PointF(1F, 2.7F));
        pointFs.add(new PointF(2F, 3.5F));
        pointFs.add(new PointF(3F, 7.2F));
        pointFs.add(new PointF(4F, 9.8F));
        pointFs.add(new PointF(5F, 13.5F));
        pointFs.add(new PointF(6F, 17.2F));
        pointFs.add(new PointF(7F, 20.5F));
        pointFs.add(new PointF(8F, 25F));
        pointFs.add(new PointF(18.6F, 29.7F));
        lineChartView.setData(pointFs);
    }
}

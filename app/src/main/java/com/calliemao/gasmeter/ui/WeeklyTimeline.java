package com.calliemao.gasmeter.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.calliemao.gasmeter.R;
import com.db.chart.model.BarSet;
import com.db.chart.view.BarChartView;
import com.db.chart.view.animation.Animation;
import com.db.chart.view.animation.easing.LinearEase;
import com.db.chart.view.animation.easing.ElasticEase;

public class WeeklyTimeline extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_timeline);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        BarChartView barChartView = (BarChartView) findViewById(R.id.linechart);

        String[] labels = {"Mon", "Tues", "Wed", "Thurs", "Fri", "Sat", "Sun"};
        float[] data = {20, 14, 17, 18, 22, 8, 6};

//        BarSet test = new BarSet(labels, data);  
        for (int i = 0; i < testData.length; i++) { 
            Bar bar = new Bar(labels[i], data[i]); 
            if (i % 2 == 0) bar.setColor(0); else bar.setColor(-10000); }  test.setColor(-10000); 
    }
        barChartView.addData(test);

        Animation animation = new Animation(1500);
        animation.setEasing(new ElasticEase());


//        barChartView.setAxisBorderValues(0, 100, 5);
        barChartView.show(animation);

    }
}

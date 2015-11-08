package com.calliemao.gasmeter.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.calliemao.gasmeter.R;
import com.db.chart.model.Bar;
import com.db.chart.model.BarSet;
import com.db.chart.view.BarChartView;
import com.db.chart.view.animation.Animation;
import com.db.chart.view.animation.easing.BaseEasingMethod;
import com.db.chart.view.animation.easing.CircEase;
import com.db.chart.view.animation.easing.ExpoEase;
import com.db.chart.view.animation.easing.LinearEase;

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

        /*Bar bar1 = new Bar("Friday", 5);
        Bar bar2 = new Bar("Saturday", 5);
        Bar bar3 = new Bar("Sunday", 5);*/
        String[] testLabels = {"Mon", "Tues", "Wed", "Thurs", "Fri", "Sat", "Sun"};
        float[] testData = {20, 14, 17, 18, 22, 8, 6};
        BarSet test = new BarSet(testLabels, testData);
        test.setColor(-10);
        barChartView.addData(test);

        Animation animation = new Animation(1000);
        //animation.setAlpha(0.5);
        animation.setEasing(new LinearEase()); //ElasticEase

//        barChartView.setAxisBorderValues(0, 100, 5);
        barChartView.show(animation);

    }
}

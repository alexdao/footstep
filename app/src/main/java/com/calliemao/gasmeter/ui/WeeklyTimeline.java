package com.calliemao.gasmeter.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.calliemao.gasmeter.R;
import com.db.chart.model.Bar;
import com.db.chart.model.BarSet;
import com.db.chart.view.BarChartView;

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
        String[] testLabels = {"Friday", "Saturday", "Sunday"};
        float[] testData = {5, 10, 12};
        BarSet test = new BarSet(testLabels, testData);

        barChartView.addData(test);
    }
}

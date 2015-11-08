package com.calliemao.gasmeter.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.calliemao.gasmeter.R;
import com.db.chart.model.Bar;
import com.db.chart.model.BarSet;
import com.db.chart.view.BarChartView;
import com.db.chart.view.animation.Animation;
import com.db.chart.view.animation.easing.ElasticEase;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeeklyTimeline extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.linechart)
    BarChartView barChartView;

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    LinearLayoutManager llm;
    RVAdapter adapter;

    List<TimelineItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_timeline);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        initializeData();
        initializeRecyclerView(findViewById(R.id.recycler_view));

       // String[] testLabels = {"Mon", "Tues", "Wed", "Thurs", "Fri", "Sat", "Sun"};
      //  float[] testData = {19, 14, 17, 18, 19, 8, 6};
        BarSet test = new BarSet();
        test.setColor(-10);
        barChartView.addData(test);

        for (int i = 0; i < items.size(); i++){
            Bar bar = new Bar(items.get(i).getDayOfWeek(), items.get(i).getDistance());

            if (i % 2 == 0)
                bar.setColor(Color.parseColor("#DDDDDD"));
            else
                bar.setColor(-5);
            test.addBar(bar);
        }

        Animation animation = new Animation(2500);
        animation.setEasing(new ElasticEase());

//        barChartView.setAxisBorderValues(0, 100, 5);
        barChartView.show(animation);

    }

    private void initializeData(){
        items = new ArrayList<>();
        items.add(new TimelineItem("Mon", "11/2", 19, "miles"));
        items.add(new TimelineItem("Tues", "11/3", 14, "miles"));
        items.add(new TimelineItem("Wed", "11/4", 17, "miles"));
        items.add(new TimelineItem("Thurs", "11/5", 18, "miles"));
        items.add(new TimelineItem("Fri", "11/6", 19, "miles"));
        items.add(new TimelineItem("Sat", "11/7", 8, "miles"));
        items.add(new TimelineItem("Sun", "11/8", 6, "miles"));
    }

    private void initializeRecyclerView(View view) {
        adapter = new RVAdapter(items, view);
        llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(itemDecoration);
    }

    public static class RVAdapter extends RecyclerView.Adapter<RVAdapter.TimelineViewHolder> {

        List<TimelineItem> items;
        View view;

        RVAdapter(List<TimelineItem> items, View view) {
            this.items = items;
            this.view = view;
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        @Override
        public TimelineViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
            Log.d("entered here", "sdfsdjf");
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item_timeline, viewGroup, false);
            TimelineViewHolder pvh = new TimelineViewHolder(v, new TimelineViewHolder.ISunlightDataViewHolderClicks() {
                public void onClickItem(View caller) {
                }
            });
            return pvh;
        }

        @Override
        public void onBindViewHolder(TimelineViewHolder timelineViewHolder, int i) {
            Log.d("entered here - date", items.get(i).getDate());
            timelineViewHolder.date.setText(items.get(i).getDayOfWeek() + " " + items.get(i).getDate());
            timelineViewHolder.distance.setText(Integer.toString(items.get(i).getDistance()) + " " + items.get(i).getUnits());
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        public static class TimelineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            RelativeLayout relativeLayout;
            TextView date;
            TextView distance;
            public ISunlightDataViewHolderClicks mListener;

            TimelineViewHolder(View itemView, ISunlightDataViewHolderClicks listener) {
                super(itemView);
                Log.d("entered here", "1");
                mListener = listener;
                relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative_layout);
                relativeLayout.setOnClickListener(this);
                date = ButterKnife.findById(itemView, R.id.date);
                distance = ButterKnife.findById(itemView, R.id.distance);
            }

            @Override
            public void onClick(View v) {
                mListener.onClickItem(v);
            }

            public interface ISunlightDataViewHolderClicks {
                void onClickItem(View caller);
            }
        }
    }
}

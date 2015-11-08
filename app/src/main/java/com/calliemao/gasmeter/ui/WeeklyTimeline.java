package com.calliemao.gasmeter.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.calliemao.gasmeter.R;
import com.db.chart.model.BarSet;
import com.db.chart.view.BarChartView;
import com.db.chart.view.animation.Animation;
import com.db.chart.view.animation.easing.LinearEase;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeeklyTimeline extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.fab)
    FloatingActionButton fab;

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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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

    private void initializeData(){
        items = new ArrayList<>();
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
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item_stats, viewGroup, false);
            TimelineViewHolder pvh = new TimelineViewHolder(v, new TimelineViewHolder.ISunlightDataViewHolderClicks() {
                public void onClickItem(View caller) {
                }
            });
            return pvh;
        }

        @Override
        public void onBindViewHolder(TimelineViewHolder timelineViewHolder, int i) {
            timelineViewHolder.date.setText(items.get(i).date);
            timelineViewHolder.distance.setText(items.get(i).distance);
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

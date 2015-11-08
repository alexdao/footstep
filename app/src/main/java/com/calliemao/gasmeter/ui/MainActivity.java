package com.calliemao.gasmeter.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.calliemao.gasmeter.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    List<StatItem> stats;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    LinearLayoutManager llm;
    RVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        initializeData();
        initializeRecyclerView(findViewById(R.id.recycler_view));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.timeline) {
            Intent myIntent = new Intent(MainActivity.this, WeeklyTimeline.class);
            MainActivity.this.startActivity(myIntent);
        }
        if (id == R.id.map) {
            Intent myIntent = new Intent(MainActivity.this, PathsMap.class);
            MainActivity.this.startActivity(myIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void initializeData(){
        stats = new ArrayList<>();
        String[] stats_array = getResources().getStringArray(R.array.stats_array);
        String[] photo_array = getResources().getStringArray(R.array.photo_ID);

        for(int i = 0; i<stats_array.length; i++){
            stats.add(new StatItem(photo_array[i], stats_array[i], 5));
        }
    }

    private void initializeRecyclerView(View view) {
        adapter = new RVAdapter(stats, view);
        llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(itemDecoration);
    }

    public static class RVAdapter extends RecyclerView.Adapter<RVAdapter.MainStatViewHolder> {

        List<StatItem> stats;
        View view;

        RVAdapter(List<StatItem> stats, View view) {
            this.stats = stats;
            this.view = view;
        }

        @Override
        public int getItemCount() {
            return stats.size();
        }

        @Override
        public MainStatViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item_stats, viewGroup, false);
            MainStatViewHolder pvh = new MainStatViewHolder(v, new MainStatViewHolder.ISunlightDataViewHolderClicks() {
                public void onClickItem(View caller) {
                }
            });
            return pvh;
        }

        @Override
        public void onBindViewHolder(MainStatViewHolder mainStatViewHolder, int i) {
            mainStatViewHolder.rightTitle.setText(Double.toString(stats.get(i).amount));
            mainStatViewHolder.mainDescription.setText(stats.get(i).description);

            Context context = mainStatViewHolder.statImage.getContext();
            int picID;
            if(i==0){
                picID = R.drawable.ic_directions_car_black_48dp;
            }
            else if(i==1){
                picID = R.drawable.ic_local_gas_station_black_48dp;
            }
            else if(i==2){
                picID = R.drawable.ic_favorite_border_black_48dp;
            }
            else{
                picID = R.drawable.ic_directions_run_black_48dp;
            }
            Picasso.with(context)
                    .load(picID)
                    .fit().centerCrop()
                    .into(mainStatViewHolder.statImage);
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        public static class MainStatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            RelativeLayout relativeLayout;
            ImageView statImage;
            TextView rightTitle;
            TextView mainDescription;
            public ISunlightDataViewHolderClicks mListener;

            MainStatViewHolder(View itemView, ISunlightDataViewHolderClicks listener) {
                super(itemView);
                mListener = listener;
                relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative_layout);
                relativeLayout.setOnClickListener(this);
                statImage = ButterKnife.findById(itemView, R.id.stat_photo);
                rightTitle = ButterKnife.findById(itemView, R.id.mainTitle);
                mainDescription = ButterKnife.findById(itemView, R.id.mainDescription);
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

package com.example.bagpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PerformanceHintManager;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<String> titles;
    List<Integer> images;
    Adapter adapter;
    RoomDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        addItem();
        addImages();
        AppDate();
        database = RoomDB.getInstance(this);
        System.out.println("----------------->"+database.mainDao().
                getAllSelected(false).get(0).getItemname());
        adapter = new Adapter(this,titles,images,MainActivity.this);
        GridLayoutManager GLM = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(GLM);
        recyclerView.setAdapter(adapter);
    }
    private void AppDate(){
        SharedPreferences perfs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = perfs.edit();
        database = RoomDB.getInstance(this);
        AppData appDate = new AppData(database);
        int last = perfs.getInt(AppData.LastVersion,0);
        if (!perfs.getBoolean(Constants.FIRST_TIME_CAMEL_CASE,false)){
            appDate.pushAllData();
            editor.putBoolean(Constants.FIRST_TIME_CAMEL_CASE,true);
            editor.commit();
        }else if (last<AppData.NewVersion){
            database.mainDao().deleteAllSystemItem(Constants.SYSTEM_SMALL);
            appDate.pushAllData();
            editor.putInt(AppData.LastVersion,AppData.NewVersion);
            editor.commit();
        }
    }

    private void addItem(){
        titles = new ArrayList<>();
        titles.add(Constants.BASIC_NEEDS_CAMEL_CASE);
        titles.add(Constants.CLOTHING_CAMEL_CASE);
        titles.add(Constants.PERSONAL_CARE_CAMEL_CASE);
        titles.add(Constants.BABY_NEEDS_CAMEL_CASE);
        titles.add(Constants.HEALTH_CAMEL_CASE);
        titles.add(Constants.TECHNOLOGY_CAMEL_CASE);
        titles.add(Constants.FOOD_CAMEL_CASE);
        titles.add(Constants.BEACH_SUPPLIES_CAMEL_CASE);
        titles.add(Constants.CAR_SUPPLIES_CAMEL_CASE);
        titles.add(Constants.NEEDS_CAMEL_CASE);
        titles.add(Constants.MY_LIST_CAMEL_CASE);
        titles.add(Constants.MY_SELECTIONS_CAMEL_CASE);
    }
    private void addImages(){
        images = new ArrayList<>();
        images.add(R.drawable.p1);
        images.add(R.drawable.p2);
        images.add(R.drawable.p3);
        images.add(R.drawable.p4);
        images.add(R.drawable.p5);
        images.add(R.drawable.p6);
        images.add(R.drawable.p7);
        images.add(R.drawable.p8);
        images.add(R.drawable.p9);
        images.add(R.drawable.p10);
        images.add(R.drawable.p11);
        images.add(R.drawable.p12);
    }
}
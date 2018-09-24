package com.z2wenfa.toucheventdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.Arrays;

public class NestedScrollingDemoActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scrolling_demo);
        RecyclerView listView = findViewById(R.id.listView);
        String[] arr = new String[50];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = String.valueOf(i);
        }
        listView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listView.setAdapter(new CommonAdapter<String>(this, android.R.layout.simple_list_item_1, Arrays.asList(arr)) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                TextView textView = holder.getView(android.R.id.text1);
                textView.setText(s);
            }
        });
    }
}

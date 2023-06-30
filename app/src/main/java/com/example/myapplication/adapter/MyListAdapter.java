package com.example.myapplication.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MyListActivity;
import com.example.myapplication.R;
import com.example.myapplication.models.IdNameModel;

import java.util.List;

public class MyListAdapter extends BaseAdapter {
    private static final String TAG = "MyListAdapter";
    private List<IdNameModel> items;
    private MyListActivity activity;

    public MyListAdapter(MyListActivity activity, List<IdNameModel> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        IdNameModel item = items.get(position);

        view = LayoutInflater.from(activity).inflate(R.layout.activity_my_list_item, viewGroup, false);
        TextView id = view.findViewById(R.id.tv_id);
        TextView name = view.findViewById(R.id.tv_name);
        id.setText(String.valueOf(item.getId()));
        name.setText(item.getName());

//        name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(activity, item.getName(), Toast.LENGTH_LONG).show();
//                Log.d(TAG, "onClick: " + item.getName());
//            }
//        });

        return view;
    }
}

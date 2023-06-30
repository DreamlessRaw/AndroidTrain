package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.adapter.MyListAdapter;
import com.example.myapplication.models.IdNameModel;

import java.util.List;

public class MyListActivity extends AppCompatActivity {

    private static final String TAG = "MyListActivity";
    private ListView listView;
    private MyListAdapter adapter;
    private List<IdNameModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        listView = findViewById(R.id.listView);
        list = IdNameModel.mockData();
        adapter = new MyListAdapter(this, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MyListActivity.this, list.get(i).getName(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
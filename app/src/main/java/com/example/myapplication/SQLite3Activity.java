package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class SQLite3Activity extends AppCompatActivity {

    private Button mBtnRead;
    private Button mBtnWrite;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite3);
        mBtnRead = findViewById(R.id.btn_read);
        mBtnWrite = findViewById(R.id.btn_write);

    }
}
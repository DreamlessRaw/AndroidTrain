package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SharedPreferencesActivity extends AppCompatActivity {

    private TextView mTvContent;

    private Button mBtnRead;

    private Button mBtnWrite;

    private static final String KEY = "TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        mTvContent = findViewById(R.id.tv_content);
        mBtnRead = findViewById(R.id.btn_read);
        mBtnWrite = findViewById(R.id.btn_write);

//        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);

        SharedPreferences sharedPreferences = this.getApplicationContext().getSharedPreferences("SHAREDPREFERENCES", Context.MODE_PRIVATE);


        mBtnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 读取
                String content = sharedPreferences.getString(KEY, "");
                mTvContent.setText(content);
            }
        });

        mBtnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取Editor
                SharedPreferences.Editor editor = sharedPreferences.edit();
                // 写入数据
                editor.putString(KEY, "测试");
                // 提交
                editor.apply();
            }
        });
    }
}
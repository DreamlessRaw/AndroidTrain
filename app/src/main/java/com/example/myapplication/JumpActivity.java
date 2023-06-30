package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class JumpActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "JumpActivity";

    private Button mBtnNoParam;
    private Button mBtnParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump);
        this.setTitle("跳转界面");

        mBtnNoParam = findViewById(R.id.btn_noParam);

        mBtnParam = findViewById(R.id.btn_param);

        mBtnNoParam.setOnClickListener(this);
        mBtnParam.setOnClickListener(this);

        String data = getIntent().getStringExtra("data");
        if (data != null && !data.equals("")) {
            Toast.makeText(this, data, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 开始
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    /**
     * 停止
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    /**
     * 销毁,finish
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    /**
     * 界面暂停显示
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }


    /**
     * 界面恢复显示
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_noParam) {
            this.finish();
        } else if (view.getId() == R.id.btn_param) {
            Intent intent = new Intent();
            intent.putExtra("data", "这是返回结果");
            setResult(Activity.RESULT_OK,intent);
            finish();
        } else {
            this.finish();
        }
    }
}
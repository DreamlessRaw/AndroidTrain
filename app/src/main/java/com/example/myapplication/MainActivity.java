package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String TAG = "MainActivity";

    private Button mBtnNoParam;
    private Button mBtnParam;
    private Button mBtnListview;
    private Button mBtnSharedPreferences;
    private Button mBtnSqlite3;

    private ImageView mImageView;

    /**
     * 创建
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        this.setTitle("主界面");
        mBtnNoParam = findViewById(R.id.btn_noParam);
        mBtnParam = findViewById(R.id.btn_param);
        mBtnListview = findViewById(R.id.btn_list);
        mBtnSharedPreferences = findViewById(R.id.btn_shared);
        mBtnSqlite3 = findViewById(R.id.btn_sqlite3);

        mBtnNoParam.setOnClickListener(this);
        mBtnParam.setOnClickListener(this);
        mBtnListview.setOnClickListener(this);
        mBtnSharedPreferences.setOnClickListener(this);
        mBtnSqlite3.setOnClickListener(this);


        // ImageView
        mImageView = findViewById(R.id.iv_01);
//        // 一、①本地方式,Resource
//        mImageView.setImageResource(R.drawable.asura);
        // 一、②本地方式,assets
        try {
            InputStream inputStream = getAssets().open("asura.png");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            mImageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            Toast.makeText(this, "加载失败", Toast.LENGTH_SHORT).show();
        }

//        // 二、插件方式
//        Glide.with(this)
//                .load("https://imglf3.lf127.net/img/UmVzdmpRd3BkZU9HT2xoWkFnRWJNU0FLcEhLQys3cE5zd1dVZmxPdnRTQWRyR1VlQ2ppRXZ3PT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg")
//                .into(mImageView);

//        // 三、主线程不能直接发起请求，需要单独开线程
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                setImage();
//            }
//        }).start();

    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {

            Bitmap bitmap = (Bitmap) message.obj;
            mImageView.setImageBitmap(bitmap);
            return false;
        }
    });

    private void setImage() {
        try {
            URL url = new URL("https://imglf3.lf127.net/img/UmVzdmpRd3BkZU9HT2xoWkFnRWJNU0FLcEhLQys3cE5zd1dVZmxPdnRTQWRyR1VlQ2ppRXZ3PT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            int code = connection.getResponseCode();
            if (code == 200) {
                InputStream inputStream = connection.getInputStream();
                //使用工厂把网络的输入流生产Bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                // 使用Handler方式
                Message msg = new Message();
                msg.what = 0;
                msg.obj = bitmap;
                mHandler.sendMessage(msg);
//                // 需要回到UI主线程,也可以使用Handler
//                this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mImageView.setImageBitmap(bitmap);
//                    }
//                });
                inputStream.close();

            } else {
                this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
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

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
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
            this.startActivity(new Intent(this, JumpActivity.class));
//            this.finish(); // 决定是否关闭当前界面
        } else if (view.getId() == R.id.btn_param) {
            Intent intent = new Intent(this, JumpActivity.class);
            intent.putExtra("data", "这是传递参数");
            this.startActivityForResult(intent, 1000);
        } else if (view.getId() == R.id.btn_list) {
            this.startActivity(new Intent(this, MyListActivity.class));
        } else if (view.getId() == R.id.btn_shared) {
            this.startActivity(new Intent(this, SharedPreferencesActivity.class));
        } else if (view.getId() == R.id.btn_sqlite3) {
            this.startActivity(new Intent(this, SQLite3Activity.class));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 1000:
                    Toast.makeText(this, data.getStringExtra("data"), Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
}
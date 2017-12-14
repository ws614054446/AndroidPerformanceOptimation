package com.wangshuai.androidperformanceoptimation.network;

import android.net.http.HttpResponseCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wangshuai.androidperformanceoptimation.R;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetWorkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_work);


    }

    private void openCache(){

        try {
            //Android系统默认的HttpRe(网络请求相应缓存)是关闭的
            File cacheDir = new File(getCacheDir(),"http");
            long maxSize = 10*1024*1024;//缓存大小，单位byte
            HttpResponseCache.install(cacheDir,maxSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void request(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL("").openConnection();
                    connection.setDoInput(true);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

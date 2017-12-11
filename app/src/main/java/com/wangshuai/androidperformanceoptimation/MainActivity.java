package com.wangshuai.androidperformanceoptimation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.wangshuai.androidperformanceoptimation.memoryleakage.MemoryLeakageActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton btnMemoryLeakage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        btnMemoryLeakage = (AppCompatButton) findViewById(R.id.btn_memory_leakage);
        btnMemoryLeakage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_memory_leakage:
                startActivity(new Intent(MainActivity.this, MemoryLeakageActivity.class));
                break;
        }
    }
}

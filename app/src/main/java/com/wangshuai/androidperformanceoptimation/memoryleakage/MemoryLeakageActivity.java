package com.wangshuai.androidperformanceoptimation.memoryleakage;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wangshuai.androidperformanceoptimation.R;

import java.lang.ref.WeakReference;

/**
 * 内存泄漏
 */
public class MemoryLeakageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leakage);

        CommonUtils commonUtils = CommonUtils.getInstance(this);//单例导致内存泄漏例子

//        loadData();
//
//        mHandler.sendEmptyMessage(0);


    }

    /**
     * 错误写法（匿名内部类持外部引用造成内存泄漏问题）
     * 隐式持有Activity实例
     * 解决方案：将非静态内部类改为静态内部类（静态内部类不会隐式持有外部类）
     */
    int a = 0;
    public void loadData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //进行操作
                int b = a;
            }
        }).start();
    }

    //匿名内部类的实例，也会引用外部对象MainActivity.this
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //
        }
    };

    //解决方案2：改成静态类,集成Handler
    private static class MyHandler extends Handler{
        //如果需要持有一个外部类的强引用
        private WeakReference<MemoryLeakageActivity> memoryReference;//设置软引用，当内存发生GC时候就会回收

        public MyHandler(MemoryLeakageActivity leakageActivity){
            this.memoryReference = new WeakReference<MemoryLeakageActivity>(leakageActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //
            MemoryLeakageActivity memoryLeakageActivity = memoryReference.get();
            if (memoryLeakageActivity == null || memoryLeakageActivity.isFinishing()){
                return;
            }
            switch (msg.what){
                case 0:
                    break;
            }
        }
    }
}

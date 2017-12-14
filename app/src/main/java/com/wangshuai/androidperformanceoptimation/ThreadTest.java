package com.wangshuai.androidperformanceoptimation;

/**
 * Created by 王帅 on 2017/12/14.
 */

public class ThreadTest {

    public static void main(String[] srgs){
        Person person = new Person();
        new Thread(new Producer(person)).start();
        new Thread(new Consumer(person)).start();
    }

   static class MyThread implements Runnable{

       @Override
       public void run() {
           System.out.print("thread");
       }
   }
}

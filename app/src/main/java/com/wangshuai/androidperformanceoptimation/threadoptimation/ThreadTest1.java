package com.wangshuai.androidperformanceoptimation.threadoptimation;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by 王帅 on 2017/12/14.
 */

public class ThreadTest1 {

    static class ProduceObject{
        private String value = "";
    }

    public static void main(String[] args){
//        Handler
        Task work = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(work){
            @Override
            protected void done() {

            }
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(futureTask);

        try {
            futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class Producer extends Thread{
        @Override
        public void run() {
            while (true){

            }
        }
    }

    static class Task implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            return null;
        }
    }

}

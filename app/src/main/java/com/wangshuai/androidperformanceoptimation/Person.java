package com.wangshuai.androidperformanceoptimation;

/**
 * Created by 王帅 on 2017/12/14.
 */

public class Person {
    private String name;
    private String sex;
    private boolean bFull = false;//是否完成赋值

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public synchronized void set(String name,String sex){

        if (bFull){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = name;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.sex = sex;
        bFull = true;
        notify();
    }

    public synchronized void get(){
        if (!bFull){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this.name+"-------------"+this.sex);
        bFull = false;
        notify();
    }
}

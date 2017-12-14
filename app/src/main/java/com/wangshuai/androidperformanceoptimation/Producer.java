package com.wangshuai.androidperformanceoptimation;

/**
 * Created by 王帅 on 2017/12/14.
 */

public class Producer implements Runnable {

    Person person = null;

    public Producer(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        int i = 0;
        while (true){
            if (i == 0){
//                person.setName("张三");
//                person.setSex("男");
                person.set("张三","男");
            }else {
//                person.setName("小红");
//                person.setSex("女");
                person.set("小红","女");
            }
            i= (i+1)%2;
        }
    }
}

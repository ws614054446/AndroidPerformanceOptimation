package com.wangshuai.androidperformanceoptimation;

/**
 * Created by 王帅 on 2017/12/14.
 */

public class Consumer implements Runnable {

    Person person = null;

    public Consumer(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        while (true){
//            System.out.println(person.getName()+"------"+person.getSex());
            person.get();
        }
    }
}

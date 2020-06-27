package com.zjj.juc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/27 12:58
 */
public class TestCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadDemo th=new ThreadDemo();

        FutureTask<Integer> fu=new FutureTask<>(th);//类似于CountDownLatch闭锁
        new Thread(fu).start();
        System.out.println(fu.get());
        System.out.println("-------------------");
    }
}

/*
 * 一、创建执行线程的方式三：实现 Callable 接口。 相较于实现 Runnable 接口的方式，方法可以有返回值，并且可以抛出异常。
 *
 * 二、执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。  FutureTask 是  Future 接口的实现类
 */
class ThreadDemo implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        int sum=0;
        for (int i=0;i<1000;i++){
            sum+=i;
        }
        return sum;
    }
}

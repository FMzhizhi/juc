package com.zjj.juc.thread;

import com.sun.xml.internal.ws.message.source.PayloadSourceMessage;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/27 17:07
 */
public class TestABCAlternate {
    public static void main(String[] args) {
        AlternateDemo at=new AlternateDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<=20;i++){
                    at.loopA(i);
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<=20;i++){
                    at.loopB(i);
                }
            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<=20;i++){
                    at.loopC(i);
                }
            }
        },"C").start();

    }
}
class AlternateDemo{
    private int num=1; //打印的标记 1 打印A 2 打印B 3打印C
    private Lock lock=new ReentrantLock();
    Condition condition1=lock.newCondition();
    Condition condition2=lock.newCondition();
    Condition condition3=lock.newCondition();

    //totalLoop 循环的轮数
    public void loopA(int totalLoop){
        lock.lock();
        try {
            if (num!=1){
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i=1;i<=5;i++){
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
            }
            num=2;
            condition2.signal();
        } finally {
            lock.unlock();

        }
    }

    //totalLoop 循环的轮数
    public void loopB(int totalLoop){
        lock.lock();
        try {
            if (num!=2){
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i=1;i<=5;i++){
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
            }
            num=3;
            condition3.signal();
        } finally {
            lock.unlock();

        }
    }

    //totalLoop 循环的轮数
    public void loopC(int totalLoop){
        lock.lock();
        try {
            if (num!=3){
                try {
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i=1;i<=5;i++){
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
            }
            num=1;
            condition1.signal();
        } finally {
            lock.unlock();

        }
    }

}

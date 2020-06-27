package com.zjj.juc.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/27 15:09
 */
public class TestProductAndConsumerForLock {
    public static void main(String[] args) {
        Clerk clerk=new Clerk();
        Productor pro=new Productor(clerk);
        Consumer con=new Consumer(clerk);
        new Thread(pro,"生产者A").start();
        new Thread(con,"消费者B").start();
        new Thread(pro,"生产者C").start();
        new Thread(con,"消费者D").start();

    }
}

//店员
class Clerk {
    private int product = 0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    //进货
    public  void get() {
        lock.lock();
        try {
            while (product >= 1) {
                System.out.println("已经满货了。。");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "在进货" + ++product);
            condition.signalAll();
        } finally {
            lock.unlock();

        }

    }

    //卖货
    public  void sale() {
        lock.lock();
        try {
            while (product <= 0) { //避免虚假问题，使用while 而不是if
                System.out.println("缺货了。。。");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "在卖货" + --product);
           condition.signalAll();
        } finally {
           lock.unlock();

        }


    }

}

//生产者
class Productor implements Runnable{
    private Clerk clerk;
    public Productor(Clerk clerk){
        this.clerk=clerk;
    }

    @Override
    public void run() {
        for (int i=0;i<20;i++){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.get();
        }
    }
}

//consumer
class Consumer implements Runnable{

    private Clerk clerk;
    public Consumer(Clerk clerk){
        this.clerk=clerk;
    }

    @Override
    public void run() {
        for (int i=0;i<20;i++){
            clerk.sale();
        }
    }
}
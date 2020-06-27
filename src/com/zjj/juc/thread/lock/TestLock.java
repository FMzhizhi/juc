package com.zjj.juc.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/27 14:49
 */
public class TestLock {
    public static void main(String[] args) {
        Ticket ticket=new Ticket();
        new Thread(ticket,"1号窗口：").start();
        new Thread(ticket,"2号窗口：").start();
        new Thread(ticket,"3号窗口：").start();
    }
}
class Ticket implements Runnable{

    private int ticket=1000;
    private Lock lock=new ReentrantLock();

    @Override
    public void run() {
        while (true){
            lock.lock();
            try {

                if (ticket>0){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"还剩余票数："+--ticket);
                }
            } finally {
                lock.unlock();
            }
        }

    }
}

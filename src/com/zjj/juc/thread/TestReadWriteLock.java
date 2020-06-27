package com.zjj.juc.thread;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/27 17:29
 */
public class TestReadWriteLock {
    public static void main(String[] args) {
        ReadWriteLockDemo demo=new ReadWriteLockDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.set((int) (Math.random()*100));
            }
        },"Write").start();

        for (int i=0;i<100;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.get();
                }
            }).start();
        }

    }
}

class ReadWriteLockDemo {
    private int number = 0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    //读
    public void get() {
        lock.readLock().lock();
        try {

            System.out.println(Thread.currentThread().getName() + " : " + number);
        } finally {
            lock.readLock().unlock();
        }
    }

    //读
    public void set(int number) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName());
            this.number = number;
        } finally {
            lock.writeLock().unlock();
        }
    }
}
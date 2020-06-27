package com.zjj.juc.Atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/26 22:49
 */
public class TestAtomicDemo {
    public static void main(String[] args) {
        AtomicDemo ad = new AtomicDemo();

        for (int i = 0; i < 10; i++) {
            new Thread(ad).start();
        }
    }
}
class AtomicDemo implements Runnable{

	//private volatile int serialNumber = 0;

  private AtomicInteger serialNumber = new AtomicInteger(0);


    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }

        System.out.println(Thread.currentThread().getName()+":"+getSerialNumber());
    }

    public int getSerialNumber(){
        return serialNumber.getAndIncrement();
    }


}


package com.zjj.juc.countDownLatch;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/27 9:47
 */
public class TestCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch =new CountDownLatch(5);
        LatchDemo l=new LatchDemo(latch);
        Instant start = Instant.now();
        for (int i=0;i<5;i++){
            new Thread(l).start();
        }
        latch.await(3, TimeUnit.MINUTES);
        Instant end = Instant.now();
        System.out.println("花费的时间："+Duration.between(start,end).toMillis());
    }
}
class LatchDemo implements Runnable{
   private CountDownLatch latch;
    public LatchDemo(CountDownLatch latch) {
        this.latch = latch;
    }
    @Override
    public void run() {
        synchronized (this){
            try {
                for (int i=0;i<10;i++){
                    if (i%2==0){
                        System.out.println(Thread.currentThread().getName()+"="+i);
                    }
                }
            } finally {
                latch.countDown();

            }

        }
    }
}

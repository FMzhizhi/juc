package com.zjj.juc.thread;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/27 15:09
 */
public class TestProductAndConsumer {
    public static void main(String[] args) {
        /*Clerk clerk=new Clerk();
        Productor pro=new Productor(clerk);
        Consumer con=new Consumer(clerk);
        new Thread(pro,"生产者A").start();
        new Thread(con,"消费者B").start();
        new Thread(pro,"生产者C").start();
        new Thread(con,"消费者D").start();*/

    }
}

/*
//店员
class Clerk {
    private int product = 0;

    //进货
    public synchronized void get() {
        while (product >= 1) {
            System.out.println("已经满货了。。");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            System.out.println(Thread.currentThread().getName() + "在进货" + ++product);
            this.notifyAll();

    }

    //卖货
    public synchronized void sale() {
        while (product <= 0) { //避免虚假问题，使用while 而不是if
            System.out.println("缺货了。。。");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            System.out.println(Thread.currentThread().getName() + "在卖货" + --product);
            this.notifyAll();


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
}*/

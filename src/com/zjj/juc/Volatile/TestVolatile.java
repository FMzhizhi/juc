package com.zjj.juc.Volatile;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/26 21:05
 */
public class TestVolatile {

    /*
     * 一、volatile 关键字：当多个线程进行操作共享数据时，可以保证内存中的数据可见。
     * 					  相较于 synchronized 是一种较为轻量级的同步策略。
     *
     * 注意：
     * 1. volatile 不具备“互斥性”
     * 2. volatile 不能保证变量的“原子性”
     */
    public static void main(String[] args) {
        ThreadDemo demo = new ThreadDemo();
        new Thread(demo).start();
        while (true){
            if (demo.isFlag()){
                System.out.println("------------");
                break;
            }
        }
    }
}
class ThreadDemo implements Runnable{

   private volatile boolean flag=false;
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag=true;
        System.out.println("flag="+isFlag());

    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

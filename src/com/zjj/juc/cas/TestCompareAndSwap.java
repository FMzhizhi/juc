package com.zjj.juc.cas;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/27 8:56
 */
public class TestCompareAndSwap {
    public static void main(String[] args) {
        final CompareAndSwap cas = new CompareAndSwap();
        for (int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int expectedValue = cas.getValue();
                    boolean b = cas.compareAndSet(expectedValue, (int)(Math.random() * 101));
                    System.out.println(b);

                }
            }).start();
        }
    }
}
class CompareAndSwap{
    private int value;

    public synchronized int getValue(){
        return value;
    }

    public synchronized int compareAndSwap(int expectedValue, int newValue){
        int oldValue=value;
        if (oldValue==expectedValue){
            this.value=newValue;
        }
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectedValue, int newValue){
        return expectedValue == compareAndSwap(expectedValue,newValue);

    }
}

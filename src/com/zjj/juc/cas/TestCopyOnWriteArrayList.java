package com.zjj.juc.cas;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/27 9:26
 */
public class TestCopyOnWriteArrayList {
    public static void main(String[] args) {
        HelloThread ht=new HelloThread();
        for (int i=0;i<10;i++){
            new Thread(ht).start();
        }
    }
}
class HelloThread implements Runnable{


    /*
     * CopyOnWriteArrayList/CopyOnWriteArraySet : “写入并复制”
     * 添加操作多时，效率低，因为每次添加时都会进行复制，开销非常的大。并发迭代操作多时可以选择。
     */
  //private static List<String> list= Collections.synchronizedList(new ArrayList<>());
  private static List<String> list= new CopyOnWriteArrayList<>();

  static {
      list.add("AA");
      list.add("BB");
      list.add("CC");
  }

    @Override
    public void run() {
      //迭代和list操作同一个数据源会有并发异常
        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
            list.add("FFF");
        }
    }
}
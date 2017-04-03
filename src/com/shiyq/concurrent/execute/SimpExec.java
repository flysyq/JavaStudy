/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shiyq.concurrent.execute;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author flysy
 */
class SimpExec {

    public static void main(String[] args) {
        CountDownLatch cd1 = new CountDownLatch(5);
        CountDownLatch cd2 = new CountDownLatch(5);
        CountDownLatch cd3 = new CountDownLatch(5);
        CountDownLatch cd4 = new CountDownLatch(5);

        ExecutorService es = Executors.newFixedThreadPool(2);

        System.out.println("Starting");

        es.execute(new MyThread(cd1, "A"));
        es.execute(new MyThread(cd2, "B"));
        es.execute(new MyThread(cd3, "C"));
        es.execute(new MyThread(cd4, "D"));

        try {
            cd1.await();
            cd2.await();
            cd3.await();
            cd4.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(SimpExec.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        es.shutdown();
        System.out.println("Done");
    }
}

class MyThread implements Runnable{
    String name;
    CountDownLatch latch;
    
    MyThread(CountDownLatch c,String n){
        latch = c;
        name = n;
        
        //new Thread(this).start();
    }

    @Override
    public void run() {
        for(int i=0;i<5;i++){
            System.out.println( name + " : " + i);
            latch.countDown();
        }
    }
}

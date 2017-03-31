/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shiyq.concurrent.countdown;


import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author flysy
 */
class CDLDemo {
    
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(5);
        
        System.out.println("Starting");
        new MyThread(cdl);
        
        try{
            cdl.await();
        }catch(InterruptedException ex){
            Logger.getLogger(CDLDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Done");
    }
}

class MyThread implements Runnable{
    CountDownLatch latch;
    
    MyThread(CountDownLatch c){
        latch = c;
        new Thread(this).start();
    }
    
    public void run(){
        for(int i=0;i<5;i++){
            System.out.println("i: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            latch.countDown();
        }
    }
}

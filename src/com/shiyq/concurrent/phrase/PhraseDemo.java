/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shiyq.concurrent.phrase;

import java.util.concurrent.Phaser;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author flysy
 */
class PhraseDemo {
    public static void main(String[] args) {
        Phaser phsr = new Phaser(1);
        
        int curPhase;
        
        System.out.println("Starting");
        
        new MyThread(phsr, "A");
        new MyThread(phsr, "B");
        new MyThread(phsr, "C");
        
        curPhase = phsr.getPhase();
        phsr.arriveAndAwaitAdvance();
        System.out.println("Phase " + curPhase + " Complete.");
        
        curPhase = phsr.getPhase();
        phsr.arriveAndAwaitAdvance();
        System.out.println("Phase " + curPhase + " Complete.");
        
        curPhase = phsr.getPhase();
        phsr.arriveAndAwaitAdvance();
        System.out.println("Phase " + curPhase + " Complete.");
        
        phsr.arriveAndDeregister();
        if(phsr.isTerminated()){
            System.out.println("The Phaser is Terminated");
        }
    }
}

class MyThread implements Runnable{
    Phaser phsr;
    String name;
    
    MyThread(Phaser p, String n){
        phsr = p;
        name = n;
        phsr.register();
        new Thread(this).start();
    }
    
    public void run(){
        System.out.println("Thread " + name + " Beginning Phase One");
        phsr.arriveAndAwaitAdvance();
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         System.out.println("Thread " + name + " Beginning Phase Two");
        phsr.arriveAndAwaitAdvance();
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
        }
         System.out.println("Thread " + name + " Beginning Phase Three");
        phsr.arriveAndDeregister();
               
    }
}

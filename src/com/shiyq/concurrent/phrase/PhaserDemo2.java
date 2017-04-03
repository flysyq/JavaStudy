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
class PhaserDemo2 {
    public static void main(String[] args) {
        MyPhaser phsr = new MyPhaser(1,4);
        
        System.out.println("Starting \n");
        
        new MyThread2(phsr, "A");
        new MyThread2(phsr, "B");
        new MyThread2(phsr, "C");
        
        while(!phsr.isTerminated()){
            phsr.arriveAndAwaitAdvance();
        }
        
        System.out.println("The Phaser is terminated.");
    }
}

class MyPhaser extends Phaser{
    int numPhases;
    
    MyPhaser(int parties, int phaseCount){
        super(parties);
        numPhases = phaseCount - 1;
    }
    
    protected boolean onAdvance(int p, int regParties){
        System.out.println("Phase " +p + " Completed.\n" );
        
        if(p == numPhases || regParties == 0){
            return true;
        }
        
        return false;
    }
}

class MyThread2 implements Runnable{
    Phaser phsr;
    String name;
    
    MyThread2(Phaser p, String n){
        phsr = p;
        name = n;
        
        phsr.register();
        new Thread(this).start();
    }
    
    public void run(){
        while(!phsr.isTerminated()){
            System.out.println("Thread " + name +" Beginning Phase " + phsr.getPhase());
            phsr.arriveAndAwaitAdvance();
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}


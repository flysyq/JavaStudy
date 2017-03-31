/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shiyq.concurrent.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author flysy
 */
class BarDemo {

    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(3, new BarAction());

        System.out.println("Starting");
        new MyThread(cb, "A");
        new MyThread(cb, "B");
        new MyThread(cb, "C");
        new MyThread(cb, "D");
        new MyThread(cb, "E");
        new MyThread(cb, "F");
        new MyThread(cb, "G");
        new MyThread(cb, "H");
        new MyThread(cb, "I");
        new MyThread(cb, "J");
        new MyThread(cb, "K");
        new MyThread(cb, "L");

    }
}

class MyThread implements Runnable {

    CyclicBarrier cbar;

    String name;

    MyThread(CyclicBarrier c, String n) {
        cbar = c;
        name = n;
        new Thread(this).start();
    }

    public void run() {
        System.out.println(name);

        try {
            System.out.println(Thread.currentThread().getName()+" 即将完成，共有 " + cbar.getNumberWaiting()+" 在等待");
            cbar.await();
        } catch (InterruptedException | BrokenBarrierException ex) {
            Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class BarAction implements Runnable {

    public void run() {
        System.out.println("Barrier Reached");
    }
}

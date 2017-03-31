/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shiyq.concurrent.exchanger;

import java.util.concurrent.Exchanger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author flysy
 */
class ExgrDemo {
    public static void main(String[] args) {
        Exchanger<String> exgr = new Exchanger<String>();
        new UseString(exgr);
        new MakeString(exgr);
    }
}

class MakeString implements Runnable{
    Exchanger<String> ex;
    String str;
    
    MakeString(Exchanger<String> c){
        ex = c;
        str = new String();
        new Thread(this).start();
    }
    
    public void run(){
        char ch = 'A';
        for(int i=0;i<3;i++){
            for(int j=0;j<5;j++)
                str += ch++;            
            try{
                str = ex.exchange(str);
                System.out.println("Make got: "+str);
            } catch (InterruptedException ex) {
                Logger.getLogger(MakeString.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

class UseString implements Runnable{
    Exchanger<String> ex;
    String str;
    
    UseString(Exchanger<String> c){
        ex = c;
        new Thread(this).start();
    }
    
    public void run(){
  
        for(int i=0;i<3;i++){
            
            try{
                str = ex.exchange(new String());
                System.out.println("Use Got : " +str);
            } catch (InterruptedException ex) {
                Logger.getLogger(MakeString.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

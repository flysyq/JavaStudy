/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shiyq.concurrent.forkjoin;

/**
 *
 * @author flysy
 */
public class SqrtDemo {
    public static void main(String[] args) {
        long beginT, endT;
        double[] nums = new double[100000000];
        for(int i=0;i<nums.length;i++){
            nums[i] = (double)i;
        }
        System.out.println("A portion of the original sequence:");

        for (int i = 0; i < 100; i++) {
            System.out.print(nums[i] +  " ");
        }
        System.out.println("\n");
         beginT = System.nanoTime();
        
        for(int i=0;i<nums.length;i++){
            nums[i]=Math.sqrt(nums[i]);
        }
        
        endT = System.nanoTime();
        System.out.println("Elapsed time: " + (endT - beginT) + " ns");
        System.out.println("A portion of the transoformed sequence "
                + " (to four decimal places): ");
        for (int i = 0; i < 100; i++) {
            System.out.format("%4f ", nums[i]);
        }
        System.out.println();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shiyq.concurrent.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 *
 * @author flysy
 */
class SqrtTransform extends RecursiveAction {

    final int seqThreshold = 1000;
    double[] data;
    int start, end;

    SqrtTransform(double[] vals, int s, int e) {
        data = vals;
        start = s;
        end = e;
    }

    @Override
    protected void compute() {

        if (end - start < seqThreshold) {
            for (int i = start; i < end; i++) {
                data[i] = Math.sqrt(data[i]);
            }
        } else {
            int middle = (start + end) / 2;
            invokeAll(new SqrtTransform(data, start, middle), new SqrtTransform(data, middle, end));
        }
    }
}

class ForkJoinDemo {

    public static void main(String[] args) {
        long beginT, endT;
        ForkJoinPool fjp = new ForkJoinPool();
        double[] nums = new double[100000000];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = (double) i;
        }

        System.out.println("A portion of the original sequence:");

        for (int i = 0; i < 100; i++) {
            System.out.print(nums[i] +  " ");
        }
        System.out.println("\n");

        SqrtTransform task = new SqrtTransform(nums, 0, nums.length);
        beginT = System.nanoTime();
        fjp.invoke(task);
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

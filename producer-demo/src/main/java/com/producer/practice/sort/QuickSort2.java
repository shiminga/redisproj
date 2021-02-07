package com.producer.practice.sort;

import java.util.Random;

public class QuickSort2 {

    public static void main(String[] args) {
        int[] nums={6,5,5,7,8,4,5,456,5476,344,222,90};
        quickSort(nums,0,nums.length-1);
        System.out.println("2344");
    }

    public static void quickSort(int[] nums,int l,int r){
        if(l>=r){
            return;
        }
        int i=l,j=r;

        int tmp=nums[r];
        //为什么先r--再i++
        while(i<j){
            while(i<j&&nums[i]<=tmp){
                i++;
            }
            nums[j]=nums[i];
            while(i<j&&nums[j]>=tmp){
                j--;
            }
            nums[i]=nums[j];

        }
        nums[i]=tmp;

        quickSort(nums,l,i-1);
        quickSort(nums,i+1,r);
    }



    public static void swap(int[] nums,int i,int j){
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }


}

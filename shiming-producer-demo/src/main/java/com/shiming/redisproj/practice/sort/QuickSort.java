package com.shiming.redisproj.practice.sort;

import com.sun.mail.imap.Rights;

import java.util.Random;

public class QuickSort {

    public static void main(String[] args) {
        int[] nums={5,5,3,4,6,23,8,9,6};
        quickSort(nums,0,nums.length-1);
        System.out.println("2344");
    }

    public static void quickSort(int[] nums,int l,int r){
            if(l>=r){return;}

            int i=l,j=r-1;
            int pivotIndex=l+new Random().nextInt(r-l+1);

            //把pivotIndex换到最右边
            swap(nums,pivotIndex,r);

            while(i<=j){
                if(nums[i]<=nums[r]){
                    i++;
                }else{
                    swap(nums,i,j);
                    j--;
                }
            }
            swap(nums,i,r);
            quickSort(nums,l,i-1);
            quickSort(nums,i+1,r);
    }



    public static void swap(int[] nums,int i,int j){
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }


}

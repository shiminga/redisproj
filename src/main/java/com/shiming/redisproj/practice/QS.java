package com.shiming.redisproj.practice;

public class QS {

    public static void main(String[] args) {
            int[] nums={2,4,5,6,6,6,9,4,4,3,2,99,7,8};
        quickSort(nums,0,nums.length-1);
        System.out.println("23r3");
    }

    public static void quickSort(int[] nums, int l,int r){
        if(l>=r){return;}
        int pivot=nums[l],i=l,j=r;

        while(i<j){
            while(i<j&&nums[j]>=pivot){
                j--;
            }
            nums[i]=nums[j];
            while(i<j&&nums[i]<=pivot){
                i++;
            }
            nums[j]=nums[i];
        }
        nums[i]=pivot;
        quickSort(nums,l,i-1);
        quickSort(nums,i+1,r);
    }
}

package com.shiming.redisproj.practice.sort;

public class MergeSort {

    public static void main(String[] args) {
        int[] nums={4,5,2,2,333,4,2,7,8,0,666,54,54};
        mergeSort(nums,0,nums.length-1);


        System.out.println("23r3r");
    }

    public static void mergeSort(int[] nums,int l,int r){
        if(l>=r){
            return;
        }

        int mid=(l+r)>>>1;
        mergeSort(nums,l,mid);
        mergeSort(nums,mid+1,r);

        int[] tmp=new int[r-l+1];
        int i=l,j=mid+1,index=0;
        while(i<=mid||j<=r){
            if(i>mid){
                tmp[index++]=nums[j++];
            }else if(j>r){
                tmp[index++]=nums[i++];
            }else{
                tmp[index++]=nums[i]>nums[j]?nums[j++]:nums[i++];
            }
        }

        for(int k=0;k<tmp.length;k++){
            nums[l+k]=tmp[k];
        }
    }
}

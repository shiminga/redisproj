package com.producer.practice;

public class QS {

    public static void main(String[] args) {
            int[] nums={2,4,5,6,6,6,9,4,4,3,2,99,7,8};
        quickSort(nums,0,nums.length-1);
        System.out.println("23r3");

        maxTurbulenceSize(new int[]{4,12,8});
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

    public static int maxTurbulenceSize(int[] arr) {
        if(arr.length==1){return 1;}
        if(arr.length==2){return arr[0]!=arr[1]?2:1;}


        //4,2,1
        int count=0,max=count,state,lastState=0;

        for(int i=0;i<arr.length-1;i++){
            state=arr[i]<arr[i+1]?4:(arr[i]==arr[i+1]?2:1);

            if((i==0&&state!=2)||state+lastState==5){
                count++;
            }else{
                count=state==2?0:1;;
            }
            max=Math.max(max,count+1);
            lastState=state;
        }
        return max;
    }
}

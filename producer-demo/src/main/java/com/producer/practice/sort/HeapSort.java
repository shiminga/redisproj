package com.producer.practice.sort;

import java.util.Arrays;

public class HeapSort {


    public static void main(String[] args) {
        int[] nums=new int[]{1,4,6,3,7,9,2,4,56,67,45,67,34,34,34563,322,4,563,3};
        sort(nums);
        System.out.println(2);
    }

    private static void heapsort(int[] nums) {
        /**
         * 构建堆
         */
        for(int i=nums.length/2-1;i>=0;i--){
            adjustHeap(nums,i,nums.length);
        }
        /**
         * 每一轮循环排号一个数
         */
        for(int i=nums.length-1;i>0;i--){
            int tmp=nums[0];
            nums[0]=nums[i];
            nums[i]=tmp;
            adjustHeap(nums,0,i);
        }

    }

    private static void adjustHeap(int[] nums, int i, int length) {
        int tmp=nums[i];
        for(int k=i*2+1;k<length;k=k*2+1){
            if(k+1<length&&nums[k+1]>nums[k]){
                k++;
            }
            if(nums[k]>tmp){
                nums[i]=nums[k];
                i=k;
            }else{
                break;
            }
        }
        nums[i]=tmp;
    }


    public static int[] sort(int[] sourceArray)  {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int len = arr.length;

        buildMaxHeap(arr, len);

        for (int i = len - 1; i > 0; i--) {
            swap(arr, 0, i);
            len--;
            heapify(arr, 0, len);
        }
        return arr;
    }

    private static void buildMaxHeap(int[] arr, int len) {
        for (int i = (int) Math.floor(len / 2); i >= 0; i--) {
            heapify(arr, i, len);
        }
    }

    private static void heapify(int[] arr, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < len && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, largest, len);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}

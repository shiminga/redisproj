package com.producer.practice;

import java.util.*;

public class QS {

    public static void main(String[] args) {
//            int[] nums={2,4,5,6,6,6,9,4,4,3,2,99,7,8};
//        quickSort(nums,0,nums.length-1);
//        System.out.println("23r3");
//
//        maxTurbulenceSize(new int[]{4,12,8});
//
//        validateStackSequences(new int[]{1,2,3,4,5},new int[]{4,5,3,2,1});

//        longestSubarray(new int[]{88,10},10);
        reverseBetween(new ListNode(3,new ListNode(5,null)),2,4);
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

    public static boolean validateStackSequences(int[] pushed, int[] popped) {

        Stack<Integer> stack=new Stack<>();
        int len=pushed.length;

        int i=0,j=0;

        while(i<len||j<len){
            if(i<len&&(stack.isEmpty()||stack.peek()!=popped[j])){
                stack.push(pushed[i++]);
            }else{
                if(stack.isEmpty()){break;}
                if(stack.peek()==popped[j]){
                    stack.pop();
                    j++;
                }else{
                    break;
                }
            }
        }
        return stack.isEmpty()&&i==len-1&&j==len-1;
    }

    /**
     * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
     *
     * 如果不存在满足条件的子数组，则返回 0 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [8,2,4,7], limit = 4
     * 输出：2
     * 解释：所有子数组如下：
     * [8] 最大绝对差 |8-8| = 0 <= 4.
     * [8,2] 最大绝对差 |8-2| = 6 > 4.
     * [8,2,4] 最大绝对差 |8-2| = 6 > 4.
     * [8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
     * [2] 最大绝对差 |2-2| = 0 <= 4.
     * [2,4] 最大绝对差 |2-4| = 2 <= 4.
     * [2,4,7] 最大绝对差 |2-7| = 5 > 4.
     * [4] 最大绝对差 |4-4| = 0 <= 4.
     * [4,7] 最大绝对差 |4-7| = 3 <= 4.
     * [7] 最大绝对差 |7-7| = 0 <= 4.
     * 因此，满足题意的最长子数组的长度为 2 。
     * 示例 2：
     *
     * 输入：nums = [10,1,2,4,7,2], limit = 5
     * 输出：4
     * 解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
     * 示例 3：
     *
     * 输入：nums = [4,2,2,2,4,4,2,2], limit = 0
     * 输出：3
     *  
     *
     * 提示：
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 0 <= limit <= 10^9
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param limit
     * @return
     */
    public static int longestSubarray(int[] nums, int limit) {
        if(nums.length==1){return 1;}

        int left=0,right=1,maxLen=0;

        PriorityQueue<Integer> maxQueue=new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer i1,Integer i2){
                return i2.compareTo(i1);
            }
        });
        PriorityQueue<Integer> minQueue=new PriorityQueue<>();

        maxQueue.add(nums[left]);
        maxQueue.add(nums[right]);
        minQueue.add(nums[left]);
        minQueue.add(nums[right]);

        do{
            if(right==left){
                if(++right>nums.length-1){
                    continue;
                }
                maxQueue.add(nums[right]);
                minQueue.add(nums[right]);
            }

            if(maxQueue.peek()-minQueue.peek()<=limit){
                maxLen=Math.max(maxLen,right-left+1);

                if(++right>nums.length-1){continue;}
                maxQueue.add(nums[right]);
                minQueue.add(nums[right]);
            }else{
                maxQueue.remove(nums[left]);
                minQueue.remove(nums[left++]);
            }
        }while(right<nums.length);

        return maxLen;
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode node=head,pre=null,leftNode=null,rightNode=null,next=null;

        while(node!=null){
            if(node.val==left){leftNode=node;}
            if(node.val==right){rightNode=node;next=node.next;}
            if(leftNode==null){pre=node;}
            node=node.next;
        }

        if(leftNode==null){return head;}

        reverse(leftNode,rightNode);
        pre.next=rightNode;
        leftNode.next=next;
        return head==leftNode?rightNode:head;
    }

    public static ListNode reverse(ListNode head,ListNode rightNode){
        ListNode pre=null,cur=head,next=null;

        while(cur!=null){
            next=cur.next;

            cur.next=pre;
            if(cur==rightNode){return cur;}
            pre=cur;
            cur=next;
        }
        return pre;
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
}

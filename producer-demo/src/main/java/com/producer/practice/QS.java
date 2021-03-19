package com.producer.practice;

import java.util.*;

public class QS {

    public static void main(String[] args) {
        Node root=new Node(4,new Node(2,new Node(1),new Node(3)),new Node(5));
        root=new Node(1);

        Node dd=treeToDoublyList(root);

        System.out.println(dd);

//        long startTime=System.currentTimeMillis();
//        int i=0;
//        while(i<(1<<31-1)){
//            System.out.println(findNthDigit(i++));
//        }
//        System.out.println(System.currentTimeMillis()-startTime+"ms");

//        System.out.println(findNthDigit(2000000000));

        spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
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

    /**
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。

     * 为了让您更好地理解问题，以下面的二叉搜索树为例：

     * 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
     *
     * 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。

     * 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
     *
     *  
     *
     * 注意：本题与主站 426 题相同：
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    static LinkedList<Node> preOrderList=new LinkedList<>();
    public static Node treeToDoublyList(Node root) {
        if(root==null){return null;}
        preOrder(root);

        Node head=preOrderList.poll(),next=preOrderList.poll(),node=head;
        while(node!=null){
            if(next==null){break;}

            node.right=next;
            next.left=node;

            node=next;
            next=preOrderList.poll();
        }
        if(head!=node){
            node.right=head;
            head.left=node;
        }

        return head;
    }

    public static void preOrder(Node root){
        if(root==null){
            return ;
        }
        preOrder(root.left);
        preOrderList.add(root);
        preOrder(root.right);
    }
    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };

    /**
     * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
     *
     * 请写一个函数，求任意第n位对应的数字。
     * 示例 1：
     *
     * 输入：n = 3
     * 输出：3
     * 示例 2：
     *
     * 输入：n = 11
     * 输出：0
     * 限制：
     * 0 <= n < 2^31
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public static int findNthDigit(int n) {
        if(n==0){return 0;}
        int p=1,seg=(int)(9*Math.pow(10,p-1)*p);
        while(n>seg){
            n-=seg;
            p++;
            seg=(int)(9*Math.pow(10,p-1)*p);
        }
        int mod=n%p;//该数中第mod位数字
        n=(n+p-1)/p+(int)Math.pow(10,p-1)-1;//该组的第n个数
        if(mod==0){
            return n%10;
        }else{
            return (int)(n/Math.pow(10,p-mod))%10;
        }
    }

    public static int findNthDigit2(int n) {
        int digit = 1;
        long start = 1;
        long count = 9;
        while (n > count) { // 1.
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit; // 2.
        return Long.toString(num).charAt((n - 1) % digit) - '0'; // 3.
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        int row=matrix.length,col=matrix[0].length;

        List<Integer> res=new ArrayList<>();
        boolean[][] used=new boolean[row][col];

        int len=row*col,i=0,x=0,y=0,direct=1;
        while(res.size()<len){
            res.add(matrix[x][y]);
            used[x][y]=true;
            switch(direct){
                case 1:
                    if(x+1<col&&!used[x+1][y]){
                        ++x;
                    }else{
                        direct=2;y++;
                    }
                    break;
                case 2:
                    if(y+1<row&&!used[x][y+1]){
                        ++y;
                    }else{
                        direct=3;x--;
                    }
                    break;
                case 3:
                    if(x-1>=0&&!used[x-1][y]){
                        --x;
                    }else{
                        direct=4;y--;
                    }
                    break;
                case 4:
                    if(y-1>=0&&!used[x][y-1]){
                        --y;
                    }else{
                        direct=1;x++;
                    }
                    break;
            }
        }
        return res;
    }
}

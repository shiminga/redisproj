package com.producer.practice;

import jdk.nashorn.internal.ir.ReturnNode;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class QS {

    public static void main(String[] args) {
//        Node root=new Node(4,new Node(2,new Node(1),new Node(3)),new Node(5));
//        root=new Node(1);
//
//        Node dd=treeToDoublyList(root);
//
//        System.out.println(dd);

//        long startTime=System.currentTimeMillis();
//        int i=0;
//        while(i<(1<<31-1)){
//            System.out.println(findNthDigit(i++));
//        }
//        System.out.println(System.currentTimeMillis()-startTime+"ms");

//        System.out.println(findNthDigit(2000000000));

//        spiralOrder();

//        int[][] arr=new int[][]{{1,2,3},{4,5,6},{7,8,9}};
//        System.out.println(arr instanceof int[][]);
//
//        System.out.println(oneEditAway("baaa","b"));
//        spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
////        longestSubarray(new int[]{88,10},10);
//        reverseBetween(new ListNode(3,new ListNode(5,null)),2,4);
//        int[][] nums=new int[][]{{0,1,0},{1,1,0},{0,0,0}};
//        System.out.println(shortestPathBinaryMatrix2(nums));


//        clumsy(9);
//        Integer[] nums={10,5,-3,3,2,null,11,3,-2,null,1};
//        RemoveDuplicates.TreeNode r=new RemoveDuplicates.TreeNode(nums[0]);
//        buildTree(nums,0,r);
//
//        pathSum(r,0);
//        System.out.println(res);

        int[] nums=new int[200];
        for(int i=1;i<=200;i++){
            nums[i-1]=i;
        }
        nums=new int[]{2,1,3};

        combinationSum4(nums,35);
    }

    /**
     * 组合问题公式
     *
     *
     * dp[i] += dp[i-num]
     * True、False问题公式
     *
     *
     * dp[i] = dp[i] or dp[i-num]
     * 最大最小问题公式
     *
     *
     * dp[i] = min(dp[i], dp[i-num]+1)或者dp[i] = max(dp[i], dp[i-num]+1)
     * 以上三组公式是解决对应问题的核心公式。
     *
     * 当然拿到问题后，需要做到以下几个步骤：
     * 1.分析是否为背包问题。
     * 2.是以上三种背包问题中的哪一种。
     * 3.是0-1背包问题还是完全背包问题。也就是题目给的nums数组中的元素是否可以重复使用。
     * 4.如果是组合问题，是否需要考虑元素之间的顺序。需要考虑顺序有顺序的解法，不需要考虑顺序又有对应的解法。
     *
     * 接下来讲一下背包问题的判定
     * 背包问题具备的特征：给定一个target，target可以是数字也可以是字符串，再给定一个数组nums，nums中装的可能是数字，也可能是字符串，问：能否使用nums中的元素做各种排列组合得到target。
     *
     * 背包问题技巧：
     * 1.如果是0-1背包，即数组中的元素不可重复使用，nums放在外循环，target在内循环，且内循环倒序；
     *
     *
     * for num in nums:
     *     for i in range(target, nums-1, -1):
     * 2.如果是完全背包，即数组中的元素可重复使用，nums放在外循环，target在内循环。且内循环正序。
     *
     *
     * for num in nums:
     *     for i in range(nums, target+1):
     * 3.如果组合问题需考虑元素之间的顺序，需将target放在外循环，将nums放在内循环。
     *
     *
     * for i in range(1, target+1):
     *     for num in nums:
     *
     * 作者：Jackie1995
     * 链接：https://leetcode-cn.com/problems/combination-sum-iv/solution/xi-wang-yong-yi-chong-gui-lu-gao-ding-bei-bao-wen-/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @param target
     * @return
     */
    static int[] ans;
    public static int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        ans=new int[target+1];
        Arrays.fill(ans,-1);
        ans[0]=1;
        return helper(nums,target);
    }

    public static int helper(int[] nums, int target){
        if(ans[target]==-1){
            int res=0;
            for(int i=0;i<nums.length;i++){
                if(target<nums[i]){break;}
                res+=helper(nums,target-nums[i]);
            }
            ans[target]=res;
        }
        return ans[target];
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
                count=state==2?0:1;
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

    void swap(String first, String second){
        String tmp=first;
        first=second;
        second=tmp;
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

    public static boolean oneEditAway(String first, String second) {
        return oneEdit(first,second,2);
    }

    public static boolean oneEdit(String first, String second,int threshold){
        if(first==null||second==null||first.length()-second.length()>threshold){
            return false;
        }

        if(first.length()<second.length()){
            String tmp=first;
            first=second;
            second=tmp;
        }

        int index1=0,index2=0;
        while(index1<first.length()&&index2<second.length()){
            while(index1<first.length()&&first.charAt(index1)!=second.charAt(index2)){
                index1++;
                if(index1-index2>threshold){
                    System.out.println(index1-index2);
                    return false;
                }
            }
            index1++;
            index2++;
        }
        System.out.println(index1-index2);
        return index2+threshold>=first.length()||(index1==first.length()&&index2==second.length());
    }

    /**
     * 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < aj。设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。
     *
     * 注意：n 的值小于15000。
     *
     * 示例1:
     *
     * 输入: [1, 2, 3, 4]
     *
     * 输出: False
     *
     * 解释: 序列中不存在132模式的子序列。
     * 示例 2:
     *
     * 输入: [3, 1, 4, 2]
     *
     * 输出: True
     *
     * 解释: 序列中有 1 个132模式的子序列： [1, 4, 2].
     * 示例 3:
     *
     * 输入: [-1, 3, 2, 0]
     *
     * 输出: True
     *
     * 解释: 序列中有 3 个132模式的的子序列: [-1, 3, 2], [-1, 3, 0] 和 [-1, 2, 0].
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/132-pattern
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean find132pattern(int[] nums) {
        return false;
    }

    /**
     * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
     *
     * 返回同样按升序排列的结果链表。
     * 示例 1：
     * 输入：head = [1,1,2]
     * 输出：[1,2]
     * 示例 2：
     * 输入：head = [1,1,2,3,3]
     * 输出：[1,2,3]
     * 提示：
     * 链表中节点数目在范围 [0, 300] 内
     * -100 <= Node.val <= 100
     * 题目数据保证链表已经按升序排列
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode pre=null,returnHead=head;
        while(head!=null){
            if(pre!=null&&head.val==pre.val){
                head=head.next;
                pre.next=head;
            }else{
                pre=head;
                head=head.next;
            }
        }
        return returnHead;
    }

    /**
     * 给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
     *
     * 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求：
     *
     * 路径途经的所有单元格都的值都是 0 。
     * 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
     * 畅通路径的长度 是该路径途经的单元格总数。
     *
     *
     *
     * 示例 1：
     *
     *
     * 输入：grid = [[0,1],[1,0]]
     * 输出：2
     * 示例 2：
     *
     *
     * 输入：grid = [[0,0,0],[1,1,0],[1,1,0]]
     * 输出：4
     * 示例 3：
     *
     * 输入：grid = [[1,0,0],[1,1,0],[1,1,0]]
     * 输出：-1
     *
     *
     * 提示：
     *
     * n == grid.length
     * n == grid[i].length
     * 1 <= n <= 100
     * grid[i][j] 为 0 或 1
     */
    static int res=Integer.MAX_VALUE;
    static boolean[][] used;

    static int[][] rc={{0,1},{1,-1},{1,0},{1,1},{-1,-1},{-1,0},{-1,1},{0,-1}};
    public static int shortestPathBinaryMatrix(int[][] grid) {
//        if(grid==null||grid.length==0||grid[0][0]!=0){return -1;}
//        int row=grid.length,col=grid[0].length;
//        used=new boolean[row][col];
//
//        used[0][0]=true;
//        bfs(grid,0,0,col,row,1);
//        return res==Integer.MAX_VALUE?-1:res;
        int row=grid.length,col=grid[0].length;

        if(grid[0][0]==1||grid[row-1][col-1]==1){return -1;}

        LinkedList<int[]> queue=new LinkedList<>();
        int ans=0;
        queue.add(new int[]{0,0});
        ans++;
        while(!queue.isEmpty()){
            int size=queue.size();
            while(size-->0){
                int[] loc=queue.removeFirst();

                for(int[] dirs:rc){
                    int newX=loc[0]+dirs[0];
                    int newY=loc[1]+dirs[1];

                    if(newX==row-1&&newY==col-1){
                        return ans+1;
                    }

                    if(newX>=0 && newX < col && newY >= 0 && newY < row&&grid[newX][newY] == 0){
                        queue.add(new int[]{newX,newY});
                        grid[newX][newY]=1;
                    }
                }
            }
            ans++;
        }
        return -1;
    }

    static void bfs(int[][] grid,int x,int y,int X,int Y,int len){
        if(x==X-1&&y==Y-1){
            res=Math.min(res,len);
            System.out.println(res);
            return;
        }

        for (int[] i : rc) {
            int xi=x+i[0];
            int yj=y+i[1];
            if (xi>=0 && xi < X && yj >= 0 && yj < Y && !used[xi][yj] && grid[xi][yj] == 0) {
                used[xi][yj] = true;
                bfs(grid, xi, yj, X, Y, len + 1);
                used[xi][yj] = false;
            }
        }
    }

    private static int[][] directions = {{0,1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {-1, -1}, {-1, 0}, {-1, 1}};
    private static int row, col;
    public static int shortestPathBinaryMatrix2(int[][] grid) {
        row = grid.length;
        col = grid[0].length;
        if(grid[0][0] == 1 || grid[row - 1][col - 1] == 1) return -1;
        Queue<int[]> pos = new LinkedList<>();
        grid[0][0] = 1; // 直接用grid[i][j]记录从起点到这个点的最短路径长。按照题意 起点也有长度1
        pos.add(new int[]{0,0});
        while(!pos.isEmpty() && grid[row - 1][col - 1] == 0){ // 求最短路径 使用BFS
            int[] xy = pos.remove();
            int preLength = grid[xy[0]][xy[1]]; // 当前点的路径长度
            for(int i = 0; i < 8; i++){
                int newX = xy[0] + directions[i][0];
                int newY = xy[1] + directions[i][1];
                if(inGrid(newX, newY) && grid[newX][newY] == 0){
                    pos.add(new int[]{newX, newY});
                    grid[newX][newY] = preLength + 1; // 下一个点的路径长度要+1
                    System.out.println(pos.size());
                }
            }
        }
        List<int[]> queue=new LinkedList<>();
        return grid[row - 1][col - 1] == 0 ? -1 : grid[row - 1][col - 1]; // 如果最后终点的值还是0，说明没有到达
    }

    private static boolean inGrid(int x, int y){
        return x >= 0 && x < row && y >= 0 && y < col;
    }

    public static int clumsy(int N) {
        int res=0,ans=0;
        boolean flag=false;

        if(N>=4){
            res=(N--)*(N--)/(N--)+(N--);
            flag=true;
        }
        while(N>=4){
            res-=(N--)*(N--)/(N--);
            res+=N--;
        }

        if(N>=1){
            ans=N--;
        }
        if(N>=1){
            ans*=N--;
        }
        if(N>=1){
            ans/=N--;
        }
        return flag?(res-ans):ans;
    }


    static Map<Integer,Integer> count=new HashMap<>();
    public static int pathSum(RemoveDuplicates.TreeNode root, int sum) {
        res=0;
        if(root==null){return 0;}

        helper(root,0,sum);
        return res;
    }

    public static void helper(RemoveDuplicates.TreeNode root,int pathSum, int sum){
        if(root==null){return ;}
        int newSum=pathSum+root.val;

        res+=count.getOrDefault(newSum-sum,0);
        count.put(newSum,count.getOrDefault(newSum,0)+1);

        helper(root.left,newSum,sum);
        helper(root.right,newSum,sum);

        count.put(newSum,count.getOrDefault(newSum,0)-1);
    }

    static void buildTree(Integer[] nums,int index,RemoveDuplicates.TreeNode parent){
        if(2*index+1<nums.length&&nums[2*index+1]!=null){
            parent.left=new RemoveDuplicates.TreeNode(nums[2*index+1]);
            buildTree(nums,2*index+1,parent.left);
        }

        if(2*index+2<nums.length&&nums[2*index+2]!=null){
            parent.right=new RemoveDuplicates.TreeNode(nums[2*index+2]);
            buildTree(nums,2*index+2,parent.right);
        }
    }
}

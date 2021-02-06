package com.shiming.redisproj.practice;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.IntConsumer;


public class RemoveDuplicates {

    /**
     * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     *  
     *
     * 示例 1:
     *
     * 给定数组 nums = [1,1,2],
     *
     * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     * 示例 2:
     *
     * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
     *
     * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     *
     */
    public static int removeDuplicates(int[] nums) {
        if(nums==null||nums.length==0){
            return 0;
        }
        /**
         * 已经排好序的就不用map了，这里失策
         */
        HashMap<Integer,Integer> container=new HashMap();

        for(int i=0;i<nums.length;i++){
            if(!container.containsKey(nums[i])){
                container.put(nums[i],i);

                nums[container.size()-1]=nums[i];
            }
        }
        return container.size();
    }

    /**
     * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * 示例 1:
     *
     * 给定数组 nums = [1,1,2],
     *
     * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     * 示例 2:
     *
     * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
     *
     * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public static int removeDuplicates2(int[] nums) {
        if(nums==null||nums.length==0){
            return 0;
        }
        int v=nums[0],offset=0;
        for(int i=1;i<nums.length;i++){
            if(nums[i]==v){
                offset++;
            }else{
                v=nums[i];
                if(offset!=0){
                    nums[i-offset]=nums[i];
                }
            }
        }
        return nums.length-offset;
    }

























    /**
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     *
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     *
     * 示例 1:
     *
     * 给定 nums = [3,2,2,3], val = 3,
     *
     * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     * 示例 2:
     *
     * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
     *
     * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
     *
     * 注意这五个元素可为任意顺序。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     *
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        if(nums==null||nums.length==0){
            return 0;
        }
        int j=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=val){
                nums[j++]=nums[i];
            }
        }
        return j;
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 示例 1:
     *
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     *
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     *
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if(s==null||s.length()==0){
            return 0;
        }

        HashMap<Character,Integer> map =new HashMap();
        char[] cs=s.toCharArray();

        int max=0;
        int mapStartIndex=0;
        for(int i=0;i<cs.length;i++){
            if(map.containsKey(cs[i])){
                int tempRep=map.get(cs[i]);
                max=Math.max(max,i-mapStartIndex);

                removeLower(map,cs,mapStartIndex,tempRep);
                mapStartIndex=tempRep+1;
            }
            map.put(cs[i],i);
            if(i==cs.length-1){
                max=Math.max(max,i-mapStartIndex+1);
            }
        }
        return max;
    }

    /**
     * 用set维护一个不重复的窗口
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring3(String s) {
        int length=0;
        if(s==null||(length=s.length())==0||length==1){
            return length;
        }

        HashSet<Character> set =new HashSet<>();
        int res=Integer.MIN_VALUE;

        for(int i=0,j=0;j<length;j++){
            char c=s.charAt(j);
            while (set.contains(c)){
                set.remove(s.charAt(i++));
            }
            res=Math.max(res,j-i+1);
            set.add(c);
        }
        return res;
    }

    private static void removeLower(HashMap<Character,Integer> map, char[] cs,int start,int end) {
        while (start<=end){
            map.remove(cs[start++]);
        }
    }

    public static int lengthOfLongestSubstring2(String s) {
        if(s.length()==0){
            return 0;
        }
        int[] windows = new int[256];
        int l = 0,j=0;
        char[] chars = s.toCharArray();
        int res = Integer.MIN_VALUE;
        while(j<chars.length){
            if(windows[chars[j]]==0){
                windows[chars[j]]++;
                res = Math.max(j-l+1,res);
                j++;
            }else{
                while(chars[l]!=chars[j]){
                    windows[chars[l]]--;
                    l++;
                }
                windows[chars[l]]--;
                l++;
            }
        }
        return res;
    }

    /**
     * 用map来实现一个字符串的最长不重复子串
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring4(String s) {
        int length=0;
        if(s==null||(length=s.length())==0||length==1){
            return length;
        }

        HashMap<Character,Integer> map=new HashMap<>();
        int res=0;
        for(int i=0,j=0;i<s.length();i++){
            char c=s.charAt(i);
            if(map.containsKey(c)){
                j=Math.max(j,map.get(c)+1);
            }
            map.put(c,i);
            res=Math.max(res,i-j+1);
        }
        return res;
    }

    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2,2]
     * 示例 2:
     *
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[4,9]
     *  
     *
     * 说明：
     *
     * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
     * 我们可以不考虑输出结果的顺序。
     * 进阶：
     *
     * 如果给定的数组已经排好序呢？你将如何优化你的算法？
     * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
     * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        if(nums1==null||nums1.length==0||nums2==null||nums2.length==0){
            return new int[]{};
        }
        HashMap<Integer,Integer> map=new HashMap<>();
        for(Integer i:nums1){
            if(map.containsKey(i)){
                map.put(i,map.get(i)+1);
            }else {
                map.put(i,1);
            }
        }
        List<Integer> res=new ArrayList<>();
        for(Integer i:nums2){
            if(map.containsKey(i)){
                int cur=map.get(i);
                if(cur!=1){
                    map.put(i,cur-1);
                }else{
                    map.remove(i);
                }
                res.add(i);
            }
        }
        int[] returnRes=new int[res.size()];
        for(int i=0;i<res.size();i++){
            returnRes[i]=res.get(i);
        }
        return returnRes;
    }

    /**
     * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
     *
     * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
     *
     *  
     *
     * 例如，给定三角形：
     *
     * [
     *      [2],
     *     [3,4],
     *    [6,5,7],
     *   [4,1,8,3]
     * ]
     * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
     *
     *  
     *
     * 说明：
     *
     * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/triangle
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param triangle
     * @return
     */
    public static int minimumTotal1(List<List<Integer>> triangle) {

        if(triangle==null||triangle.size()==0){
            return 0;
        }
        int n=triangle.size();
        int[] container=new int[n+1];

        for(int i=n-1;i>=0;i--){
            for(int j=0;j<=i;j++){
                container[j]=Math.min(container[j],container[j+1])+triangle.get(i).get(j);
            }
        }
        return container[0];
    }
    public static int minimumTotal2(List<List<Integer>> triangle) {

        if(triangle==null||triangle.size()==0){
            return 0;
        }
        int n=triangle.size();
        int[][] container=new int[n+1][n+1];

        for(int i=n-1;i>=0;i--){
            for(int j=0;j<=i;j++){
                container[i][j]=Math.min(container[i+1][j],container[i+1][j+1])+triangle.get(i).get(j);
            }
        }
        return container[0][0];
    }

    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
     *
     *  
     *
     * 示例：
     *
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if(l1==null&l2==null){
            return null;
        }

        ListNode newHead=null;
        ListNode newTail=null;

        while (l1!=null||l2!=null){
            ListNode next=l1==null?l2:(l2==null?l1:(l1.val<l2.val?l1:l2));

            if(newTail==null){
                newHead=next;
                newTail=next;
            }else{
                newTail.next=next;
                newTail=newTail.next;
            }
            if(next==l1){
                l1=l1.next;
            }else{
                l2=l2.next;
            }
        }
        return newHead;
    }

    static final class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

    }

    /**
     * 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
     *
     * 注意：整数序列中的每一项将表示为一个字符串。
     *
     * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
     *
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * 第一项是数字 1
     *
     * 描述前一项，这个数是 1 即 “一个 1 ”，记作 11
     *
     * 描述前一项，这个数是 11 即 “两个 1 ” ，记作 21
     *
     * 描述前一项，这个数是 21 即 “一个 2 一个 1 ” ，记作 1211
     *
     * 描述前一项，这个数是 1211 即 “一个 1 一个 2 两个 1 ” ，记作 111221
     * 示例 1:
     *
     * 输入: 1
     * 输出: "1"
     * 解释：这是一个基本样例。
     * 示例 2:
     *
     * 输入: 4
     * 输出: "1211"
     * 解释：当 n = 3 时，序列是 "21"，其中我们有 "2" 和 "1" 两组，"2" 可以读作 "12"，也就是出现频次 = 1 而 值 = 2；类似 "1" 可以读作 "11"。所以答案是 "12" 和 "11" 组合在一起，也就是 "1211"。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/count-and-say
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public static String countAndSay(int n) {
        if(n==1){
            return "1";
        }
        char[] str=countAndSay(n-1).toCharArray();

        if(str.length==1){
            return 1+""+str[0];
        }
        int c=1;
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<str.length-1;i++){
            if(str[i]==str[i+1]){
                c++;
                if(i+1==str.length-1){
                    sb.append(c).append(str[i]);
                }
            }else{
                sb.append(c).append(str[i]);
                c=1;
                if(i+1==str.length-1){
                    sb.append(1).append(str[i+1]);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     *
     * 你可以假设数组中无重复元素。
     *
     * 示例 1:
     *
     * 输入: [1,3,5,6], 5
     * 输出: 2
     * 示例 2:
     *
     * 输入: [1,3,5,6], 2
     * 输出: 1
     * 示例 3:
     *
     * 输入: [1,3,5,6], 7
     * 输出: 4
     * 示例 4:
     *
     * 输入: [1,3,5,6], 0
     * 输出: 0
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/search-insert-position
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        if(nums==null||nums.length==0){
            return 0;
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i]==target){
                return i;
            }else if(nums[i]>target){
                return i==0?0:i;
            }

            if(i==nums.length-1){
                return nums.length;
            }
        }
        return 0;
    }

    /**
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     *
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     *
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     *
     * 示例 1:
     *
     * 输入: [1,2,3]
     * 输出: [1,2,4]
     * 解释: 输入数组表示数字 123。
     * 示例 2:
     *
     * 输入: [4,3,2,1]
     * 输出: [4,3,2,2]
     * 解释: 输入数组表示数字 4321。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/plus-one
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param digits
     * @return
     */
    public static int[] plusOne(int[] digits) {
        if(digits==null||digits.length==0){
            return new int[]{1};
        }
        boolean carry=false;
        for(int i=digits.length-1;i>=0;i--){
            if(i==digits.length-1||carry){
                if(digits[i]==9){
                    digits[i]=0;
                    carry=true;
                }else{
                    digits[i]+=1;
                    carry=false;
                }
            }
        }
        if(carry){
            int[] res=new int[digits.length+1];
            res[0]=1;
            for(int i=1;i<res.length;i++){
                res[i]=digits[i-1];
            }
            return res;
        }
        return digits;
    }

    /**
     * 实现 int sqrt(int x) 函数。
     *
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     *
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     *
     * 示例 1:
     *
     * 输入: 4
     * 输出: 2
     * 示例 2:
     *
     * 输入: 8
     * 输出: 2
     * 说明: 8 的平方根是 2.82842...,
     *      由于返回类型是整数，小数部分将被舍去。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sqrtx
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param x
     * @return
     */
    public static int mySqrt(int x) {

        if(x==0){
            return 0;
        }

        double last=x;
        double res=(x==1)?2:1;
        while (last!=res){
            last=res;
            res=(last+x/last)/2;
        }
        return (int)res;
    }

//    public static double mySqrt2(int x){
//        //float后加f转换成double类型
//        if(x == 0) return 0;
//        float result = x;
//        float xhalf = 0.5f*result;
//        int i = *(int*)&result;
//
//        // what the fuck?
//        i = 0x5f3759df - (i>>1);
//        result = *(float*)&i;
//        result = result*(1.5f-xhalf*result*result);
//        result = result*(1.5f-xhalf*result*result);
//        return 1.0f/result;
//    }

    /**
     * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
     *
     * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
     *
     * 说明:
     *
     * 返回的下标值（index1 和 index2）不是从零开始的。
     * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
     * 示例:
     *
     * 输入: numbers = [2, 7, 11, 15], target = 9
     * 输出: [1,2]
     * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @return
     */
    public static int[] twoSum(int[] numbers, int target){
        if(numbers==null||numbers.length==1){
            return new int[]{};
        }
        int l=numbers.length/2-1,r=numbers.length/2;

        while (l<r&&l>=0&&r<=numbers.length-1){
            int tempRes=numbers[l]+numbers[r]-target;

            System.out.println("经历结果"+tempRes+","+l+","+r);
            if(tempRes==0){
                return new int[]{l+1,r+1};
            }
            if(tempRes>0){
                if(l==0){
                    r--;
                }else{
                    l--;
                }
            }else{
                if(r==numbers.length-1){
                    l++;
                }else{
                    r++;
                }
            }
        }
        return new int[]{};
    }

    public static int[] twoSum2(int[] numbers, int target){
        if(numbers==null||numbers.length==1){
            return new int[]{};
        }
        int l=0,r=numbers.length-1;

        while (l<r&&l>=0&&r<=numbers.length-1){
            int tempRes=numbers[l]+numbers[r]-target;

            if(tempRes==0){
                return new int[]{l+1,r+1};
            }else if(tempRes>0){
                r--;
            }else{
                l++;
            }
        }
        return new int[]{};
    }

    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     *
     * 示例 1:
     *
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     * 示例 2:
     *
     * 输入: s = "rat", t = "car"
     * 输出: false
     * 说明:
     * 你可以假设字符串只包含小写字母。
     *
     * 进阶:
     * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/valid-anagram
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        if(s==null&&t==null){
            return true;
        }else if(s==null^t==null||s.length()!=t.length()){
            return false;
        }
        int [] sBits=new int[26];

        for(Character c:s.toCharArray()){
            sBits[(int)c-97]++;
        }

        for(Character u:t.toCharArray()){
            sBits[(int)u-97]--;
        }

        for(int i:sBits){
            if(i!=0){
                return false;
            }
        }

        return true;
    }

    /**
     * 删除链表中等于给定值 val 的所有节点。
     *
     * 示例:
     *
     * 输入: 1->2->6->3->4->5->6, val = 6
     * 输出: 1->2->3->4->5
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements(ListNode head, int val) {
        if(head==null){
            return head;
        }
        if(head.val==val){
            return removeElements(head.next,val);
        }
        ListNode pre=head ,current=pre.next;
        while (current!=null){
            if(current.val==val){
                pre.next=current.next;
            }else{
                pre=pre.next;
            }
            current=pre.next;
        }
        return head;
    }

    /**
     * 统计所有小于非负整数 n 的质数的数量。
     *
     * 示例:
     *
     * 输入: 10
     * 输出: 4
     * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
     * @param n
     * @return
     */
    public static int countPrimes(int n) {
        if(n<=2){
            return 0;
        }
        int count=0;
        boolean[] table=new boolean[n];//false是合数
        for(int i=2;i<n;i++){
            if(!table[i]){
                count++;
            }
            int j=2;
            while (j*i<n){
                table[j*i]=true;
                j++;
            }

        }
        return count;
    }

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     *
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * 注意：给定 n 是一个正整数。
     *
     * 示例 1：
     *
     * 输入： 2
     * 输出： 2
     * 解释： 有两种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶
     * 2.  2 阶
     * 示例 2：
     *
     * 输入： 3
     * 输出： 3
     * 解释： 有三种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶 + 1 阶
     * 2.  1 阶 + 2 阶
     * 3.  2 阶 + 1 阶
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/climbing-stairs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if(n<=3){
            return n;
        }
        int t1=2,t2=3;
        for(int i=3;i<n;i++){
            int temp=t1+t2;
            t1=t2;
            t2=temp;
        }
        return t2;
    }

    /**
     * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
     *
     *  
     *
     * 示例：
     *
     * 输入：3
     * 输出：
     * [
     *   [1,null,3,2],
     *   [3,2,null,1],
     *   [3,1,null,null,2],
     *   [2,1,3],
     *   [1,null,2,null,3]
     * ]
     * 解释：
     * 以上的输出对应以下 5 种不同结构的二叉搜索树：
     *
     *    1         3     3      2      1
     *     \       /     /      / \      \
     *      3     2     1      1   3      2
     *     /     /       \                 \
     *    2     1         2                 3
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        return null;
    }

    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
     *
     * 示例 1：
     *
     * 输入：[3,4,5,1,2]
     * 输出：1
     * 示例 2：
     *
     * 输入：[2,2,2,0,1]
     * 输出：0
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param numbers
     * @return
     */
    public static int minArray(int[] numbers) {
        if(numbers==null||numbers.length==0){
            return 0;
        }
        if(numbers.length==1){
            return numbers[0];
        }

        Integer res=numbers[0];
        for(int i:numbers){
            res=res>i?i:res;
        }
        return res;
    }

    public static int minArray2(int[] numbers) {
        if(numbers==null||numbers.length==0){
            return 0;
        }
        if(numbers.length==1){
            return numbers[0];
        }
        int l=0,r=numbers.length-1;
        while (l<r){
            int pivot=(l+r)/2;
            if(numbers[pivot]>numbers[r]){
                l=pivot+1;
            }else if(numbers[pivot]<numbers[r]){
                r=pivot;
            }else{
                r--;
            }
        }
        return numbers[l];
    }

    /**
     * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
     *
     * 说明:
     *
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。

     * 示例:
     *
     * 输入:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     *
     * 输出: [1,2,2,3,5,6]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] can=new int[nums1.length];

        int i=0,j=0,k=0;
        while (i<m||j<n){
            if(i>=m){
                can[k++]=nums2[j++];
            }else if(j>=n){
                can[k++]=nums1[i++];
            }else{
                can[k++]=nums1[i]>nums2[j]?nums2[j++]:nums1[i++];
            }
        }
        for(int p=0;p<nums1.length;p++){
            nums1[p]=can[p];
        }
    }

    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 示例:
     *
     * 输入: [-2,1,-3,4,-1,2,1,-5,4],
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * 进阶:
     *
     * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-subarray
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        if(nums==null||nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        int res=nums[0];
        int sum=0;
        for(int i:nums){
            if(sum>0){
                sum+=i;
            }else{
                sum=i;
            }
            res=Math.max(res,sum);
        }
        return res;
    }

    /**
     * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
     *
     * 如果不存在最后一个单词，请返回 0 。
     *
     * 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。
     *
     * 示例:
     *
     * 输入: "Hello World"
     * 输出: 5
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/length-of-last-word
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public static int lengthOfLastWord(String s) {
        if(s==null||s.trim().length()==0){
            return 0;
        }
        String[] strs= s.trim().split(" +");
        return strs.length>0?strs[strs.length-1].length():0;
    }

    /**
     * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
     *
     * 例如，
     *
     *     1 -> A
     *     2 -> B
     *     3 -> C
     *     ...
     *     26 -> Z
     *     27 -> AA
     *     28 -> AB
     *     ...
     * 示例 1:
     *
     * 输入: 1
     * 输出: "A"
     * 示例 2:
     *
     * 输入: 28
     * 输出: "AB"
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/excel-sheet-column-title
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public static String convertToTitle(int n) {
        if(n<=0){
            return "";
        }
        StringBuffer sb=new StringBuffer();
        int mod=0;
        while (n>0){
            mod=(n-1)%26;
            n=(n-1)/26;
            sb.append((char)(65+mod));
        }

        return sb.reverse().toString();
    }

    /**
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     *
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     * 示例 1:
     *
     * 输入: [3,2,3]
     * 输出: 3
     * 示例 2:
     *
     * 输入: [2,2,1,1,1,2,2]
     * 输出: 2
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/majority-element
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {
        if(nums==null||nums.length==0){
            return 0;
        }
        int count=0;
        int index=0;
        for(int i=0;i<nums.length;i++){
            if(count==0){
                index=i;
            }
            if(nums[i]==nums[index]){
                count++;
                index=i;
            }else{
                count--;
            }
        }
       return nums[index];
    }

    /**
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     *
     * 示例 1:
     *
     * 输入: [1,2,3,4,5,6,7] 和 k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4]
     * 示例 2:
     *
     * 输入: [-1,-100,3,99] 和 k = 2
     * 输出: [3,99,-1,-100]
     * 解释:
     * 向右旋转 1 步: [99,-1,-100,3]
     * 向右旋转 2 步: [3,99,-1,-100]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/rotate-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
        if(nums==null||nums.length<=1){
            return;
        }
        int offset=k%nums.length;

        rotatePart(nums,0,nums.length-offset-1);
        rotatePart(nums,nums.length-offset,nums.length-1);
        rotatePart(nums,0,nums.length-1);

        System.out.println("23233");
    }

    public static void rotatePart(int[] nums,int l,int r){
        while (l<r){
            int temp=nums[l];
            nums[l]=nums[r];
            nums[r]=temp;
            l++;r--;
        }
    }

    /**
     *爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
     *
     * 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
     *
     * 选出任一 x，满足 0 < x < N 且 N % x == 0 。
     * 用 N - x 替换黑板上的数字 N 。
     * 如果玩家无法执行这些操作，就会输掉游戏。
     *
     * 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。

     * 示例 1：
     *
     * 输入：2
     * 输出：true
     * 解释：爱丽丝选择 1，鲍勃无法进行操作。
     * 示例 2：
     *
     * 输入：3
     * 输出：false
     * 解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/divisor-game
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param N
     * @return
     */
    public boolean divisorGame(int N) {
        return (N&1)!=1;
    }

    /**
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     *
     * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
     *
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     *
     * 示例 1:
     * s = "abc", t = "ahbgdc"
     *
     * 返回 true.
     *
     * 示例 2:
     * s = "axc", t = "ahbgdc"
     *
     * 返回 false.
     *
     * 后续挑战 :
     *
     * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
     *
     * 致谢:
     *
     * 特别感谢 @pbrother 添加此问题并且创建所有测试用例。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/is-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @param t
     * @return
     */
    public static boolean isSubsequence(String s, String t) {
        if(s==null||t==null||t.length()<s.length()){
            return false;
        }
        if(s.length()==0){
            return true;
        }
        int i=0,j=0;
        char[] ss=s.toCharArray(),tt=t.toCharArray();
        while (i<tt.length){
            if(tt[i]==ss[j]){
                if(++j>=ss.length){
                    return true;
                }
            }
            i++;
        }
        return false;
    }

    /**
     * 给定一个二叉树，找出其最大深度。
     *
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最大深度 3 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        int left=maxDepth(root.left);
        int right=maxDepth(root.right);
        return (left>right?left:right)+1;
    }

    /**
     * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
     *
     * 现有一个链表 -- head = [4,5,1,9]，它可以表示为:
     *
     * 示例 1:
     *
     * 输入: head = [4,5,1,9], node = 5
     * 输出: [4,1,9]
     * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     * 示例 2:
     *
     * 输入: head = [4,5,1,9], node = 1
     * 输出: [4,5,9]
     * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/delete-node-in-a-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param node
     */
    public static void deleteNode(ListNode node) {
        node.val=node.next.val;
        node.next= node.next.next;
    }

    /**
     * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
     *
     * 示例 1:
     *
     * 输入: 1
     * 输出: true
     * 解释: 20 = 1
     * 示例 2:
     *
     * 输入: 16
     * 输出: true
     * 解释: 24 = 16
     * 示例 3:
     *
     * 输入: 218
     * 输出: false
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/power-of-two
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public static boolean isPowerOfTwo(int n) {
        if(n<=0){
            return false;
        }
        boolean flag=false;
        while (n>0){
            if((n&1)==1){
                if(flag){
                    return false;
                }else{
                    flag=true;
                }
            }
            n=n>>1;
        }
        return true;
    }

    /**
     * 给定一个单词列表，只返回可以使用在键盘同一行的字母打印出来的单词。键盘如下图所示。

     * 示例：
     *
     * 输入: ["Hello", "Alaska", "Dad", "Peace"]
     * 输出: ["Alaska", "Dad"]

     * 注意：
     *
     * 你可以重复使用键盘上同一字符。
     * 你可以假设输入的字符串将只包含字母。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/keyboard-row
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param words
     * @return
     */
    public static String[] findWords(String[] words) {
        if(words==null||words.length==0){
            return new String[]{};
        }
        List<Character> row1=new ArrayList<>();
        row1.add('Q');row1.add('q');row1.add('W');row1.add('w');row1.add('E');row1.add('e');row1.add('R');row1.add('r');
        row1.add('T');row1.add('t');row1.add('W');row1.add('w');row1.add('E');row1.add('e');row1.add('R');row1.add('r');
        row1.add('Q');row1.add('q');row1.add('W');row1.add('w');row1.add('E');row1.add('e');row1.add('R');row1.add('r');

        return null;
    }

    /**
     *给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
     *
     * 示例 1:
     *
     * 输入: 2
     * 输出: 1
     * 解释: 2 = 1 + 1, 1 × 1 = 1。
     * 示例 2:
     *
     * 输入: 10
     * 输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
     * 说明: 你可以假设 n 不小于 2 且不大于 58。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/integer-break
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public static int integerBreak(int n) {
        return 0;
    }

    /**
     * 魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。给定一个有序整数数组，编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。若有多个魔术索引，返回索引值最小的一个。
     *
     * 示例1:
     *
     *  输入：nums = [0, 2, 3, 4, 5]
     *  输出：0
     *  说明: 0下标的元素为0
     * 示例2:
     *
     *  输入：nums = [1, 1, 1]
     *  输出：1
     * 提示:
     *
     * nums长度在[1, 1000000]之间
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/magic-index-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public static int findMagicIndex(int[] nums) {
        if(nums==null||nums.length==0){
            return -1;
        }
        int length=nums.length;
        for(int i=0;i<length;i++){
            if(i==nums[i]){
                return i;
            }
        }
        return -1;
    }

    /**
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
     *
     * 注意：
     *
     * num1 和num2 的长度都小于 5100.
     * num1 和num2 都只包含数字 0-9.
     * num1 和num2 都不包含任何前导零。
     * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-strings
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param num1
     * @param num2
     * @return
     */
    public static String addStrings(String num1, String num2) {
        if(num1==null||num1.length()==0){
            return num2;
        }
        if(num2==null||num2.length()==0){
            return num1;
        }
        StringBuffer sb=new StringBuffer();
        int carry=0,i=num1.length()-1,j=num2.length()-1;
        while (i>=0||j>=0||carry>0){
            if(i>=0){
                carry+=num1.charAt(i)-'0';i--;
            }
            if(j>=0){
                carry+=num2.charAt(j)-'0';j--;
            }
            sb.append(carry%10);
            carry/=10;
        }
        return sb.reverse().toString();
    }

    /**
     * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

     * 示例:
     *
     * 现有矩阵 matrix 如下：
     *
     * [
     *   [1,   4,  7, 11, 15],
     *   [2,   5,  8, 12, 19],
     *   [3,   6,  9, 16, 22],
     *   [10, 13, 14, 17, 24],
     *   [18, 21, 23, 26, 30]
     * ]
     * 给定 target = 5，返回 true。
     *
     * 给定 target = 20，返回 false。
     * 限制：
     *
     * 0 <= n <= 1000
     *
     * 0 <= m <= 1000
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param matrix
     * @param target
     * @return
     */
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix==null||matrix.length==0){
            return false;
        }
        int i=matrix.length-1,j=0,levelLength=matrix[0].length-1;
        while (i>=0&&j<=levelLength){
            int res=matrix[i][j]-target;
            if(res==0){
                System.out.println(i+","+j);
                return true;
            }else if (res>0){
                i--;
            }else{
                j++;
            }
        }
        return false;
    }

    /**
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        return false;
    }

    /**
     * 请判断一个链表是否为回文链表。
     *
     * 示例 1:
     *
     * 输入: 1->2
     * 输出: false
     * 示例 2:
     *
     * 输入: 1->2->2->1
     * 输出: true
     * 进阶：
     * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        if(head==null){
            return false;
        }
        int length=1;
        ListNode node=head;
        while ((node=node.next)!=null){length++;}

        length=((length&1)!=1?length>>1:(length>>1+1));
        node=head;
        while (length-->0){
            node=node.next;
        }
        Stack<Integer> stack=new Stack<>();
        while (node!=null){
            stack.push(node.val);
            node=node.next;
        }
        while (!stack.empty()){
            if(head==null||stack.pop()!=head.val){
                return false;
            }
            head=head.next;
        }
        return  true;
    }

    /**
     * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。
     * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
     * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     *
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
     *
     * 示例 1:
     *
     * 输入: [3,2,3,null,3,null,1]
     *
     *      3
     *     / \
     *    2   3
     *     \   \
     *      3   1
     *
     * 输出: 7
     * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
     * 示例 2:
     *
     * 输入: [3,4,5,1,3,null,1]
     *
     *      3
     *     / \
     *    4   5
     *   / \   \
     *  1   3   1
     *
     * 输出: 9
     * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/house-robber-iii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public static int rob(TreeNode root) {
        if(root==null){
            return 0;
        }
        return robCal(root,new int[2],true);
    }

    private static int robCal(TreeNode root ,int[] can,boolean odd) {
        if(root!=null){
            can[odd?0:1]+=root.val;
            if(root.left!=null){
                robCal(root.left,can,!odd);
            }
            if(root.right!=null){
                robCal(root.right,can,!odd);
            }

            return Math.max(can[0],can[1]);
        }
        return 0;
    }

    /**
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * 限制：
     *
     * 0 <= 节点个数 <= 5000
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode p=head,q=p.next,r=q.next;
        p.next=null;
        q.next=p;
        while (r!=null){
            p=q;
            q=r;
            r=r.next;
            q.next=p;
        }
        return q;
    }

    /**
     *给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
     *
     * 示例 1:
     *
     * 输入: ["abcd","dcba","lls","s","sssll"]
     * 输出: [[0,1],[1,0],[3,2],[2,4]]
     * 解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
     * 示例 2:
     *
     * 输入: ["bat","tab","cat"]
     * 输出: [[0,1],[1,0]]
     * 解释: 可拼接成的回文串为 ["battab","tabbat"]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/palindrome-pairs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param words
     * @return
     */
    public static List<List<Integer>> palindromePairs(String[] words) {
        if(words==null||words.length<2){
            return new ArrayList<>();
        }
        int length=words.length;
        List<List<Integer>> res=new ArrayList<>();

        for(int i=0;i<length;i++){
            for(int j=0;j<length;j++){
                if(i==j){
                    continue;
                }
                if(checkValid(words[i],words[j])){
                    List<Integer> valid=new ArrayList<>();
                    valid.add(i);
                    valid.add(j);
                    res.add(valid);
                }
            }
        }
        return res;
    }

    /**
     * s1+s2是否有效
     * @param s
     * @param t
     * @return
     */
    public static boolean checkValid(String s,String t){
        int l1=s.length();
        int l2=t.length();
        int j=l1+l2-1;

        int i=0;
        while (i<j){
            if(i>=l1){
                if(t.charAt(i-l1)!=t.charAt(j-l1)){return  false;}
            }else if(j>=l1){
                if(s.charAt(i)!=t.charAt(j-l1)){return false;}
            }else{
                if(s.charAt(i)!=s.charAt(j)){return false; }
            }
            i++;
            j--;
        }
        return true;
    }

    /**
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * 示例 1：
     *
     * 输入：s = "We are happy."
     * 输出："We%20are%20happy."
     * 限制：
     *
     * 0 <= s 的长度 <= 10000
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public static String replaceSpace(String s) {
        if(s==null){
            return s;
        }
        return s.replace(" ","%20");
    }

    /**
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     * 示例 1：
     *
     * 输入：head = [1,3,2]
     * 输出：[2,3,1]
     * @param head
     * @return
     */
    public static int[] reversePrint(ListNode head) {
        if(head==null){
            return new int[]{};
        }
        ListNode p=head;
        int count=0;
        while (p!=null){
            p=p.next;
            count++;
        }
        int[] res=new int[count];
        int i=count-1;
        while (head!=null){
            res[i--]=head.val;
            head=head.next;
        }
        return res;
    }

    /**
     * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )

     * 示例 1：
     *
     * 输入：
     * ["CQueue","appendTail","deleteHead","deleteHead"]
     * [[],[3],[],[]]
     * 输出：[null,null,3,-1]
     * 示例 2：
     *
     * 输入：
     * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
     * [[],[],[5],[2],[],[]]
     * 输出：[null,-1,null,null,5,2]
     * 提示：
     *
     * 1 <= values <= 10000
     * 最多会对 appendTail、deleteHead 进行 10000 次调用
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class CQueue {

        Stack<Integer>  pushStack=new Stack<>();
        Stack<Integer>  popStack=new Stack<>();

        public CQueue() {

        }

        public void appendTail(int value) {
            pushStack.push(value);
        }

        public int deleteHead() {
            if(popStack.isEmpty()){
                while (!pushStack.isEmpty()){
                    popStack.push(pushStack.pop());
                }
            }
            if(popStack.isEmpty()){return  -1;}
            return popStack.pop();
        }
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     *
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     *
     * 示例 1：
     *
     * 输入：n = 2
     * 输出：2
     * 示例 2：
     *
     * 输入：n = 7
     * 输出：21
     * 示例 3：
     *
     * 输入：n = 0
     * 输出：1
     * 提示：
     *
     * 0 <= n <= 100
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public static int numWays(int n) {
        if(n==0){
            return 1;
        }
        if(n<3){
            return n;
        }
        int[] res=new int[n+1];res[1]=1;res[2]=2;
        for(int i=3;i<=n;i++){
            res[i]=(res[i-1]+res[i-2])%1000000007;
        }
        return res[n];
    }

    /**
     *请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
     *
     * 示例 1：
     *
     * 输入：00000000000000000000000000001011
     * 输出：3
     * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
     * 示例 2：
     *
     * 输入：00000000000000000000000010000000
     * 输出：1
     * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
     * 示例 3：
     *
     * 输入：11111111111111111111111111111101
     * 输出：31
     * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public static int hammingWeight(int n) {

        int count=0;
        while (n!=0){
            if((n&1)==1){
                count++;
            }
            n=n>>>1;
        }
        return count;
    }


    /**
     * 给定两个二叉树，编写一个函数来检验它们是否相同。
     *
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     *
     * 示例 1:
     *
     * 输入:       1         1
     *           / \       / \
     *          2   3     2   3
     *
     *         [1,2,3],   [1,2,3]
     *
     * 输出: true
     * 示例 2:
     *
     * 输入:      1          1
     *           /           \
     *          2             2
     *
     *         [1,2],     [1,null,2]
     *
     * 输出: false
     * 示例 3:
     *
     * 输入:       1         1
     *           / \       / \
     *          2   1     1   2
     *
     *         [1,2,1],   [1,1,2]
     *
     * 输出: false
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/same-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param p
     * @param q
     * @return
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if((p==null^q==null)){
            return false;
        }else if(p==null){
            return true;
        }else{
            if(p.val==q.val){
                if(isSameTree(p.left,q.left)){
                    return isSameTree(p.right,q.right);
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
    }

    /**
     * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
     *
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
     *
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

     * 示例 1：
     *
     * 输入：n = 2
     * 输出：1
     * 示例 2：
     *
     * 输入：n = 5
     * 输出：5

     * 提示：
     *
     * 0 <= n <= 100
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param N
     * @return
     */
    public static int fib(int N) {
        if(N<2){
            return N;
        }
        int[] can=new int[N+1];
        can[0]=0;can[1]=1;
        for(int i=2;i<=N;i++){
            can[i]=(can[i-1]+can[i-2])%1000000007;
        }
        return can[N];
    }

    /**
     * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1]
     * 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     *
     * 示例 1：
     *
     * 输入: 2
     * 输出: 1
     * 解释: 2 = 1 + 1, 1 × 1 = 1
     * 示例 2:
     *
     * 输入: 10
     * 输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
     * 提示：
     *
     * 2 <= n <= 58
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/jian-sheng-zi-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public static int cuttingRope(int n) {
        if(n==2){
            return  1;
        }
        if(n==3){
            return  2;
        }
        int[] dp=new int[n+1];
        for(int i=4;i<=n;i++){
            for(int j=1;j<i;j++){

            }
        }
        return 0;
    }


    /**
     *给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     *
     * 说明：你不能倾斜容器，且 n 的值至少为 2。

     * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

     * 示例：
     *
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/container-with-most-water
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int l=0,r=height.length-1,max=0;
        while (l<r){
            int diff=height[l]-height[r];
            if(diff>0){
                max=Math.max(max,(r-l)*height[r]);
                r--;
            }else{
                max=Math.max(max,(r-l)*height[l]);
                l++;
            }
        }
        return max;
    }

    public static int jieyushui(int[] height) {
        int l=0,r=height.length-1,l_max=0,r_max=0,area=0;
        while (l<r){
            if(height[l]<height[r]){
                if(height[l]<l_max){
                    area+=l_max-height[l];
                }else{
                    l_max=height[l];
                }
                l++;
            }else{
                if(height[r]<r_max){
                    area+=r_max-height[r];
                }else{
                    r_max=height[r];
                }
                r--;
            }
        }
        return area;
    }

    /**
     * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
     *
     * 重复出现的子串要计算它们出现的次数。
     *
     * 示例 1 :
     *
     * 输入: "00110011"
     * 输出: 6
     * 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
     *
     * 请注意，一些重复出现的子串要计算它们出现的次数。
     *
     * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
     * 示例 2 :
     *
     * 输入: "10101"
     * 输出: 4
     * 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
     * 注意：
     *
     * s.length 在1到50,000之间。
     * s 只包含“0”或“1”字符。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/count-binary-substrings
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public static int countBinarySubstrings(String s) {
        char[] ss=s.toCharArray();
        int lastCount=-1,thisCount=1,res=0;
        for(int i=1;i<ss.length;i++){
            if(ss[i]==ss[i-1]){
                thisCount++;
            }else{
                res+= lastCount==-1?0:Math.min(lastCount,thisCount);
                lastCount=thisCount;thisCount=1;
            }
        }
        res+= lastCount==-1?0:Math.min(lastCount,thisCount);
        return res;
    }

    /**
     * 给定一个 N 叉树，返回其节点值的前序遍历。
     *
     * 例如，给定一个 3叉树 :

     * 返回其前序遍历: [1,3,5,6,2,4]。

     * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public static List<Integer> preorder(Node root) {
        /**
         * 递归法
         */
        List<Integer> res=new ArrayList<>();
        preOrderNode(root,res);
        return res;
    }

    private static void preOrderNode(Node root,List<Integer> res) {
        if(root==null){
            return;
        }
        res.add(root.val);
        if(root.children!=null&&root.children.size()>0){
            for(Node child:root.children){
                preOrderNode(child,res);
            }
        }
    }

    /**
     * 迭代法
     * @param root
     * @return
     */
    public static List<Integer> preorder2(Node root) {
        /**
         * 迭代法
         */
        List<Integer> res=new ArrayList<>();
        Stack<Node> stack=new Stack<>();

        if(root!=null){
            stack.push(root);

            while (!stack.isEmpty()){
                Node node=stack.pop();
                res.add(node.val);

                List<Node> children=node.children;
                if(children!=null&&children.size()>0){
                    int i=children.size()-1;
                    while (i>=0){
                        stack.push(children.get(i--));
                    }
                }
            }
        }

        return res;
    }

    /**
     * 给定一个二叉树，返回它的 前序 遍历。
     *  示例:
     * 输入: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     * 输出: [1,2,3]
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        Stack<TreeNode> stack=new Stack<>();

        if(root!=null){
            stack.push(root);

            while (!stack.isEmpty()){
                TreeNode node=stack.pop();

                if(node!=null){
                    res.add(node.val);

                    stack.push(node.right);
                    stack.push(node.left);
                }
            }
        }
        return res;
    }

    /**
     * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
     *
     * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     *
     * 示例:
     *
     * X X X X
     * X O O X
     * X X O X
     * X O X X
     * 运行你的函数后，矩阵变为：
     *
     * X X X X
     * X X X X
     * X X X X
     * X O X X
     * 解释:
     *
     * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
     * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/surrounded-regions
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param board
     */
    public static void solve(char[][] board) {
        int row=0;
        if((row=board.length)==0){
            return;
        }
        int col=board[0].length;
        /**
         * 找到边界的'O'
         */
        for(int i=0;i<col;i++){
            if(board[0][i]=='O'){
                bfs(board,0,i);
            }
            if(board[row-1][i]=='O'){
                bfs(board,row-1,i);
            }
        }

        for(int i=0;i<row;i++){
            if(board[i][0]=='O'){
                bfs(board,i,0);
            }
            if(board[i][col-1]=='O'){
                bfs(board,i,col-1);
            }
        }

        /**
         *非M->X  M->O
         */

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(board[i][j]!='M'){
                    board[i][j]='X';
                }else{
                    board[i][j]='O';
                }
            }
        }
    }

    public static void bfs(char[][] board,int i,int j){
        /**b
         * 从边界的O开始广度搜索，把相邻的'O'改成M
         */
        if(i<0||i>board.length-1||j<0||j>board[0].length-1||board[i][j]!='O'){
            return;
        }
        board[i][j]='M';
        bfs(board,i-1,j);
        bfs(board,i+1,j);
        bfs(board,i,j-1);
        bfs(board,i,j+1);
    }

    /**
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     *
     * 假设一个二叉搜索树具有如下特征：
     *
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * 示例 1:
     *
     * 输入:
     *     2
     *    / \
     *   1   3
     * 输出: true
     * 示例 2:
     *
     * 输入:
     *     5
     *    / \
     *   1   4
     *      / \
     *     3   6
     * 输出: false
     * 解释: 输入为: [5,1,4,null,null,3,6]。
     *      根节点的值为 5 ，但是其右子节点值为 4 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root) {
        List<TreeNode> can=new ArrayList<>();
        midOrder(root,can);

        TreeNode last=null;
        for(TreeNode node:can){
            if(last!=null&&last.val>=node.val){
                return false;
            }
            last=node;
        }
        return true;
    }

    private static void midOrder(TreeNode root, List<TreeNode> can) {
        if(root!=null){
            midOrder(root.left,can);
            can.add(root);
            midOrder(root.right,can);
        }
    }

    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     *
     * 示例 1:
     *
     * 输入: 123
     * 输出: 321
     *  示例 2:
     *
     * 输入: -123
     * 输出: -321
     * 示例 3:
     *
     * 输入: 120
     * 输出: 21
     * 注意:
     *
     * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-integer
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int i = 0;
        while (x != 0) {
            int tmp=i * 10 + x % 10;
            if(tmp/10!=i){
                return 0;
            }
            i=tmp;
            x = x / 10;
        }
        return i;
    }

    /**
     * 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从1 到 n 的 min(ai, bi) 总和最大。
     * 示例 1:
     *
     * 输入: [1,4,3,2]
     *
     * 输出: 4
     * 解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4).
     * 提示:
     *
     * n 是正整数,范围在 [1, 10000].
     * 数组中的元素范围在 [-10000, 10000].
     * @param nums
     * @return
     */
    public static int arrayPairSum(int[] nums) {
        if (nums==null||nums.length==0){
            return 0;
        }
//        bubbleSort(nums);
        Arrays.sort(nums);

        int res=0;
        for(int i=0 ;i<nums.length;i+=2){
            res+=Math.min(nums[i],nums[i+1]);
        }
        return res;
    }


    public static void bubbleSort(int[] nums){
        int length=nums.length;
        for(int i=0;i<length;i++){
            for(int j=0;j<length-i-1;j++){
                if(nums[j]>nums[j+1]){
                    int tmp=nums[j];
                    nums[j]=nums[j+1];
                    nums[j+1]=tmp;
                }
            }
        }
    }

    /**
     * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     *
     * 示例 1:
     *
     * 输入: num1 = "2", num2 = "3"
     * 输出: "6"
     * 示例 2:
     *
     * 输入: num1 = "123", num2 = "456"
     * 输出: "56088"
     * 说明：
     *
     * num1 和 num2 的长度小于110。
     * num1 和 num2 只包含数字 0-9。
     * num1 和 num2 均不以零开头，除非是数字 0 本身。
     * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/multiply-strings
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param num1
     * @param num2
     * @return
     */
    public static String multiply(String num1, String num2) {
        int len1=num1.length();
        int len2=num2.length();

        int[] table=new int[len1+len2+1];

        int startIndex=len1+len2-2;
        for(int i=len1-1;i>=0;i--){
            for(int j=len2-1;j>=0;j--){
                table[startIndex-i-j]+=Integer.parseInt(num1.charAt(i)+"")*Integer.parseInt(num2.charAt(j)+"");
            }
        }
        StringBuffer sb=new StringBuffer();
        //处理进位
        for(int i=0;i<len1+len2;i++){
            if(table[i]>=10){
                table[i+1]+=table[i]/10;
                table[i]=table[i]%10;
            }
            sb.append(table[i]);
        }
        if(table[len1+len2]>0){
            sb.append(table[len1+len2]);
        }
        while (sb.length()>1&&sb.charAt(sb.length()-1)=='0'){
            sb.setLength(sb.length()-1);
        }
        return sb.reverse().toString();
    }

    public static String multiply2(String num1, String num2) {
        /**
        * num2拆成一位一位的
        */

        char[] cn1=num1.toCharArray();
        char[] cn2=num2.toCharArray();
        int i=cn2.length-1,res=0;
        while (i>=0){
            res+=Math.pow(10,cn2.length-1-i)*singleCal(cn1, Integer.parseInt(cn2[i]+""));
            --i;
        }
        return res+"";
    }

    public static int singleCal(char[] cn1,int n){
        int res=0;
        for(int i=cn1.length-1;i>=0;i--){
            res+=n*Integer.parseInt(cn1[i]+"")*Math.pow(10,cn1.length-1-i);
        }
        return res;
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     *
     * 示例 1:
     *
     * 输入: "()"
     * 输出: true
     * 示例 2:
     *
     * 输入: "()[]{}"
     * 输出: true
     * 示例 3:
     *
     * 输入: "(]"
     * 输出: false
     * 示例 4:
     *
     * 输入: "([)]"
     * 输出: false
     * 示例 5:
     *
     * 输入: "{[]}"
     * 输出: true
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/valid-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if(s==null||"".equals(s)){
            return true;
        }
        Stack<Character> stack=new Stack();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c=='('||c=='['||c=='{'){
                stack.push(c);
            }else {
                if(stack.isEmpty()){
                    return false;
                }
                if(!other(c).equals(stack.pop())){
                    return false;
                }
            }
        }
        if(stack.isEmpty()){
            return true;
        }
        return false;
    }

    public static Character other(char c){
        switch (c){
            case ')':
                return '(';
            case ']':
                return '[';
            case '}':
                return '{';
        }
        return null;
    }

    public boolean isValid2(String s) {
        if(s==null||"".equals(s)){
            return true;
        }
        Stack<Character> stack=new Stack();

        for(char c:s.toCharArray()){
            if(c=='('){
                stack.push(')');
            }else if(c=='['){
                stack.push(']');
            }else if(c=='{'){
                stack.push('}');
            }else{
                if(stack.isEmpty()||c!=stack.pop()){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     *
     * 本题中，一棵高度平衡二叉树定义为：
     *
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
     *
     * 示例 1:
     *
     * 给定二叉树 [3,9,20,null,null,15,7]
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回 true 。
     *
     * 示例 2:
     *
     * 给定二叉树 [1,2,2,3,3,null,null,4,4]
     *
     *        1
     *       / \
     *      2   2
     *     / \
     *    3   3
     *   / \
     *  4   4
     * 返回 false 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/balanced-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public static boolean isBalanced(TreeNode root) {
        if(root==null){
            return true;
        }

        if( Math.abs(helper(root.left)-helper(root.right))<=1){
            return isBalanced(root.left)&&isBalanced(root.right);
        }
        return false;
    }

    public static int helper(TreeNode root){
        if(root==null){
            return 0;
        }
        return Math.max(helper(root.left),helper(root.right))+1;
    }


    /**
     * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
     *
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     *
     * 示例:
     *
     * 给定的有序链表： [-10, -3, 0, 5, 9],
     *
     * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
     *
     *       0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        return helper(head);
    }

    public TreeNode helper(ListNode head){
        if(head==null){return null;}
        ListNode fast=head,slow=head,last=null;

        while (fast.next!=null&&fast.next.next!=null){
            fast = fast.next.next;
            last=slow;
            slow=slow.next;
        }
        TreeNode root=new TreeNode();
        root.val=slow.val;
        //此时slow就是根节点
        if(last==null){
            root.left=null;
        }else{
            last.next=null;
            root.left=helper(head);
        }
        if(slow.next==null){
            root.right=null;
        }else{
            root.right=helper(slow.next);
        }
        return root;
    }

    /**
     * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
     * 示例 1：
     * 输入："abc"
     * 输出：3
     * 解释：三个回文子串: "a", "b", "c"
     * 示例 2：
     *
     * 输入："aaa"
     * 输出：6
     * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
     * 提示：
     *
     * 输入的字符串长度不会超过 1000 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/palindromic-substrings
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public static int countSubstrings(String s) {
        if(s==null){
            return 0;
        }
        int res=0,length;
        if((length=s.length())<2){
            return length;
        }

        boolean[][] dp=new boolean[length][length];

        for(int i=length-1;i>=0;i--){
            for(int j=i;j<length;j++){
                if(s.charAt(i)==s.charAt(j)&&(j-i<3||dp[i+1][j-1])){
                    dp[i][j]=true;
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 让我们一起来玩扫雷游戏！
     *
     * 给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，
     * 数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。
     *
     * 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板：
     *
     * 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
     * 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。
     * 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。
     * 如果在此次点击中，若无更多方块可被揭露，则返回面板。
     * 示例 1：
     *
     * 输入:
     *
     * [['E', 'E', 'E', 'E', 'E'],
     *  ['E', 'E', 'M', 'E', 'E'],
     *  ['E', 'E', 'E', 'E', 'E'],
     *  ['E', 'E', 'E', 'E', 'E']]
     *
     * Click : [3,0]
     *
     * 输出:
     *
     * [['B', '1', 'E', '1', 'B'],
     *  ['B', '1', 'M', '1', 'B'],
     *  ['B', '1', '1', '1', 'B'],
     *  ['B', 'B', 'B', 'B', 'B']]
     *
     * 解释:
     *
     * 示例 2：
     *
     * 输入:
     *
     * [['B', '1', 'E', '1', 'B'],
     *  ['B', '1', 'M', '1', 'B'],
     *  ['B', '1', '1', '1', 'B'],
     *  ['B', 'B', 'B', 'B', 'B']]
     *
     * Click : [1,2]
     *
     * 输出:
     *
     * [['B', '1', 'E', '1', 'B'],
     *  ['B', '1', 'X', '1', 'B'],
     *  ['B', '1', '1', '1', 'B'],
     *  ['B', 'B', 'B', 'B', 'B']]
     *
     * 解释:
     * 注意：
     * 输入矩阵的宽和高的范围为 [1,50]。
     * 点击的位置只能是未被挖出的方块 ('M' 或者 'E')，这也意味着面板至少包含一个可点击的方块。
     * 输入面板不会是游戏结束的状态（即有地雷已被挖出）。
     * 简单起见，未提及的规则在这个问题中可被忽略。例如，当游戏结束时你不需要挖出所有地雷，考虑所有你可能赢得游戏或标记方块的情况。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minesweeper
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param board
     * @param click
     * @return
     */
    public static char[][] updateBoard(char[][] board, int[] click) {
        return null;
    }

    /**
     * 在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。
     *
     * 给定行数 N 和序数 K，返回第 N 行中第 K个字符。（K从1开始）
     * 例子:
     * 输入: N = 1, K = 1
     * 输出: 0
     *
     * 输入: N = 2, K = 1
     * 输出: 0
     *
     * 输入: N = 2, K = 2
     * 输出: 1
     *
     * 输入: N = 4, K = 5
     * 输出: 1
     *
     * 解释:
     * 第一行: 0
     * 第二行: 01
     * 第三行: 0110
     * 第四行: 01101001
     *
     * 注意：
     * N 的范围 [1, 30].
     * K 的范围 [1, 2^(N-1)].
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/k-th-symbol-in-grammar
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param N
     * @param K
     * @return
     */
    public static int kthGrammar(int N, int K) {
        int[] dp=new int[N+1];

        dp[2]=(K>>(N-2)+K&1)==1?0:1;
//        for(int i=2;i<=N;i++){
//            dp[i]=K>>(N-i+1)
//        }
//        return helper(N,K);
        return 0;
    }


    /**
     * 给定一个二叉树，找出其最小深度。
     *
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例:
     *
     * 给定二叉树 [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最小深度  2.
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        if(root.left==null^root.right==null){
            return 1+minDepth(root.left==null?root.right:root.left);
        }
        return 1+Math.min(minDepth(root.left),minDepth(root.right));
    }


    /**
     *给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
     *
     * 示例 1:
     *
     * 输入: "abab"
     *
     * 输出: True
     *
     * 解释: 可由子字符串 "ab" 重复两次构成。
     * 示例 2:
     *
     * 输入: "aba"
     *
     * 输出: False
     * 示例 3:
     *
     * 输入: "abcabcabcabc"
     *
     * 输出: True
     *
     * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/repeated-substring-pattern
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public static boolean repeatedSubstringPattern(String s) {
        if(s==null||s.length()==0){
            return false;
        }
        char[] cs =s.toCharArray();
        int l=0,in=0,len=cs.length;

        outer:for(int i=1;i<=len>>1;i++){
            if(len%i!=0){
                continue;
            }
            for(int j=i;j<len;++j){
                if(cs[j-i]!=cs[j]){
                    continue outer;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 快速排序
     * @param nums
     */
    public static void quickSort(int[] nums){
        sort(nums,0,nums.length-1);

        System.out.println(nums);
    }

    private static void sort(int[] nums, int l, int r) {
        int i=l,j=r,pivot=nums[l];
        /**
         * ->找到第一个比 pivot大的数   找到第一个比pivot小的数<-
         */
        while (i<j){
            while (nums[j]>pivot&&i<j)
                --j;
            nums[i]=nums[j];
            while (nums[i]<=pivot&&i<j)
                ++i;
            nums[j]=nums[i];
        }
        nums[i]=pivot;
        if(i>l)
            sort(nums,l,i-1);
        if(j<r)
            sort(nums,i+1,r);
    }

    /**
     * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
     *
     * 示例:
     *
     * 输入: [4, 6, 7, 7]
     * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
     * 说明:
     *
     * 给定数组的长度不会超过15。
     * 数组中的整数范围是 [-100,100]。
     * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/increasing-subsequences
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public static List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        return findSubsequence(res,null,nums,0);
    }

    private static List<List<Integer>> findSubsequence(List<List<Integer>> res,List<Integer> list, int[] nums, int i) {

        if(i>nums.length-1){
            if(list.size()>=2){
                res.add(list);
            }
            return res;
        }else{
            List<Integer> numList=new ArrayList<>();
            if(list!=null){
                numList.addAll(list);
            }
            int num=nums[i];
            while (i<nums.length&&nums[i++]<num){}

            findSubsequence(res,numList,nums,i);
            numList.add(num);
            findSubsequence(res,numList,nums,i);

            return res;
        }
    }

    class Solution {
        List<Integer> temp = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        public List<List<Integer>> findSubsequences(int[] nums) {
            dfs(0, Integer.MIN_VALUE, nums);
            return ans;
        }

        public void dfs(int cur, int last, int[] nums) {
            if (cur == nums.length) {
                if (temp.size() >= 2) {
                    ans.add(new ArrayList<Integer>(temp));
                }
                return;
            }
            if (nums[cur] >= last) {
                temp.add(nums[cur]);
                dfs(cur + 1, nums[cur], nums);
                temp.remove(temp.size() - 1);
            }
            if (nums[cur] != last) {
                dfs(cur + 1, last, nums);
            }
        }
    }

    /**
     *
     * @param nums
     * @return
     */
    public static int searchInsertIndex(int[] nums,int target){
        int i=0,j=nums.length-1;

        while (i<j){
            int pivot=(i+j)>>1;
            int val=nums[pivot];
            if(target>val){
                i=pivot+1;
            }else if(target<val){
                j=pivot;
            }else{
                return pivot;
            }
        }
        return i;
    }

    public static boolean repeatedSubstringPattern2(String s) {
        String t=s+s;
        return t.substring(1,t.length()-1).indexOf(s)!=-1;
    }

    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     *
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

     * 示例:
     *
     * 输入："23"
     * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     * 说明:
     * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits) {
        List<String> res=new ArrayList<>();
        helper(res,"",digits,0);
        return res;
    }

    private static void helper(List<String> res,String tmp, String digits, int i) {
        if(i==digits.length()){
            if (!"".equals(tmp))
                res.add(tmp);
            return ;
        }

        char[] m=mapFor(digits.charAt(i++));

        for(char c:m){
            helper(res,tmp+c,digits,i);
        }
    }

    public static char[] mapFor(char num){
        switch (num){
            case '2':
                return new char[]{'a','b','c'};
            case '3':
                return new char[]{'d','e','f'};
            case '4':
                return new char[]{'g','h','i'};
            case '5':
                return new char[]{'j','k','l'};
            case '6':
                return new char[]{'m','n','o'};
            case '7':
                return new char[]{'p','q','r','s'};
            case '8':
                return new char[]{'t','u','v'};
            case '9':
                return new char[]{'w','x','y','z'};
        }
        return null;
    }

    /**
     * 在二维平面上，有一个机器人从原点 (0, 0) 开始。给出它的移动顺序，判断这个机器人在完成移动后是否在 (0, 0) 处结束。
     *
     * 移动顺序由字符串表示。字符 move[i] 表示其第 i 次移动。机器人的有效动作有 R（右），L（左），U（上）和 D（下）。如果机器人在完成所有动作后返回原点，则返回 true。否则，返回 false。
     *
     * 注意：机器人“面朝”的方向无关紧要。 “R” 将始终使机器人向右移动一次，“L” 将始终向左移动等。此外，假设每次移动机器人的移动幅度相同。
     * 示例 1:
     * 输入: "UD"
     * 输出: true
     * 解释：机器人向上移动一次，然后向下移动一次。所有动作都具有相同的幅度，因此它最终回到它开始的原点。因此，我们返回 true。
     * 示例 2:
     *
     * 输入: "LL"
     * 输出: false
     * 解释：机器人向左移动两次。它最终位于原点的左侧，距原点有两次 “移动” 的距离。我们返回 false，因为它在移动结束时没有返回原点。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/robot-return-to-origin
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param moves
     * @return
     */
    public static boolean judgeCircle(String moves) {
        if(moves==null||moves.length()==0){
            return true;
        }
        int i=0,j=0,l=0;
        while (l<moves.length()){
            char c =moves.charAt(l++);
            switch (c){
                case 'R':
                    ++i;
                    break;
                case 'L':
                    --i;
                    break;
                case 'U':
                    ++j;
                    break;
                case 'D':
                    --j;
                    break;
            }
        }
        return i==0&&j==0;
    }

    /**
     * 初始时有 n 个灯泡关闭。 第 1 轮，你打开所有的灯泡。 第 2 轮，每两个灯泡你关闭一次。 第 3 轮，每三个灯泡切换一次开关（如果关闭则开启，如果开启则关闭）。
     * 第 i 轮，每 i 个灯泡切换一次开关。 对于第 n 轮，你只切换最后一个灯泡的开关。 找出 n 轮后有多少个亮着的灯泡。
     * 示例:
     * 输入: 3
     * 输出: 1
     * 解释:
     * 初始时, 灯泡状态 [关闭, 关闭, 关闭].
     * 第一轮后, 灯泡状态 [开启, 开启, 开启].
     * 第二轮后, 灯泡状态 [开启, 关闭, 开启].
     * 第三轮后, 灯泡状态 [开启, 关闭, 关闭].
     *
     * 你应该返回 1，因为只有一个灯泡还亮着。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/bulb-switcher
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public static int bulbSwitch(int n) {
        boolean[] flags=new boolean[n+1];//开是false 关true
        int res=n;

        for(int i=2;i<flags.length;i++){
            int j=1,m=0;
            while ((m=i*j++)< flags.length){
                res+=flags[m]?1:-1;
                flags[m]=!flags[m];
            }
        }
        return res;
    }

    /**
     *有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
     *
     * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，
     * 其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
     *
     * 最初，除 0 号房间外的其余所有房间都被锁住。
     *
     * 你可以自由地在房间之间来回走动。
     *
     * 如果能进入每个房间返回 true，否则返回 false。
     *
     * 示例 1：
     *
     * 输入: [[1],[2],[3],[]]
     * 输出: true
     * 解释:
     * 我们从 0 号房间开始，拿到钥匙 1。
     * 之后我们去 1 号房间，拿到钥匙 2。
     * 然后我们去 2 号房间，拿到钥匙 3。
     * 最后我们去了 3 号房间。
     * 由于我们能够进入每个房间，我们返回 true。
     * 示例 2：
     *
     * 输入：[[1,3],[3,0,1],[2],[0]]
     * 输出：false
     * 解释：我们不能进入 2 号房间。
     * 提示：
     *
     * 1 <= rooms.length <= 1000
     * 0 <= rooms[i].length <= 1000
     * 所有房间中的钥匙数量总计不超过 3000。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/keys-and-rooms
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param rooms
     * @return
     */
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set set=new HashSet<>();
        set.add(0);
        dfs(set,rooms.size(),rooms.get(0),rooms);
        return set.size()==rooms.size();
    }

    private static void dfs(Set set,int N,List<Integer> integers,List<List<Integer>> rooms) {
        if(integers!=null&&integers.size()>0){
            for(Integer i:integers){
                if(set.contains(i)){continue;}
                set.add(i);
                if(set.size()==N){return;}
                dfs(set,N,rooms.get(i),rooms);
            }
        }
    }

    /**
     * 给定一个表示分数的非负整数数组。 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，然后玩家 1 拿，…… 。
     * 每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。
     *
     * 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。

     * 示例 1：
     *
     * 输入：[1, 5, 2]
     * 输出：False
     * 解释：一开始，玩家1可以从1和2中进行选择。
     * 如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）可选。
     * 所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
     * 因此，玩家 1 永远不会成为赢家，返回 False 。
     * 示例 2：
     *
     * 输入：[1, 5, 233, 7]
     * 输出：True
     * 解释：玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
     *      最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 True，表示玩家 1 可以成为赢家。
     *  
     * 提示：
     *
     * 1 <= 给定的数组长度 <= 20.
     * 数组里所有分数都为非负数且不会大于 10000000 。
     * 如果最终两个玩家的分数相等，那么玩家 1 仍为赢家。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/predict-the-winner
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public static boolean PredictTheWinner(int[] nums) {
        int max=bfs(0,nums.length-1,0,0,true,nums);
        int sum=0;
        for(int i:nums){
            sum+=i;
        }
        return max>=(sum-max);
    }

    private static int bfs(int l, int r,int sum,int max,boolean A, int[] nums) {
        if(l>r){
            return Math.max(sum,max);
        }
        return Math.max(bfs(l+1,r,sum+(A?nums[l]:0),max,!A,nums),
                bfs(l,r-1,sum+(A?nums[r]:0),max,!A,nums));
    }

    /**
     * 递归的思路很好理解，就是让玩家每次取左边的数字或者右边的数字，然后先手环节加上该数字，对方环节减去该数字，最后得和为正说明玩家1赢，这个思路很容易理解，
     * 主要是动态规划得思路我觉得其他人都讲得不清楚，我就把自己得理解写一下。
     * class Solution {
     * public:
     *     bool PredictTheWinner(vector<int>& nums) {
     *         int sum = total(nums, 0, nums.size() - 1, 1);
     *         return sum >= 0;
     *     }
     *
     *     int total(vector<int>& nums, int start, int end, int turn) {
     *         if (start == end) {
     *             return nums[start] * turn;
     *         }
     *         int scoreStart = nums[start] * turn + total(nums, start + 1, end, -turn);
     *         int scoreEnd = nums[end] * turn + total(nums, start, end - 1, -turn);
     *         return max(scoreStart * turn, scoreEnd * turn) * turn;
     *     }
     * };
     *
     * 作者：yizhe-shi
     * 链接：https://leetcode-cn.com/problems/predict-the-winner/solution/c-di-gui-dp-jie-fa-by-yizhe-shi/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param args
     */

    /**
     *n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     *
     *
     *
     * 上图为 8 皇后问题的一种解法。
     *
     * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
     *
     * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/n-queens
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public static List<List<String>> solveNQueens(int n) {
        String[][] loc=new String[n+1][n+1];

        boolean[] row =new boolean[n+1];
        boolean[] col =new boolean[n+1];

        List<int[]> queues=new ArrayList<>();

        int[] tried=new int[n+1];//该行已经尝试过的皇后位置

        outer:for(int i=1;i<=n;++i){
            boolean flag=false;
            inner :for(int j=1;j<=n;++j){
                if(j<=tried[i]){
                    continue inner;
                }
                /**
                 * 同行同列同对角线不能重复
                 */
                if(!row[i]&&!col[j]&&check(queues,i,j)){
                    queues.add(new int[]{i,j});
                    row[i]=true;
                    col[j]=true;
                    flag=true;
                    tried[i]=j;//记录该行上次的皇后位子
//                    System.out.println("找到皇后:"+i+","+j);
                }
            }
            if(!flag){
                if(i==1&&tried[i]==n){
                    break outer;
                }
//                System.out.println("一列未找到:"+i);
                --i;//这样下去不满足得重新试一下
                int preI=0;
                while ((preI=tried[i]+1)>n&&i>=1){
                    --i;
                }
                if(i>=1){
                    tried[i]=preI;
//                    System.out.println("nextTry:"+i);
                    //清除后面已经尝试的皇后
                    int cur=i;
                    while (cur<=n){
                        if(cur+1<=n){
                            tried[cur+1]=0;
                        }

                        Iterator iterator=queues.iterator();
                        while (iterator.hasNext()){
                            int[] tmp=(int[])iterator.next();
                            if(tmp[0]==cur){
//                                System.out.println("row"+cur+"col"+tmp[1]+"置为false并移除");
                                row[cur]=false;
                                col[tmp[1]]=false;
                                iterator.remove();
                            }
                        }
                        ++cur;
                    }
                    --i;
                }
            }
            if(i==8&&queues.size()==8){
                for(int[] is:queues){
                    loc[is[0]][is[1]]="Q";
                }
                System.out.println("******************************");
                for(int k=1;k<=n;++k){
                    for(int j=1;j<=n;++j){
                        if(!"Q".equals(loc[k][j])){
                            System.out.print(". ");
                        }else{
                            System.out.print("Q ");
                        }
                    }
                    System.out.println("");
                }

                System.out.println("******************************");
                row=new boolean[n+1];
                col=new boolean[n+1];
                queues=new ArrayList<>();
                loc=new String[n+1][n+1];
                tried=new int[n+1];
            }
        }

//        for(int[] is:queues){
//            loc[is[0]][is[1]]="Q";
//        }
//
//        for(int i=1;i<=n;++i){
//            for(int j=1;j<=n;++j){
//                if(!"Q".equals(loc[i][j])){
//                    System.out.print(". ");
//                }else{
//                    System.out.print("Q ");
//                }
//            }
//            System.out.println("");
//        }
        return null;
    }

    public static boolean check(List<int[]> queues,int i,int j){
        for(int[] is:queues){
            if(Math.abs(is[0]-i)==Math.abs(is[1]-j)){
                return false;
            }
        }
        return true;
    }

    static List<List<String>> res=new ArrayList<>();
    static int count=1;
    public static List<List<String>> solveNQueens2(int n) {


//        String[][] loc=new String[n+1][n+1];
        List<String> rows=new ArrayList<>();
        int[] tried=new int[n+1];
        Map<Integer,Integer> queens=new HashMap<>();
        boolean[] row=new boolean[n+1];
        boolean[] col=new boolean[n+1];

        findQueens(rows,row,col,tried,queens,n);
        return res;
    }

    private static void findQueens(List<String> rows,boolean[] row,boolean[] col,int[] tried,Map<Integer,Integer> queens,int n) {

        for(int i=1;i<=n;++i){
            boolean flag=false;
            for(int j=1;j<=n;++j){
                if(j<=tried[i]){
                    continue;
                }
                if(!row[i]&&!col[j]&&checkValid(queens,i,j)){
                    queens.put(i,j);
                    row[i]=true;
                    col[j]=true;
                    flag=true;
                    tried[i]=j;
                }
            }
            if(!flag){//该行没找到回溯,则>=i的皇后清除，i-1的tried+1
                int tmp=i-1;
                while (tried[tmp]==n&&tmp>=1){
                    --tmp;
                }

                if(tmp>=1){
                    int clear=tmp;
                    while (clear<=n){
                        row[clear]=false;
                        if(queens.containsKey(clear)){
                            col[queens.get(clear)]=false;
                        }
                        queens.remove(clear);
                        tried[clear+1<=n?(clear+1):n]=0;
                        ++clear;
                    }
                    i=--tmp;
                }else{
                    return;
                }
            }
            if(i==n&&queens.size()==n){//一次完整的皇后
                System.out.println("************第"+count+++"个******************");
                for(int k=1;k<=n;++k){
                    for(int j=1;j<=n;++j){
                        if(row[k]&&j==queens.get(k)){
                            System.out.print("Q");
                        }else{
                            System.out.print(".");
                        }
                    }
                    System.out.println("");
                }

                System.out.println("******************************");
                row[n]=false;
                col[queens.get(n)]=false;
                queens.remove(n);
                i=n-1;//去掉最后一个皇后

            }
        }
    }

    public static boolean checkValid(Map<Integer,Integer> queens,int i,int j){
        for(Map.Entry<Integer,Integer> entry:queens.entrySet()){
            if(Math.abs(i- entry.getKey())==Math.abs(j-entry.getValue())){
                return false;
            }
        }
        return true;
    }

    /**
     * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
     * 示例 1:
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     * 示例 2:
     * 输入: nums = [1], k = 1
     * 输出: [1]
     * 提示：
     *
     * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
     * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
     * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
     * 你可以按任意顺序返回答案。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param k
     * @return
     */
    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> map=new HashMap<>();

        for(int i:nums){
            map.put(i,map.getOrDefault(i,0)+1);
        }

        List<Map.Entry<Integer,Integer>> entryList=new ArrayList<>(map.entrySet()) ;
        Collections.sort(entryList, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        int[] res=new int[k];

        for(int i=0;i<k;i++){
            res[i]=entryList.get(i).getKey();
        }
        return res;
    }

    public static int[] topKFrequent2(int[] nums, int k) {


        PriorityQueue<Integer> pq=new PriorityQueue<>(k);

        for(int i:nums){
            pq.offer(i);
        }

        System.out.println("23233");

        return null;
    }

    /**
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     *
     * 示例:
     *
     * 输入: n = 4, k = 2
     * 输出:
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/combinations
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @param k
     * @return
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> collection=new ArrayList<>();
        combineHelper(res,collection,1,0,n,k);
        return res;
    }

    private static void combineHelper(List<List<Integer>> res, List<Integer> collection, int cur,int i,int n, int k) {
        if(i==k){
            res.add(new ArrayList<>(collection));
            return;
        }
        if(cur>n){
            return;
        }
        collection.add(cur);
        combineHelper(res,collection,cur+1,i+1,n,k);
        collection.remove(collection.size()-1);
        combineHelper(res,collection,cur+1,i,n,k);
    }

    /**
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的数字可以无限制重复被选取。
     *
     * 说明：
     *
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。 
     * 示例 1：
     *
     * 输入：candidates = [2,3,6,7], target = 7,
     * 所求解集为：
     * [
     *   [7],
     *   [2,2,3]
     * ]
     * 示例 2：
     *
     * 输入：candidates = [2,3,5], target = 8,
     * 所求解集为：
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     * 提示：
     *
     * 1 <= candidates.length <= 30
     * 1 <= candidates[i] <= 200
     * candidate 中的每个元素都是独一无二的。
     * 1 <= target <= 500
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/combination-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res=new ArrayList<>();
        LinkedList<Integer> nums=new LinkedList<>();
        Arrays.sort(candidates);
        find(res,nums,0,candidates,target,0);
        return res;
    }

    public static void find(List<List<Integer>> res,LinkedList<Integer> nums,int sumed,int[] candidates, int target,int n){
        int diff;
        if((diff=target-sumed)>0){
            for(int i=n;i<candidates.length&&candidates[i]<=diff;++i){
                nums.addLast(candidates[i]);
                find(res,nums,sumed+candidates[i],candidates,target,i);
                nums.removeLast();
            }
        }else if(diff==0){
            res.add(new ArrayList<>(nums));
        }
    }

    /**
     * 在1之上加条件，不能使用重复元素
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res=new ArrayList<>();
        LinkedList<Integer> nums=new LinkedList<>();
        Arrays.sort(candidates);
        find2(res,nums,0,candidates,target,0);
        return res;
    }

    public static void find2(List<List<Integer>> res,LinkedList<Integer> nums,int sumed,int[] candidates, int target,int n){
        int diff;
        if((diff=target-sumed)>0){
            for(int i=n;i<candidates.length&&candidates[i]<=diff;++i){
                nums.addLast(candidates[i]);
                find2(res,nums,sumed+candidates[i],candidates,target,i+1);
                nums.removeLast();
            }
        }else if(diff==0){
            res.add(new ArrayList<>(nums));
        }
    }

    /**
     * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     * 说明：
     * 所有数字都是正整数。
     * 解集不能包含重复的组合。 
     * 示例 1:
     *
     * 输入: k = 3, n = 7
     * 输出: [[1,2,4]]
     * 示例 2:
     *
     * 输入: k = 3, n = 9
     * 输出: [[1,2,6], [1,3,5], [2,3,4]]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/combination-sum-iii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param k
     * @param n
     * @return
     */
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res=new ArrayList<>();
        LinkedList<Integer> nums=new LinkedList<>();
        int[] candidates=new int[]{1,2,3,4,5,6,7,8,9};
        Arrays.sort(candidates);
        find3(res,nums,0,candidates,n,0,k);
        return res;
    }

    public static void find3(List<List<Integer>> res,LinkedList<Integer> nums,int sumed,int[] candidates, int target,int n,int k){
        if(nums.size()>k){return;}
        int diff;
        if((diff=target-sumed)>0){
            for(int i=n;i<candidates.length&&candidates[i]<=diff;++i){
                nums.addLast(candidates[i]);
                find3(res,nums,sumed+candidates[i],candidates,target,i+1,k);
                nums.removeLast();
            }
        }else if(diff==0&&nums.size()==k){
            res.add(new ArrayList<>(nums));
        }
    }

    /**
     * 给定一个二叉树，返回它的中序 遍历。
     *
     * 示例:
     *
     * 输入: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     *
     * 输出: [1,3,2]
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        Deque<TreeNode> nodeDeque=new ArrayDeque<>();
        List<Integer> res=new LinkedList<>();

        while (root!=null||!nodeDeque.isEmpty()){
            while (root!=null){
                nodeDeque.offerFirst(root);
                root=root.left;
            }
            root=nodeDeque.poll();
            res.add(root.val);
            root=root.right;
        }
        return res;
    }


    static List<Integer> nums=new ArrayList<>();
    static LinkedList<TreeNode> nodes=new LinkedList<>();
    //
    public static int getMinimumDifference(TreeNode root) {
        nodes.addFirst(root);
        root=root.left;
        while(!nodes.isEmpty()||root!=null){
            while(root!=null){
                nodes.addFirst(root);
                root=root.left;
            }
            TreeNode node=nodes.poll();
            nums.add(node.val);
            root=node.right;
        }

        int last=nums.get(0),diff=Integer.MAX_VALUE;
        for(int i=1;i<nums.size();++i){
            int cur=nums.get(i);
            diff=Math.min(diff,Math.abs(cur-last));
            last=cur;
        }

        return diff;
    }



    static  TreeNode root=new TreeNode(1,
            null,
            new TreeNode(3,
                    new TreeNode(2,null,null),
                    null));


    /**
     * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
     *
     * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
     *
     * 以数组形式返回答案。
     * 示例 1：
     * 输入：nums = [8,1,2,2,3]
     * 输出：[4,0,1,1,3]
     * 解释：
     * 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
     * 对于 nums[1]=1 不存在比它小的数字。
     * 对于 nums[2]=2 存在一个比它小的数字：（1）。
     * 对于 nums[3]=2 存在一个比它小的数字：（1）。
     * 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
     * 示例 2：
     * 输入：nums = [6,5,4,8]
     * 输出：[2,1,0,3]
     * 示例 3：
     * 输入：nums = [7,7,7,7]
     * 输出：[0,0,0,0]
     * 提示：
     * 2 <= nums.length <= 500
     * 0 <= nums[i] <= 100
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] res=new int[101];

        for(int i:nums){
            res[i]++;
        }

        int bigger=0;
        for(int i=0;i<res.length;++i){
            int c;
            if((c=res[i])>0){
                res[i]=bigger;
                bigger+=c;
            }
        }

        for(int i=0;i<nums.length;++i){
            nums[i]=res[nums[i]];
        }
        return nums;
    }

    /**
     * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
     *
     * B.length >= 3
     * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
     * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
     *
     * 给出一个整数数组 A，返回最长 “山脉” 的长度。
     *
     * 如果不含有 “山脉” 则返回 0。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：[2,1,4,7,3,2,5]
     * 输出：5
     * 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
     * 示例 2：
     *
     * 输入：[2,2,2]
     * 输出：0
     * 解释：不含 “山脉”。
     *  
     *
     * 提示：
     *
     * 0 <= A.length <= 10000
     * 0 <= A[i] <= 10000
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-mountain-in-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param A
     */
    public static int longestMountain(int[] A) {
        int len=A.length,res=0;
        if(len<3){return 0;}

        int[] left=new int[len];
        for(int i=1;i<len;++i){
            left[i]=A[i-1]<A[i]?left[i]+1:0;
        }

        int[] right=new int[len];
        for(int i=len-2;i>=0;--i){
            right[i]=A[i]>A[i+1]?right[i]+1:0;
        }

        for(int i=0;i<len;++i){
            res=Math.max(res,left[i]+right[i]+1);
        }
        return res>=3?res:0;
    }

    public static void main(String[] args) throws Exception {
        quickSort(new int[]{3,5,9,3,4,10,3,5,6});

        Son son=new Son();
        son.b();
    }

    /**
     * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，
     * 因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
     *
     * 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend，
     * 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，
     * 所需的弓箭的最小数量。
     *
     * 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。

     * 示例 1：
     * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
     * 输出：2
     * 解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球
     * 示例 2：
     *
     * 输入：points = [[1,2],[3,4],[5,6],[7,8]]
     * 输出：4
     * 示例 3：
     *
     * 输入：points = [[1,2],[2,3],[3,4],[4,5]]
     * 输出：2
     * 示例 4：
     *
     * 输入：points = [[1,2]]
     * 输出：1
     * 示例 5：
     *
     * 输入：points = [[2,3],[2,3]]
     * 输出：1
     * 提示：
     * 0 <= points.length <= 104
     * points[i].length == 2
     * -231 <= xstart < xend <= 231 - 1
     * @param points
     * @return
     */
    public static int findMinArrowShots(int[][] points) {
        return  0;
    }

    /**
     * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
     *
     * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
     *
     * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，
     * 两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
     *
     *  
     *
     * 示例 1：
     *
     * 输入：R = 1, C = 2, r0 = 0, c0 = 0
     * 输出：[[0,0],[0,1]]
     * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
     * 示例 2：
     *
     * 输入：R = 2, C = 2, r0 = 0, c0 = 1
     * 输出：[[0,1],[0,0],[1,1],[1,0]]
     * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
     * [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
     * 示例 3：
     *
     * 输入：R = 2, C = 3, r0 = 1, c0 = 2
     * 输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
     * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
     * 其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
     *  
     *
     * 提示：
     *
     * 1 <= R <= 100
     * 1 <= C <= 100
     * 0 <= r0 < R
     * 0 <= c0 < C
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/matrix-cells-in-distance-order
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] locations=new int[R*C][2];

        int index=0;
        for(int i=0;i<R;++i){
            for(int j=0;j<C;++j){
                locations[index][0]=i;
                locations[index++][1]=j;
            }
        }

        Arrays.sort(locations,new Comparator<int[]>(){
            @Override
            public int compare(int[] a,int[] b){
                return Math.abs(a[0]-r0)+Math.abs(a[1]-c0)
                        -Math.abs(b[0]-r0)-Math.abs(b[1]-c0);
            }
        });
        return locations;
    }

    /**
     * 返回链表倒数第K个节点
     * @param head
     * @param k
     * @return
     */
    public static ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast=head,slow=head;

        while(k-->0){
            fast=fast.next;
        }

        while((fast=fast.next)!=null){
            slow=slow.next;
        }
        return slow;
    }

    /**
     * 一个数组，把奇数放前面，偶数放后面
     * @param nums
     * @return
     */
    public static int[] exchange(int[] nums) {

        int i=0,j=nums.length-1;

        while(i<j){
            while(nums[i]%2==1&&i<j){i++;}
            while(nums[j]%2==0&&i<j){j--;}

            int tmp=nums[i];
            nums[i]=nums[j];
            nums[j]=tmp;
        }
        return nums;
    }

    public static void nextPermutation(int[] nums) {

        int index=-1;
        outer:for(int i=nums.length-1;i>=0;--i){
            for(int j=i-1;j>=0;--j){
                if(nums[j]<nums[i]){
                    index=j;

                    int tmp =nums[i];
                    nums[i]=nums[j];
                    nums[j]=tmp;
                    break outer;
                }
            }
        }
        if(index==-1){
            Arrays.sort(nums);
        }else{
            Arrays.sort(nums,index+1,nums.length);
        }
    }

    static int result=Integer.MAX_VALUE;
    static boolean[] used;
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        used=new boolean[wordList.size()];
        used[wordList.size()-1]=true;

        Map<String,List<Integer>> mappings=generate(wordList);
        helper(beginWord,endWord,wordList,mappings,0);
        return result==Integer.MAX_VALUE?0:result;
    }

    static void helper(String beginWord, String endWord,List<String> wordList,Map<String,List<Integer>> mappings,int step){
        if(beginWord.equals(endWord)){
            result=Math.min(result,step+1);return;
        }
        List<Integer> next=mappings.get(beginWord);
        if(next==null){return;}
        for(Integer i:next){
            if(used[i]){continue;}
            used[i]=true;
            helper(wordList.get(i),endWord,wordList,mappings,step+1);
            used[i]=false;
        }
    }

    static Map<String,List<Integer>> generate(List<String> wordList){
        Map<String,List<Integer>> res=new HashMap<>();

        int size=wordList.size();
        for(int i=0;i<size;++i){//每个单词遍历一次
            List<Integer> arrList=new ArrayList<>();
            String word1=wordList.get(i);
            int[] counts=new int[size];

            for(int j=0;j<size;++j){
                if(i==j){continue;}
                for(int k=0;k<word1.length();++k){
                    if(word1.charAt(k)!=wordList.get(j).charAt(k)){++counts[j];}
                }
            }
            for(int ci=0;ci<counts.length;++ci){
                if(counts[ci]==1){arrList.add(ci);}
            }
            res.put(word1,arrList);
        }
        return res;
    }

    /**
     * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
     * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
     * 示例 1：
     * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
     * 输出：[[1,5],[6,9]]
     * 示例 2：
     * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     * 输出：[[1,2],[3,10],[12,16]]
     * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
     * 注意：输入类型已在 2019 年 4 月 15 日更改。请重置为默认代码定义以获取新的方法签名。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/insert-interval
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param intervals
     * @param newInterval
     * @return
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {

        int[] last=new int[2];
        Arrays.fill(last,Integer.MIN_VALUE);
        boolean flag=false;

        for(int[] itv:intervals){
            itv[0]=newInterval[1]>=itv[0]?Math.min(itv[0],newInterval[0]):itv[0];
            itv[1]=newInterval[0]<=itv[1]?Math.max(itv[1],newInterval[1]):itv[1];

            if(itv[0]<=last[1]){
                itv[0]=last[0];
                Arrays.fill(last,Integer.MIN_VALUE);
            }
            last=itv;
            if(itv[0]<=newInterval[0]&&itv[1]>=newInterval[1]){flag=true;}
        }

        List<int[]> arrList=new ArrayList<>();
        for(int[] itv:intervals){
            if(itv[0]!=Integer.MIN_VALUE){
                if(!flag){
                    if(itv[0]>newInterval[0]&&(arrList.size()==0||arrList.get(arrList.size()-1)[0]<newInterval[0])){
                        arrList.add(newInterval);
                    }
                }
                arrList.add(itv);
            }
        }

        int[][] res=new int[arrList.size()][2];
        for(int i=0;i<res.length;++i){
            res[i]=arrList.get(i);
        }
        return res;
    }

    /**
     * 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
     *
     * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
     *
     * A.length >= 3
     * 在 0 < i < A.length - 1 条件下，存在 i 使得：
     * A[0] < A[1] < ... A[i-1] < A[i]
     * A[i] > A[i+1] > ... > A[A.length - 1]
     *  
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/valid-mountain-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param A
     * @return
     */
    public static boolean validMountainArray(int[] A) {
        int len=A.length;
        int[] left=new int[len];
        for(int i=1;i<len;++i){
            left[i]=A[i]>A[i-1]?left[i-1]+1:0;
        }

        int[] right=new int[len];
        for(int i=len-2;i>=0;--i){
            right[i]=A[i]>A[i+1]?right[i+1]+1:0;
        }

        for(int i=0;i<len;++i){
            if(left[i]>0&&right[i]>0){
                return true;
            }
        }
        return false;
    }

    public static Integer aweweew(){
        int i=1;
        try {

            return i;
        }catch (Exception e){

        }finally {
            i=2;
        }
        return null;
    }

    static final int resizeStamp(int n) {
        return Integer.numberOfLeadingZeros(n) | (1 << 15);
    }

    public static int numberOfLeadingZeros(int i) {
        // HD, Figure 5-6
        if (i == 0)
            return 32;
        int n = 1;
        if (i >>> 16 == 0) { n += 16; i <<= 16; }
        if (i >>> 24 == 0) { n +=  8; i <<=  8; }
        if (i >>> 28 == 0) { n +=  4; i <<=  4; }
        if (i >>> 30 == 0) { n +=  2; i <<=  2; }
        n -= i >>> 31;
        return n;
    }

     final  String name;
    {
        name="2323";
    }

    RemoveDuplicates(){
//        name="2332";
    }

    public static void main22(String[] args) {

//
//        //查看对象外部信息
//        System.out.println(GraphLayout.parseInstance(map).toPrintable());
//
//        //获取对象总大小
//        System.out.println("size : " + GraphLayout.parseInstance(map).totalSize());

//        Integer integer=new Integer(1);
//        System.out.println(GraphLayout.parseInstance(integer).totalSize());
//
//
//        System.out.println(VM.current().details());


        System.out.println("********************************************************");
        ConcurrentHashMap concurrentHashMap=new ConcurrentHashMap();

//        System.out.println(ClassLayout.parseInstance(concurrentHashMap).toPrintable());

    }

}

/**
 * 现在有两种线程，氧 oxygen 和氢 hydrogen，你的目标是组织这两种线程来产生水分子。
 *
 * 存在一个屏障（barrier）使得每个线程必须等候直到一个完整水分子能够被产生出来。
 *
 * 氢和氧线程会被分别给予 releaseHydrogen 和 releaseOxygen 方法来允许它们突破屏障。
 *
 * 这些线程应该三三成组突破屏障并能立即组合产生一个水分子。
 *
 * 你必须保证产生一个水分子所需线程的结合必须发生在下一个水分子产生之前。
 *
 * 换句话说:
 *
 * 如果一个氧线程到达屏障时没有氢线程到达，它必须等候直到两个氢线程到达。
 * 如果一个氢线程到达屏障时没有其它线程到达，它必须等候直到一个氧线程和另一个氢线程到达。
 * 书写满足这些限制条件的氢、氧线程同步代码。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: "HOH"
 * 输出: "HHO"
 * 解释: "HOH" 和 "OHH" 依然都是有效解。
 * 示例 2:
 *
 * 输入: "OOHHHH"
 * 输出: "HHOHHO"
 * 解释: "HOHHHO", "OHHHHO", "HHOHOH", "HOHHOH", "OHHHOH", "HHOOHH", "HOHOHH" 和 "OHHOHH" 依然都是有效解。
 *  
 *
 * 提示：
 *
 * 输入字符串的总长将会是 3n, 1 ≤ n ≤ 50；
 * 输入字符串中的 “H” 总数将会是 2n 。
 * 输入字符串中的 “O” 总数将会是 n 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/building-h2o
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class H2O {

    private volatile int hydrogens=0;

    public H2O() {

    }

    public synchronized void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        while (hydrogens == 2) {
            this.wait();
            continue;
        }
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        hydrogens++;
        this.notify();
    }

    public synchronized void oxygen(Runnable releaseOxygen) throws InterruptedException {

        while (hydrogens != 2) {
            this.wait();
            continue;
        }
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        hydrogens=0;
        this.notify();
    }
}

/**
 * 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
 *
 * 如果这个数字可以被 3 整除，输出 "fizz"。
 * 如果这个数字可以被 5 整除，输出 "buzz"。
 * 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
 * 例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。
 *
 * 假设有这么一个类：
 *
 * class FizzBuzz {
 *   public FizzBuzz(int n) { ... }               // constructor
 *   public void fizz(printFizz) { ... }          // only output "fizz"
 *   public void buzz(printBuzz) { ... }          // only output "buzz"
 *   public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
 *   public void number(printNumber) { ... }      // only output the numbers
 * }
 * 请你实现一个有四个线程的多线程版  FizzBuzz， 同一个 FizzBuzz 实例会被如下四个线程使用：
 *
 * 线程A将调用 fizz() 来判断是否能被 3 整除，如果可以，则输出 fizz。
 * 线程B将调用 buzz() 来判断是否能被 5 整除，如果可以，则输出 buzz。
 * 线程C将调用 fizzbuzz() 来判断是否同时能被 3 和 5 整除，如果可以，则输出 fizzbuzz。
 * 线程D将调用 number() 来实现输出既不能被 3 整除也不能被 5 整除的数字。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fizz-buzz-multithreaded
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class FizzBuzz {

    private int n;

    private volatile int current=1;

    private volatile int type=4;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {

        while (current<=n){
            synchronized (this){
                if (type!=1){
                    this.wait();
                    continue;
                }
                printFizz.run();
                current++;
                updateType();
                this.notifyAll();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (current<=n){
            synchronized (this){
                if (type!=2){
                    this.wait();
                    continue;
                }
                printBuzz.run();
                current++;
                updateType();
                this.notifyAll();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (current<=n){
            synchronized (this){
                if (type!=3){
                    this.wait();
                    continue;
                }
                printFizzBuzz.run();
                current++;
                updateType();
                this.notifyAll();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (current<=n){
            synchronized (this){
                if (type!=4){
                    this.wait();
                    continue;
                }
                printNumber.accept(current);
                current++;
                updateType();
                this.notifyAll();
            }
        }
    }

    public void updateType(){
        if(current%3==0){
            if(current%5==0){
                type=3;
            }else{
                type=1;
            }
        }else{
            if(current%5==0){
                type=2;
            }else{
                type=4;
            }
        }
    }
}

/**
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 *
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。

 * 示例 1:
 *
 * 输入: n = 1
 * 输出: "foobar"
 * 解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
 * 示例 2:
 *
 * 输入: n = 2
 * 输出: "foobarfoobar"
 * 解释: "foobar" 将被输出两次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-foobar-alternately
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class FooBar {
    private int n;

    private volatile boolean flag=false;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.
            synchronized (this){
                if(flag){
                   this.wait();
                }
                printFoo.run();
                flag = true;
                this.notify();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printBar.run() outputs "bar". Do not change or remove this line.
            synchronized (this){
                if(!flag){
                    this.wait();
                }
                printBar.run();
                flag = false;
                this.notify();
            }
        }
    }
}

class FooBar2 {
    private int n;

    private volatile boolean flag=false;

    public FooBar2(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.
            synchronized (this){
                if(flag){
                    this.wait();
                }
                printFoo.run();
                flag = true;
                this.notify();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printBar.run() outputs "bar". Do not change or remove this line.
            synchronized (this){
                if(!flag){
                    this.wait();
                }
                printBar.run();
                flag = false;
                this.notify();
            }
        }
    }
}

/**
 * 假设有这么一个类：
 *
 * class ZeroEvenOdd {
 *   public ZeroEvenOdd(int n) { ... }      // 构造函数
 *   public void zero(printNumber) { ... }  // 仅打印出 0
 *   public void even(printNumber) { ... }  // 仅打印出 偶数
 *   public void odd(printNumber) { ... }   // 仅打印出 奇数
 * }
 * 相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：
 *
 * 线程 A 将调用 zero()，它只输出 0 。
 * 线程 B 将调用 even()，它只输出偶数。
 * 线程 C 将调用 odd()，它只输出奇数。
 * 每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出："0102"
 * 说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
 * 示例 2：
 *
 * 输入：n = 5
 * 输出："0102030405"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-zero-even-odd
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class ZeroEvenOdd {

    /**
     * 0102
     * 四位分别step为1234
     */
    private Integer step =1;

    private int n;

    private Object lock=new Object();


    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int j=1;j<=n;j++){
            synchronized (lock) {
                while (step != 1 && step != 3) {
                    lock.wait();
                }
                printNumber.accept(0);
                step++;
                lock.notifyAll();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {

        for(int j=2;j<=n;j=j+2){
            synchronized (lock) {
                while (step != 4) {
                    lock.wait();
                }
                printNumber.accept(j);
                step=1;
                lock.notifyAll();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {

        for(int j=1;j<=n;j=j+2){
            synchronized (lock) {
                while (step != 2) {
                    lock.wait();
                }
                printNumber.accept(j);
                step = 3;
                lock.notifyAll();
            }
        }
    }
}

/**
 * 测试一直有问题的版本
 *
 */
class ZeroEvenOdd2 {
    /**
     * 0102
     * 四位分别step为1234
     */
    private Integer step =1;

    private int n;

    private int current=1;

    private Object lock=new Object();

    public ZeroEvenOdd2(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while (current < n) {
            synchronized (lock) {
                while (step != 1 && step != 3) {
                    lock.wait();
                }
                printNumber.accept(0);
                step++;
                lock.notifyAll();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while (current < n) {
            synchronized (lock) {
                while (step != 4) {
                    lock.wait();
                }
                printNumber.accept(current++);
                step = 1;
                lock.notifyAll();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {

        while (current < n) {
            synchronized (lock) {
                while (step != 2) {
                    lock.wait();
                }
                printNumber.accept(current++);
                step=3;
                lock.notifyAll();
            }
        }
    }
}

class Son extends Parent {


}

class Parent{
    private String name;

    public String address;

    public final String nis="23e3";

    public void a(){
        System.out.println("调用");
    }
    public void b(){
        this.a();
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
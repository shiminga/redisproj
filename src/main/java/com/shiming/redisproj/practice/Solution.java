package com.shiming.redisproj.practice;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    Deque<Node> nodes=new ArrayDeque<>();
    Node pre=null,cur=null;
    int size=0;
    public Node connect(Node root) {
        if(root==null){return null;}

        nodes.offer(root);

        while(!nodes.isEmpty()){
            size=nodes.size();
            pre=null;

            while(size-->0){
                if(pre!=null){pre.next=nodes.peek();}
                pre=nodes.poll();
                nodes.offer(pre.left);
                nodes.offer(pre.right);
            }
        }

        return root;
    }


    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
}

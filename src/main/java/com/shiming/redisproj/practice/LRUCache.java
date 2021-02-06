package com.shiming.redisproj.practice;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

        Node head;

        Node tail;

        int cap;

        Map<Integer,Node> cache=new HashMap<>();

        public LRUCache(int capacity) {
            this.cap=capacity;
        }

        public int get(int key) {
            Node node=cache.get(key);
            if(node==null){return -1;}

            moveToHead(node);
            //追加到头部
            return node.val;
        }

        public void put(int key, int value) {
            Node node=cache.getOrDefault(key,null);
            if(node==null){
                if(cache.size()==cap){
                    cache.remove(removeTail());
                }
                node=new Node(key,value);
                cache.put(key,node);
                moveToHead(node);
            }else{
                node.val=value;
                moveToHead(node);
            }
        }

        public static class Node{
            int key;
            int val;
            Node prev;
            Node next;

            Node(int key,int val){
                this.key=key;
                this.val=val;
            }

            public void setPrev(Node node){
                this.prev=node;
            }

            public void setNext(Node node){
                this.next=node;
            }
        }

        //将节点移到头部
        public void moveToHead(Node node){
            if(node==head){
                return;
            }else if(node==tail){
                tail=node.prev;
                tail.next=null;

                head.prev=node;
                node.next=head;
                node.prev=null;
                head=node;
            }else{
                if(node.prev==null){
                    if(head==null){
                        head=tail=node;
                    }else{
                        node.next=head;
                        head.prev=node;
                        head=node;
                    }
                    return;
                }
                Node p=node.prev;
                p.next=node.next;
                node.next.prev=p;

                node.next=head;
                head.prev=node;
                head=node;
            }
        }

        //移除尾节点
        public int removeTail(){
            if(head==null){
                return -1;
            }else if(head==tail){
                int v=head.val;
                head=tail=null;
                return v;
            }else{
                Node t=tail;
                tail=tail.prev;
                tail.next=null;
                t.prev=null;
                return t.val;
            }
        }


    public static void main(String[] args) {
        /**
         * ["LRUCache","put","get","put","get","get"]
         * [[1],[2,1],[2],[3,2],[2],[3]]
         */
        LRUCache l=new LRUCache(1);

        l.put(2,1);
        System.out.println(l.get(2));
        l.put(3,2);
        System.out.println(l.get(2));
        System.out.println(l.get(3));
    }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.p
 */
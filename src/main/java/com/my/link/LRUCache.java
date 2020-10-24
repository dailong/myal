package com.my.link;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {
    private int limit;
    private Node head;
    private Node end;

    private Map<String, Node> data = new HashMap<>();
    public LRUCache(int limit) {
        this.limit = limit;
    }


    public void put(String key, String value) {
        Node node = data.get(key);
        if (node == null) {
            if (limit <= data.size()) {
                String oldKey = removeNode(head);
                data.remove(oldKey);
            }
            Node newNode = new Node(key, value);
            data.put(key, newNode);
            addNode(newNode);
        } else {
            node.value = value;
            refresh(node);
        }

    }

    public String get(String key) {
        Node node = data.get(key);
        if (node == null) {
          return  null;
        }
        refresh(node);
        return node.value;

    }

    private String removeNode(Node node) {
        if (node == end) {
            end = end.pre;
        } else if (node == head) {
            head = head.next;
        } else {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        return node.key;
    }

    private void remove(String key) {
        Node node = data.get(key);
        removeNode(node);
        data.remove(key);
    }

    // 刷新被访问节点的位置
    private void refresh(Node node) {
        if (node == end) { // 访问尾节点，无需移动
            return;
        } else {
            // 移除节点
            removeNode(node);
            // 重新插入节点
            addNode(node);
        }

    }
    // 尾部插入节点
    private void addNode(Node node) {
        if (end != null) {
            end.next = node;
            node.pre = end;
            node.next = null;
        }
        end = node;
        if (head == null) {
            head = node;
        }

    }


    class Node {
        private String key;
        private String value;
        Node pre;
        Node next;
        Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public void print() {
        Node t = head;
        while (t != null) {
            System.out.print("(" + t.key + "," + t.value + ")-->");
            t = t.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put("001","111");
        lruCache.print();
        lruCache.put("002","222");
        lruCache.print();
        lruCache.put("003","333");
        lruCache.print();
        lruCache.put("004","444");
        lruCache.print();
        System.out.println(lruCache.get("004"));
        lruCache.print();
        lruCache.put("005","555");
        lruCache.print();
        System.out.println(lruCache.get("001"));
        lruCache.print();
        System.out.println(lruCache.get("002"));
        lruCache.print();


        // 采用jdk LinkedHashMap实现LRU
        LruCache lruCache1 = new LruCache(3);
        lruCache1.put("111","aaa");
        lruCache1.put("222","bbb");
        System.out.println(lruCache1.get("222"));
        lruCache1.put("333","ccc");
        lruCache1.put("444","ddd");
        System.out.println(lruCache1.get("333"));
        lruCache1.put("555","eee");

    }

    // 采用jdk LinkedHashMap实现LRU
    static class LruCache extends LinkedHashMap<String,String> {
        private int capacity;
        LruCache(int capacity) {
            super(capacity,1.0f,true);
            this.capacity = capacity;

        }

        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
           return this.size() > capacity ; // 如果缓存中的数量大于容量限制，则删除不常访问的元素
        }
    }


}

package linkedlist;

import java.util.HashMap;

// https://www.jianshu.com/p/b1ab4a170c3c
class LRUCache {
    private int mCapacity;
    private HashMap<Integer, LinkedNode> mMaps;
    private LinkedNode mHead;
    private LinkedNode mTail;

    public LRUCache(int capacity) {
        this.mCapacity = capacity;
        mMaps = new HashMap<>(capacity);
        mHead = new LinkedNode();
        mTail = new LinkedNode();
        mHead.next = mTail;
        mTail.pre = mHead;
    }

    public int get(int key) {
        if (mMaps.containsKey(key)) {
            LinkedNode linkedNode = mMaps.get(key);
            moveNodeToTail(linkedNode);
            return linkedNode.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (mMaps.containsKey(key)) {
            LinkedNode linkedNode = mMaps.get(key);
            linkedNode.value = value;
            moveNodeToTail(linkedNode);
        } else {
            LinkedNode newNode = new LinkedNode(key, value);
            if (mMaps.size() >= mCapacity) {
                LinkedNode linkedNode = removeHeadNode();
                mMaps.remove(linkedNode.key);
            }

            addNodeToTail(newNode);
            mMaps.put(key, newNode);
        }
    }

    private void moveNodeToTail(LinkedNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        addNodeToTail(node);
    }

    private void addNodeToTail(LinkedNode node) {
        LinkedNode last = mTail.pre;
        last.next = node;
        node.pre = last;
        node.next = mTail;
        mTail.pre = node;
    }

    private LinkedNode removeHeadNode() {
        LinkedNode first = mHead.next;
        LinkedNode second = first.next;
        mHead.next = second;
        second.pre = mHead;
        return first;
    }

}

class LinkedNode {
    int key;
    int value;
    LinkedNode pre;
    LinkedNode next;

    public LinkedNode() {}

    public LinkedNode(int key, int value){
        this.key = key;
        this.value = value;
    }
}

package linkedlist;

import util.CommonUtils;

public class BaseLinkedList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int newValue) {
            this.value = newValue;
        }
    }

    public static void main(String[] args) {
        BaseLinkedList baseLinkedList = new BaseLinkedList();
        Node head = buildLinkedList();
        CommonUtils.printLinkedList(head);
//        Node reverse1 = baseLinkedList.reverse1(head);
//        CommonUtils.printLinkedList(reverse1);
        Node reverse2 = baseLinkedList.reverse2(head);
        CommonUtils.printLinkedList(reverse2);

    }

    public static Node buildLinkedList() {
        Node head = new Node(7);
        Node temp = head;

        Node newNode1 = new Node(6);
        temp.next = newNode1;
        temp = newNode1;

        Node newNode2 = new Node(4);
        temp.next = newNode2;
        temp = newNode2;

        Node newNode3 = new Node(5);
        temp.next = newNode3;
        temp = newNode3;

        Node newNode4 = new Node(9);
        temp.next = newNode4;

        return head;
    }

    /**
     * 单链表反转（遍历方式）
     * 需要两个指针，一个指向下一个节点，一个指向前一个节点
     */
    public static Node reverse1(Node node) {
        Node next;
        Node pre = null;
        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    /**
     * 单链表反转（遍历方式）
     * 需要一个指针，一个指向下一个节点
     */
    public static Node reverse2(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        Node newNode = reverse2(node.next); // 递归到链表的最后一个元素，然后执行反转
        Node temp = node.next;
        temp.next = node;
        node.next = null;

        return newNode;
    }

    /**
     * 合并两个有序链表
     */
}

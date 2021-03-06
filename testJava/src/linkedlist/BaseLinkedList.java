package linkedlist;

import util.CommonUtils;

public class BaseLinkedList {

    public static void main(String[] args) {
        BaseLinkedList baseLinkedList = new BaseLinkedList();
        ListNode head = buildLinkedList();
        CommonUtils.printLinkedList(head);

        Integer aa = new Integer(3);
        baseLinkedList.test(aa);
        System.out.println("hzjhzj " + aa);

        ListNode reverse1 = baseLinkedList.reverse1(head);
        CommonUtils.printLinkedList(reverse1);
//        ListNode reverse2 = baseLinkedList.reverse2(head);
//        CommonUtils.printLinkedList(reverse2);

    }

    public static ListNode buildLinkedList() {
        ListNode head = new ListNode(7);
        ListNode temp = head;

        ListNode newNode1 = new ListNode(6);
        temp.next = newNode1;
        temp = newNode1;

        ListNode newNode2 = new ListNode(4);
        temp.next = newNode2;
        temp = newNode2;

        ListNode newNode3 = new ListNode(5);
        temp.next = newNode3;
        temp = newNode3;

        ListNode newNode4 = new ListNode(9);
        temp.next = newNode4;

        return head;
    }

    public static void test(Integer aa) {

    }

    /**
     * 单链表反转（遍历方式）
     * 需要两个指针，一个指向下一个节点，一个指向前一个节点
     */
    public static ListNode reverse1(ListNode node) {
        ListNode next;
        ListNode pre = null;
        while (node != null) {
            //System.out.println("hzjhzj node=" + node.val);
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
    public static ListNode reverse2(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode newNode = reverse2(node.next); // 递归到链表的最后一个元素，然后执行反转
        ListNode temp = node.next;
        temp.next = node;
        node.next = null;

        return newNode;
    }

    /**
     * 合并两个有序链表
     */
}

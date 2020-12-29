package leetcode.tree;

import linkedlist.ListNode;
import tree.TreeNode;

import java.util.*;

public class Num0403 {

    // 04.03. 特定深度节点链表
    public ListNode[] listOfDepth(TreeNode root) {

        List<ListNode> result = new ArrayList<>();
        if (root == null) {
            ListNode[] resultList = new ListNode[result.size()];
            return result.toArray(resultList);
        }
        Queue<TreeNode> tempQueue = new ArrayDeque();
        tempQueue.offer(root);
        while (tempQueue.size() > 0) {
            int queueSize = tempQueue.size();
            ListNode listNodeHead = null;
            ListNode listNodeCur = null;

            while (queueSize-- > 0){
                TreeNode queueHead = tempQueue.poll();
                ListNode node = new ListNode(queueHead.val);
                if (listNodeCur == null) {
                    listNodeCur = node;
                    listNodeHead = node;
                } else {
                    listNodeCur.next = node;
                    listNodeCur = node;
                }
                if (queueHead.left != null) {
                    tempQueue.offer(queueHead.left);
                }
                if (queueHead.right != null) {
                    tempQueue.offer(queueHead.right);
                }
            }

            result.add(listNodeHead);
        }

        ListNode[] resultList = new ListNode[result.size()];
        return result.toArray(resultList);
    }
}

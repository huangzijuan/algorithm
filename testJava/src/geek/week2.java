package geek;

import tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class week2 {

    /**
     * 94 二叉树中序遍历
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private void inorderHelper(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorderHelper(root.left, result);
        result.add(root.val);
        inorderHelper(root.right, result);
    }

    /**
     * 144 二叉树前序遍历
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    private void preorderHelper(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preorderHelper(root.left, result);
        preorderHelper(root.right, result);
    }

    /**
     * 590 N叉树后序遍历
     */
    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }

    private void postorderHelper(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }
        for (Node node: root.children) {
            postorderHelper(node, result);
        }
        result.add(root.val);
    }

    /**
     * 589 N叉树前序遍历
     */
    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    private void preorderHelper(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        for (Node node: root.children) {
            preorderHelper(node, result);
        }
    }

    /**
     * 429 N叉树层序遍历
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Node> tempQueue = new ArrayDeque();
        tempQueue.offer(root);
        while (tempQueue.size() > 0) {
            int curSize = tempQueue.size();
            List<Integer> curList = new ArrayList<>(); //当前层

            while (curSize-- > 0) {
                Node head = tempQueue.poll();
                if (head.children != null && head.children.size() > 0) {
                    for (Node node: head.children) {
                        tempQueue.offer(node);
                    }
                }
                curList.add(head.val);
            }

            result.add(curList);
        }
        return result;
    }

    /**
     * TOP K问题
     * 剑指40 最小的k个数
     */
    public int[] getLeastNumbers(int[] arr, int k) {

    }
}

package geek;

import tree.TreeNode;

import java.util.*;

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
        for (Node node : root.children) {
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
        for (Node node : root.children) {
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
                    for (Node node : head.children) {
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
     * 剑指40 最小的k个数
     * TOP K问题
     * 1. sort NLogN
     * 2. heap NLogK
     * 3. quick-sort
     * 方法一：堆实现（利用系统自带的PriorityQueue）
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            heap.add(arr[i]);
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = heap.poll();
        }
        return result;
    }

    /**
     * 方法二：快排实现（利用系统自带的）
     */
    public int[] getLeastNumbers2(int[] arr, int k) {
        if (arr.length == 0 || k == 0) {
         return new int[0];
        }
        if (arr.length == k) {
            return arr;
        }
        return search(arr, 0, arr.length - 1, k);
    }

    private int[] search(int[] arr, int low, int high, int k) {
        int partition = partition(arr, low, high);
        if (partition == k) {
            return Arrays.copyOf(arr, k);
        } else if (partition > k) {
            return search(arr, low, partition - 1, k);
        } else {
            return search(arr, partition + 1, high, k);
        }
    }

    // 选最左边为基准值，left始终指向从左边起第一个大于基准值的数，right始终指向从右边起第一个小于基准值的数
    private int partition(int[] arr, int left, int right) {
        int baseValue = arr[left];
        int i = left, j = right;
        while (i < j) {
            while (i < right && arr[i] <= baseValue) i++;
            while (j > left && arr[j] >= baseValue) j--;

            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[left] = arr[j];
        arr[j] = baseValue;
        return j;
    }
}

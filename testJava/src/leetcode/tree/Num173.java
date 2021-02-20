package leetcode.tree;

import tree.TreeNode;

import java.util.*;

public class Num173 {

    public static void main(String[] args) {
        Num173 num173 = new Num173();
    }

    // 173

//    private Stack<TreeNode> iteratorStack = new Stack<>();
//    public BSTIterator(TreeNode root) {
//        findLeft(root);
//    }
//
//    private void findLeft(TreeNode root) {
//        if (root != null) {
//            iteratorStack.push(root);
//            findLeft(root.left);
//        }
//    }
//
//    public int next() {
//        if (!iteratorStack.empty()) {
//            TreeNode topNode = iteratorStack.pop();
//            if (topNode.right != null) {
//                findLeft(topNode.right);
//            }
//            return topNode.val;
//        } else {
//            return Integer.MIN_VALUE;
//        }
//    }
//
//    public boolean hasNext() {
//        return !iteratorStack.empty();
//    }

    private Queue<TreeNode> iterator = new ArrayDeque<>();
    public BSTIterator(TreeNode root) {
        inOrder(root);
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        iterator.offer(root);
        inOrder(root.right);
    }

    public int next() {
        if (!iterator.isEmpty()) {
            return iterator.poll().val;
        } else {
            return Integer.MIN_VALUE;
        }
    }

    public boolean hasNext() {
        return !iterator.isEmpty();
    }

    // 199
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (size == 1) {
                    result.add(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
        }
        return result;
    }

    private List<Integer> result = new ArrayList<>();
    public List<Integer> rightSideView1(TreeNode root) {
        if (root == null) {
            return result;
        }
        rightSideViewDfs(root, 0);
        return result;
    }

    private void rightSideViewDfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (depth == result.size()) {
            result.add(root.val);
        }
        depth++;
        rightSideViewDfs(root.right, depth);
        rightSideViewDfs(root.left, depth);
    }

    // 222
    private int count = 0;
    public int countNodes(TreeNode root) {
        countNodesHelper(root);
        return count;
    }

    public void countNodesHelper(TreeNode root) {
        if (root == null) {
            return;
        }
        count++;
        countNodesHelper(root.left);
        countNodesHelper(root.right);
    }

    // 226
    public TreeNode invertTree(TreeNode root) {
        invertTreeHelper(root);
        return root;
    }

    public void invertTreeHelper(TreeNode root) {
        if (root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
    }

    // 235
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while ((root.val - p.val) * (root.val - q.val) > 0) {
            if (root.val - p.val > 0) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return root;
    }

    // 236 后序遍历为天然自底向上回溯方法
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        TreeNode right = lowestCommonAncestor1(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

    // 257
    private List<String> result1 = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        traversal(root, "");
        return result1;
    }

    public void traversal(TreeNode root, String path) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            result1.add(path + root.val);
        }
        traversal(root.left, path + root.val + "->");
        traversal(root.right, path + root.val + "->");
    }
}

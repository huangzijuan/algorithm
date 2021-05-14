package tree;

import geek.week3;
import util.CommonUtils;

import java.util.*;

public class BaseTree {

    public TreeNode buildTree(TreeNode root, int[] numbers, int index) {
        if (index >= numbers.length) {
            return null;
        }
        root = new TreeNode(numbers[index]);
        root.left = buildTree(null, numbers, 2*index + 1);
        root.right = buildTree(null, numbers, 2*index + 2);
        return root;
    }


    public static void main(String[] args) {
        BaseTree baseTree = new BaseTree();
        int[] nums = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8};
        TreeNode root = baseTree.buildTree(null, nums, 0);
//        List<Integer> result = baseTree.levelOrderTraversal1(root);
//        //List<Integer> result = baseTree.preorderTraversal2(root);
//        //List<Integer> result = baseTree.levelOrderTraversal(root);
//        CommonUtils.printArray3(result);
        week3 week3 = new week3();
        System.out.println("hzjhzj: " + week3.serialize1(root));

    }


    /**
     * 前序遍历二叉树（递归法）
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        ArrayList<Integer> resultList = new ArrayList<>();
        preTraversal(root, resultList);
        return resultList;
    }

    public void preTraversal(TreeNode root, ArrayList<Integer> resultList) {
        if (root == null) return;
        resultList.add(root.val);
        preTraversal(root.left, resultList);
        preTraversal(root.right, resultList);
    }

    /**
     * 前序遍历二叉树（非递归法）
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        ArrayList<Integer> resultList = new ArrayList<>();
        Stack<TreeNode> treeNodeStack = new Stack<>();
        treeNodeStack.push(root);
        while (!treeNodeStack.empty()) {
            TreeNode treeNode = treeNodeStack.pop();
            resultList.add(treeNode.val);
            if (treeNode.right != null) {
                treeNodeStack.push(treeNode.right);
            }
            if (treeNode.left != null) {
                treeNodeStack.push(treeNode.left);
            }
        }
        return resultList;
    }


    /**
     * 广度优先搜索遍历二叉树
     */
    public List<Integer> levelOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> tempQueue = new ArrayDeque<>();
        tempQueue.add(root);
        while (!tempQueue.isEmpty()) {
            TreeNode treeNode = tempQueue.poll();
            result.add(treeNode.val);
            if (treeNode.left != null) {
                tempQueue.offer(treeNode.left);
            }
            if (treeNode.right != null) {
                tempQueue.offer(treeNode.right);
            }
        }
        return result;
    }

    /**
     * 之字形打印二叉树
     */
    public List<Integer> levelOrderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> to = new Stack<>();
        to.push(root);
        levelOrderTraversal11(1, to, result);
        return result;
    }
    public void levelOrderTraversal11(int level, Stack<TreeNode> from, List<Integer> result) {
        if (from.isEmpty()) {
            return;
        }

        Stack<TreeNode> to = new Stack<>();

        while (!from.isEmpty()) {
            TreeNode treeNode = from.pop();
            result.add(treeNode.val);
            System.out.print(" " + treeNode.val);
            if (level % 2 == 0) {
                if (treeNode.left != null) {
                    to.push(treeNode.left);
                }

                if (treeNode.right != null) {
                    to.push(treeNode.right);
                }
            } else {
                if (treeNode.right != null) {
                    to.push(treeNode.right);
                }

                if (treeNode.left != null) {
                    to.push(treeNode.left);
                }
            }
        }
        System.out.println();
        levelOrderTraversal11(++level, to, result);
    }

}

package leetcode.tree;

import jdk.nashorn.internal.objects.NativeUint8Array;
import tree.TreeNode;

import java.util.*;

public class Num98 {

    // 98是否是二叉搜索树
    double preNodeVal = -Long.MAX_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (isValidBST(root.left)) {
            if (preNodeVal >= root.val) {
                return false;
            }
            preNodeVal = root.val;
            return isValidBST(root.right);
        }

        return false;
    }


    // 99
    TreeNode firstNode;
    TreeNode secondNode;
    TreeNode preNode = new TreeNode(-Integer.MAX_VALUE);
    public void recoverTree(TreeNode root) {
        inOrder(root);

        int temp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = temp;
    }

    public void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        if (firstNode == null && preNode.val > root.val) {
            firstNode = preNode;
        }
        if (firstNode != null && preNode.val > root.val) {
            secondNode = root;
        }
        preNode = root;
        inOrder(root.right);
    }

    // 100
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p != null && q != null) {
            return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }

    // 101
    public boolean isSym(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && isSym(p.left, q.right) && isSym(p.right, q.left);
    }

    // 102 层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> tempQueue = new ArrayDeque();
        tempQueue.offer(root);
        while (tempQueue.size() > 0) {
            int queueSize = tempQueue.size();
            List<Integer> list = new ArrayList<>();

            while (queueSize-- > 0){
                TreeNode queueHead = tempQueue.poll();
                list.add(queueHead.val);
                if (queueHead.left != null) {
                    tempQueue.offer(queueHead.left);
                }
                if (queueHead.right != null) {
                    tempQueue.offer(queueHead.right);
                }
            }

            result.add(list);
        }

        return result;
    }

    // 103 锯齿形遍历
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> tempStack = new Stack();
        tempStack.push(root);

        zigzagLevelHelper(tempStack, 1, result);

        return result;
    }

    private void zigzagLevelHelper(Stack<TreeNode> fromStack, int orderLevel, List<List<Integer>> result) {
        if (fromStack.isEmpty()) {
            return;
        }
        Stack<TreeNode> toStack = new Stack<>();
        int size = fromStack.size();
        List<Integer> list = new ArrayList<>();

        while (size-- > 0){
            TreeNode queueHead = fromStack.pop();
            list.add(queueHead.val);
            if (orderLevel % 2 == 0) {
                if (queueHead.right != null) {
                    toStack.push(queueHead.right);
                }
                if (queueHead.left != null) {
                    toStack.push(queueHead.left);
                }
            } else {
                if (queueHead.left != null) {
                    toStack.push(queueHead.left);
                }
                if (queueHead.right != null) {
                    toStack.push(queueHead.right);
                }
            }
        }
        result.add(list);
        zigzagLevelHelper(toStack, ++orderLevel, result);
    }


    // 105 前序中序恢复二叉树
    // 选用左闭右开区间
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return recoveryTree(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    public TreeNode recoveryTree(int[] preorder, int preOrderLeft, int preOrderRight, int[] inorder, int inOrderLeft, int inOrderRight) {
        if (preOrderLeft == preOrderRight) {
            return null;
        }
        TreeNode treeRoot = new TreeNode(preorder[preOrderLeft]);

        int leftTreeLength = 0;
        int rootPositionInorder = -1;
        for (int i = 0; i < inorder.length; i++) {
            if (preorder[preOrderLeft] == inorder[i]) {
                leftTreeLength = i - inOrderLeft;
                rootPositionInorder = i;
                break;
            }
        }

        treeRoot.left = recoveryTree(preorder, preOrderLeft + 1, preOrderLeft + 1 + leftTreeLength, inorder, inOrderLeft, rootPositionInorder);
        treeRoot.right = recoveryTree(preorder, preOrderLeft + 1 + leftTreeLength, preOrderRight, inorder, rootPositionInorder + 1, inOrderRight);
        return treeRoot;
    }

    // 105 后序中序恢复二叉树
    // 选用左闭右开区间
    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        return recoveryTree1(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }

    public TreeNode recoveryTree1(int[] inorder, int inOrderLeft, int inOrderRight, int[] postorder, int postOrderLeft, int postOrderRight) {
        if (inOrderLeft == inOrderRight) {
            return null;
        }
        TreeNode treeRoot = new TreeNode(postorder[postOrderRight - 1]);

        int leftTreeLength = 0;
        int rootPositionInorder = -1;
        for (int i = 0; i < inorder.length; i++) {
            if (postorder[postOrderRight - 1] == inorder[i]) {
                leftTreeLength = i - inOrderLeft;
                rootPositionInorder = i;
                break;
            }
        }

        treeRoot.left = recoveryTree(inorder, inOrderLeft, rootPositionInorder, postorder, postOrderLeft, postOrderLeft + leftTreeLength);
        treeRoot.right = recoveryTree(inorder, rootPositionInorder + 1, inOrderRight, postorder, postOrderLeft + leftTreeLength, postOrderRight - 1);
        return treeRoot;
    }

    // 110 判断是否为平衡二叉树
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left)
                && isBalanced(root.right);
    }

    public int height(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        int leftHeight = height(treeNode.left);
        int rightHeight = height(treeNode.right);
        return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
    }

    // 112 二叉树路径和
    boolean isTrue;
    public boolean hasPathSum(TreeNode root, int sum) {
        pathSumHelper(root, 0, sum);
        return isTrue;
    }

    private void pathSumHelper(TreeNode root, int curSum, int sum) {
        if (root == null) {
            return;
        }
        curSum += root.val;
        if (root.left == null && root.right == null && curSum == sum) {
            isTrue = true;
            //return;
        }
        pathSumHelper(root.left, curSum, sum);
        pathSumHelper(root.right, curSum, sum);
        curSum -= root.val;
    }

    // 113
    List<List<Integer>> pathSumResult = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<Integer> result = new ArrayList<>();
        pathSumHelper1(root, 0, sum, result);
        return pathSumResult;
    }

    private void pathSumHelper1(TreeNode root, int curSum, int sum, List<Integer> result) {
        if (root == null) {
            return;
        }
        curSum += root.val;
        result.add(root.val);
        if (root.left == null && root.right == null && curSum == sum) {
            pathSumResult.add(new ArrayList<>(result));  // 注意要new一个新数组
        }

        pathSumHelper1(root.left, curSum, sum, result);
        pathSumHelper1(root.right, curSum, sum, result);
        curSum -= root.val;
        result.remove(result.size() - 1);
    }

    // 114
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        flatten(root.left);
        if (root.left != null) {
            TreeNode rightTree = root.right;
            TreeNode leftTree = root.left;
            root.left = null;
            root.right = leftTree;
            while (root.right != null) {
                root = root.right;
            }
            root.right = rightTree;
        }
        flatten(root.right);
    }

    // 115
    public TreeNode connect(TreeNode root) {
        connectHelper(root, null, null);
        return root;
    }

    public void connectHelper(TreeNode curNode) {
        if (curNode.left == null && curNode.right == null) {
            return;
        }
        curNode.left.next = curNode.right;
        if (curNode.next != null) {
            curNode.right.next = curNode.next.left;
        }
        connectHelper(curNode.left);
        connectHelper(curNode.right);
    }

    public void connectHelper(TreeNode curNode, TreeNode parent, TreeNode parentRightBrother) {
        if (curNode == null) {
            return;
        }

        if (parent == null) {
            curNode.next = null;
        } else if (parent.left == curNode) {
            curNode.next = parent.right;
        } else if (parentRightBrother != null) {
            curNode.next = parentRightBrother.left;
        } else {
            curNode.next = null;
        }

        TreeNode curNodeBrother = null;
        if (parent != null) {
            if (parent.right != curNode) {
                curNodeBrother = parent.right;
            } else if (parentRightBrother != null) {
                curNodeBrother = parentRightBrother.left;
            }
        }

        connectHelper(curNode.left, curNode, curNodeBrother);
        connectHelper(curNode.right, curNode, curNodeBrother);
    }

    // 117
    public void connectHelper1(TreeNode curNode) {
        if (curNode == null) {
            return;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(curNode);
        while (queue.size() > 0) {
            int size = queue.size();

            TreeNode preTreeNode = null;
            while (size-- > 0) {
                TreeNode treeNode = queue.poll();
                if (preTreeNode == null) {
                    preTreeNode = treeNode;
                } else {
                    preTreeNode.next = treeNode;
                    preTreeNode = treeNode;
                }
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
            }
        }
    }

    // 119
    private int resultSum = 0;
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        sumNumbersHelper(root, 0);
        return resultSum;
    }

    public void sumNumbersHelper(TreeNode root, int curValue) {
        if (root == null) {
            return;
        }
        curValue = curValue * 10 + root.val;
        if (root.left == null && root.right == null) {
            resultSum += curValue;
        }
        sumNumbersHelper(root.left, curValue);
        sumNumbersHelper(root.right, curValue);
    }

    // 124
    private int MAXSUM = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxGained(root);
        return MAXSUM;
    }

    public int maxGained(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftTreeMaxGained = Math.max(maxGained(root.left), 0);
        int rightTreeMaxGained = Math.max(maxGained(root.right), 0);

        int curMaxGained = root.val + leftTreeMaxGained + rightTreeMaxGained;
        if (MAXSUM < curMaxGained) {
            MAXSUM = curMaxGained;
        }
        return root.val + Math.max(leftTreeMaxGained, rightTreeMaxGained);
    }
}

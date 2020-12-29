package leetcode.tree;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class Num95 {

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<>();
        }
        return resultTrees(1, n);
    }

    private List<TreeNode> resultTrees(int start, int end) {
        List<TreeNode> allResult = new LinkedList<>();
        if (start > end) {
            allResult.add(null);
            return allResult;
        }

        for (int i = start; i <= end; i++) {

            List<TreeNode> leftResultTrees = resultTrees(start, i - 1);
            List<TreeNode> rightResultTrees = resultTrees(i + 1, end);

            for (TreeNode leftTree : leftResultTrees) {
                for (TreeNode rightTree : rightResultTrees) {
                    TreeNode newRoot = new TreeNode(i);
                    newRoot.left = leftTree;
                    newRoot.right = rightTree;
                    allResult.add(newRoot);
                }
            }
        }
        return allResult;
    }
}

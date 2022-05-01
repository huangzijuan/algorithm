package geek;

import tree.TreeNode;

import java.util.*;

public class week4 {

    /**
     * 102 层序遍历
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Deque<TreeNode> deque = new ArrayDeque();
        deque.offer(root);
        while (deque.size() > 0) {
            int size = deque.size();
            List<Integer> tempList = new ArrayList<>();
            while (size-- != 0) {
                TreeNode top = deque.poll();
                tempList.add(top.val);
                if (top.left != null) {
                    deque.offer(top.left);
                }

                if (top.right != null) {
                    deque.offer(top.right);
                }
            }
            result.add(tempList);
        }
        return result;
    }

    /**
     * 433 最小基因变化
     * 即寻找树的最短路径的问题
     * 单向BFS法
     */
    public int minMutation(String start, String end, String[] bank) {
        int count = 0;
        Character[] dnaSeq = {'A', 'C', 'G', 'T'};
        List<String> backList = new ArrayList<>(Arrays.asList(bank));
        Deque<String> queue = new ArrayDeque();
        queue.add(start);

        if (!backList.contains(end)) {
            return -1;
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String head = queue.poll();
                for (int i = 0; i < head.length(); i++) {
                    for (int j = 0; j < dnaSeq.length; j++) {
                        String newStr = head.substring(0, i) + dnaSeq[j] + head.substring(i+1);

                        if (backList.contains(newStr)) {
                            queue.offer(newStr);
                            backList.remove(newStr);   // remove的作用相当于标记visited的作用
                        }

                        if (newStr.equals(end)) {
                            return count;
                        }
                    }
                }
            }
            count++;
        }

        return count;
    }

    /**
     * 22 括号生成
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        parenthesisHelper(result, 0, 0, "", n);
        return result;
    }

    private void parenthesisHelper(List<String> result, int leftNum, int rightNum, String curStr, int n) {
        if (leftNum == n && rightNum == n) {
            result.add(curStr);
            return;
        }

        if (leftNum < n) {
            parenthesisHelper(result, leftNum + 1, rightNum, curStr + "(", n);
        }

        if (leftNum > rightNum) {
            parenthesisHelper(result, leftNum, rightNum + 1, curStr + ")", n);
        }
    }

    /**
     * 515 每个树行中找最大值
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Deque<TreeNode> deque = new ArrayDeque();
        deque.offer(root);
        while (deque.size() > 0) {
            int size = deque.size();
            int max = Integer.MIN_VALUE;
            while (size-- != 0) {
                TreeNode top = deque.poll();
                max = Math.max(max, top.val);
                if (top.left != null) {
                    deque.offer(top.left);
                }

                if (top.right != null) {
                    deque.offer(top.right);
                }
            }
            result.add(max);
        }
        return result;
    }
}

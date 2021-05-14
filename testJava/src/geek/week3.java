package geek;

import com.sun.xml.internal.ws.util.StringUtils;
import tree.TreeNode;

import java.util.*;

public class week3 {
    public static void main(String[] args) {
        week3 week3 = new week3();
        //System.out.println("hzjhzj " + week3.fastPow(2, 4));
        System.out.println("hzjhzj " + (2&7));
    }

    /**
     * 70 爬楼梯
     */
    public int climbStairs(int n) {
        if (n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 22 括号生成
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(0, 0, n, "", result);
        return result;
    }

    private void generate(int left, int right, int n, String curStr, List<String> result) {
        if (left == n && right == n) {
            result.add(curStr);
            return;
        }

        if (left < n) {
           generate(left + 1, right, n, curStr + "(", result);
        }
        if (left > right) {
            generate(left, right + 1, n, curStr + ")", result);
        }
    }

    /**
     * 226 翻转二叉树
     */
    public TreeNode invertTree(TreeNode root) {
        invert(root);
        return root;
    }

    private void invert(TreeNode node) {
        if (node == null) return;
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        invert(node.left);
        invert(node.right);
    }

    /**
     * 98 验证二叉搜索树
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBSTHelper(TreeNode node, long lower, long higher) {
        if (node == null) return true;
        if (node.val <= lower || node.val >= higher) return false;
        return isValidBSTHelper(node.left, lower, node.val) && isValidBSTHelper(node.right, node.val, higher);
    }

    /**
     * 104
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 111
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else if (root.left == null) {
            return minDepth(root.right) + 1;
        } else if (root.right == null) {
            return minDepth(root.left) + 1;
        } else {
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        }
    }

    /**
     * 297 树的序列化和反序列化
     * 方式一：DFS
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "X";
        }
        return root.val + "," + serialize(root.left) + "," + serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new ArrayDeque<>(Arrays.asList(data.split(",")));
        TreeNode treeNode = dfs(queue);
        return treeNode;
    }

    private TreeNode dfs(Queue<String> queue) {
        String top = queue.poll();
        if (top.equals("X")) {
            return null;
        }
        TreeNode treeNode = new TreeNode(Integer.valueOf(top));
        treeNode.left = dfs(queue);
        treeNode.right = dfs(queue);
        return treeNode;
    }

    /**
     * 方式二：BFS
     */
    // Encodes a tree to a single string.
    public String serialize1(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode top = queue.poll();
            if (top != null) {
                stringBuilder.append(top.val);
                queue.offer(top.left);
                queue.offer(top.right);
            } else {
                stringBuilder.append("X");
            }
            stringBuilder.append(",");
        }

        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize1(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] list = data.split(",");

        TreeNode root = new TreeNode(Integer.valueOf(list[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode top = queue.poll();
            if (!list[i].equals("X")) {
                top.left = new TreeNode(Integer.valueOf(list[i]));
                queue.offer(top.left);
            }
            i++;
            if (!list[i].equals("X")) {
                top.right = new TreeNode(Integer.valueOf(list[i]));
                queue.offer(top.right);
            }
            i++;
        }

        return root;
    }

    /**
     * 236 最近公共祖先
     * 采用后序遍历
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else {
            return right;
        }
    }

    /**
     * 105
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd) return null;
        int rootVal = preorder[preStart];
        int rootIndexInOrder = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (rootVal == inorder[i]) {
                rootIndexInOrder = i;
            }
        }
        int leftTreeLength = rootIndexInOrder - inStart;

        TreeNode root = new TreeNode(rootVal);
        root.left = buildTreeHelper(preorder, inorder, preStart + 1, preStart + leftTreeLength, inStart, inStart + leftTreeLength - 1);
        root.right = buildTreeHelper(preorder, inorder, preStart + leftTreeLength + 1, preEnd, rootIndexInOrder + 1, inEnd);

        return root;
    }

    /**
     * 77
     */
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        combineHelper(1, n, k, new ArrayList<>());
        return result;
    }

    private void combineHelper(int cur, int max, int k, List<Integer> curList) {
        if (curList.size() == k) {
            result.add(new ArrayList<>(curList));
            return;
        }

        for (int i = cur; i <= max; i++) {
            curList.add(i);
            //System.out.println("hzjhzj "+ i + " " + k);
            combineHelper(i + 1, max, k, curList);
            curList.remove(curList.size() - 1);
        }
    }

    /**
     * 46 全排列
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        permuteHelper(nums, 0, visited, new ArrayDeque<>(), result);
        return result;
    }

    private void permuteHelper(int[] nums, int depth, boolean[] visited, ArrayDeque<Integer> path, List<List<Integer>> result) {
        if (depth == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            path.addLast(nums[i]);
            permuteHelper(nums, depth + 1, visited, path, result);
            path.removeLast();
            visited[i] = false;
        }
    }

    /**
     * 47 全排列二
     * (考虑重复元素一定要优先排序，将重复的都放在一起，便于找到重复元素和剪枝！！！
     * 推广至 --> 如果涉及考虑重复元素，或者大小比较的情况，对列表排序是一个不错的选择)
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        permuteHelper1(nums, 0, visited, new ArrayDeque<>(), result);
        return result;
    }

    private void permuteHelper1(int[] nums, int depth, boolean[] visited, ArrayDeque<Integer> path, List<List<Integer>> result) {
        if (depth == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1]) && !visited[i - 1]) continue;
            visited[i] = true;
            path.addLast(nums[i]);
            permuteHelper1(nums, depth + 1, visited, path, result);
            path.removeLast();
            visited[i] = false;
        }
    }

    /**
     * 50 求x的n次方（利用分治思想）
     */
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        return fastPow(x, N);
    }

    // 分治递归
    private double fastPow(double x, long n) {
        double result = 1.0;
        if (n == 0) {
            return result;
        }
        double halfResult = fastPow(x, n/2);
        if (n % 2 == 1) {
            return halfResult * halfResult * x;
        } else {
            return halfResult * halfResult;
        }
    }

    /**
     * 78 子集
     * 方法一：递归方式
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        subsetsHelper(result, nums, new ArrayList<>(), 0);
        return result;
    }

    private void subsetsHelper(List<List<Integer>> result, int[] nums, List<Integer> list, int curIndex) {
        if (curIndex == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }

        // 注意：传参时curIndex不要自加，会改变原curIndex的值
        subsetsHelper(result, nums, list, curIndex+1);
        list.add(nums[curIndex]);
        subsetsHelper(result, nums, list, curIndex+1);

        list.remove(list.size() - 1);
    }

    /**
     * 78 子集
     * 方法二：迭代方式
     */
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            int length = result.size();
            for (int j = 0; j < length; j++) {
                List<Integer> resultItem = result.get(j);
                List<Integer> newItem = new ArrayList<>(resultItem);
                newItem.add(nums[i]);
                result.add(newItem);
            }
        }
        return result;
    }

    /**
     * 78 子集
     * 方法二：位运算
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        for (int i = 0; i < (1 << length); i++) {
            List<Integer> item = new ArrayList<>();
            for (int j = 0; j < length; j++) {
                if ((i & (1 << j)) != 0) {
                    item.add(nums[j]);
                }
            }
            result.add(item);
        }
        return result;
    }

    /**
     * 169 多数元素
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
                if (map.get(nums[i]) > length / 2) {
                    return nums[i];
                }
            } else {
                map.put(nums[i], 1);
            }
        }
        return Integer.MAX_VALUE;
    }

    /**
     * 169 多数元素（排序法）
     */
    public int majorityElement1(int[] nums) {
        Arrays.sort(nums);
        return nums[(nums.length >> 1)];
    }

    /**
     * 169 多数元素（摩尔投票法）
     */
    public int majorityElement2(int[] nums) {
        int candNum = nums[0], count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (candNum == nums[i]) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    candNum = nums[i];
                    count = 1;
                }
            }
        }
        return candNum;
    }

    /**
     * 17 电话号码的字母组合
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;
        Map<Character, String> digitMap = new HashMap<>();
        digitMap.put('2', "abc");
        digitMap.put('3', "def");
        digitMap.put('4', "ghi");
        digitMap.put('5', "jkl");
        digitMap.put('6', "mno");
        digitMap.put('7', "pqrs");
        digitMap.put('8', "tuv");
        digitMap.put('9', "wxyz");
        digitDFS("", result, digits, 0, digitMap);
        return result;
    }

    private void digitDFS(String str, List<String> result, String digits, int curIndex, Map<Character, String> digitMap) {
        if (curIndex == digits.length()) {
            result.add(str);
            return;
        }

        String curLetters = digitMap.get(digits.charAt(curIndex));
        for (int i = 0; i < curLetters.length(); i++) {
            digitDFS(str + curLetters.charAt(i), result, digits, curIndex + 1, digitMap);
        }
    }

    /**
     * 51 N皇后
     * （基于集合的回溯）
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        Set<Integer> columns = new HashSet<>();
        Set<Integer> pies = new HashSet<>();
        Set<Integer> nas = new HashSet<>();
        queensDFS(result, queens, n, 0, columns, pies, nas);
        return result;
    }

    private void queensDFS(List<List<String>> result, int[] queens, int n, int currentRow, Set<Integer> columns,
                           Set<Integer> pies, Set<Integer> nas) {
        if (currentRow == n) {
            List<String> bord = generateBord(queens, n);
            result.add(bord);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (columns.contains(i)) {
                continue;
            }
            int pie = currentRow - i;
            if (pies.contains(pie)) {
                continue;
            }
            int na = currentRow + i;
            if (nas.contains(na)) {
                continue;
            }

            columns.add(i);
            pies.add(pie);
            nas.add(na);
            queens[currentRow] = i;
            queensDFS(result, queens, n, currentRow + 1, columns, pies, nas);
            columns.remove(i);
            pies.remove(pie);
            nas.remove(na);
            queens[currentRow] = -1;
        }
    }

    private List<String> generateBord(int[] queens, int n) {
        List<String> bord = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            bord.add(new String(row));
        }
        return bord;
    }
}

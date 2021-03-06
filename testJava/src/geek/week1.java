package geek;

import linkedlist.ListNode;

import java.util.*;

public class week1 {

    /**
     * 盛最多水的容器
     * 方法一：暴力解法
     */
    public int maxArea1(int[] height) {
        int maxArea = 0;
        for(int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int area = (j - i) * Math.min(height[i], height[j]);
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    // 方法二：双指针
    public int maxArea2(int[] height) {
        int maxArea = 0;
        for (int i = 0, j = height.length - 1; i < j;) {
            int area = (j - i) * (height[i] > height[j] ? height[j--] : height[i++]);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    /**
     * 移动零
     * 方法一：双指针，用j记录第一个非0元素的位置
     */
    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != j) {
                    nums[j] = nums[i];
                    nums[i] = 0;
                }
                j++;
            }
        }
    }

    /**
     * 爬楼梯
     */
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 三数之和
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> resultSet = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int target = -nums[i];
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if (nums[j] + nums[k] < target) {
                    j++;
                } else if (nums[j] + nums[k] > target) {
                    k--;
                } else {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(nums[k]);
                    if (!resultSet.contains(temp)) {
                        resultSet.add(temp);
                        result.add(temp);
                    }
                    j++;
                    k--;
                }
            }
        }
        return result;
    }

    /**
     * 翻转链表
     */
    public ListNode reverseList1(ListNode head) {
        ListNode temp = null;
        while (head != null) {
            ListNode secondNode = head.next;
            head.next = temp;
            temp = head;
            head = secondNode;
        }
        return temp;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode temp = reverseList2(head.next);
        ListNode next = head.next;
        next.next = head;
        head.next = null;
        return temp;
    }

    /**
     * 两两交换链表中的节点
     */
    public ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode second = head.next;
        head.next = swapPairs1(second.next);
        second.next = head;
        return second;
    }

    public ListNode swapPairs2(ListNode head) {
        ListNode temp = new ListNode(0);
        temp.next = head;
        ListNode newHeadPre = temp;  // 用于指示新的返回头节点的前一个节点
        while (temp.next != null && temp.next.next != null) {
            ListNode first = temp.next;
            ListNode second = temp.next.next;
            temp.next = second;
            first.next = second.next;
            second.next = first;
            temp = first;
        }
        return newHeadPre.next;
    }

    /**
     * 判断链表中是否有环
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode quick = head;
        while (slow != null && slow.next != null
                && quick.next != null && quick.next.next != null) {
            slow = slow.next;
            quick = quick.next.next;

            if (slow == quick) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回链表开始入环的第一个节点
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, quick = head;
        while (true) {
            if (quick == null || quick.next == null) return null;
            slow = slow.next;
            quick = quick.next.next;
            // 第一次相遇时，slow走的距离是环的周长b，即slow = nb;
            if (slow == quick) {
                break;
            }
        }

        quick = head;
        while (slow != quick) {
            slow = slow.next;
            quick = quick.next;
        }
        return quick;
    }

    /**
     * k个一组翻转链表
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) return head;

        ListNode tail = head;
        for (int i = 0; i < k; i++) {
            if (tail == null) {
                return head;
            }
            tail = tail.next;
        }

        ListNode newHead = reverseKHelper(head, tail);
        head.next = reverseKGroup(tail, k);
        return newHead;
    }

    // 左闭右开区间
    private ListNode reverseKHelper(ListNode head, ListNode tail) {
        ListNode temp = null;
        while (head != tail) {
            ListNode second = head.next;
            head.next = temp;
            temp = head;
            head = second;
        }
        return temp;
    }

    /**
     * 删除排序数组中的重复项
     */
    public int removeDuplicates(int[] nums) {
        int j = 0;
        for(int i = 0; i < nums.length; i++) {
            if (nums[j] == nums[i]) {
                continue;
            } else {
                nums[++j] = nums[i];
            }
        }
        return j + 1;
    }

    /**
     * 旋转数组
     * 方法一：暴力
     */
    public void rotate1(int[] nums, int k) {
        int length = nums.length;
        for (int i = 0; i < k; i++) {
            int j = length - 1;
            int temp = nums[j];
            while (j > 0) {
                nums[j] = nums[j - 1];
                j--;
            }
            nums[0] = temp;
        }
    }

    // 方法二：翻转数组
    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - k - 1);
        reverse(nums, n - k, n - 1);
        reverse(nums, 0, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    // 方法三：环状替换
    public void rotate3(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = gcd(n, k);
        for (int i = 0; i < count; i++) {
            int current = i;
            int pre = nums[i];

            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = pre;
                pre = temp;
                current = next;
            } while (current != i);

        }
    }

    // 通过辗转相除求出最大公约数
    private int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }

    /**
     * 合并两个有序链表
     * 方法一：迭代
     */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode(0);
        ListNode cur = newHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        cur.next = l1 == null ? l2 : l1;
        return newHead.next;
    }

    // 方法二：递归
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }

    /**
     * 合并两个有序数组
     * 方法一：利用插入排序
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m; i < m + n; i++) {
            int insertPosition = i;
            int insertValue = nums2[i-m];
            while (insertPosition > 0 && insertValue < nums1[insertPosition - 1]) {
                nums1[insertPosition] = nums1[insertPosition - 1];
                insertPosition--;
            }
            nums1[insertPosition] = insertValue;
        }
    }

    /**
     * 加一
     */
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i]++;
                return digits;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    /**
     * 有效的括号
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            Character character = s.charAt(i);
            if (character.equals('(')) {
                stack.push(')');
            } else if (character.equals('[')) {
                stack.push(']');
            } else if (character.equals('{')) {
                stack.push('}');
            } else if (stack.isEmpty() || !stack.pop().equals(character)) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 最小栈
     * 使用辅助栈法
     */
    class MinStack {

        /** initialize your data structure here. */
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();
        public MinStack() {

        }

        public void push(int x) {
            stack.push(x);
            if (minStack.isEmpty() || minStack.peek() >= x) {
                minStack.push(x);
            }
        }

        public void pop() {
            int temp = stack.pop();
            if (!minStack.isEmpty() && minStack.peek() == temp) {
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    /**
     * 最大的矩形
     * 方法一：暴力1（超时）
     */
    public int largestRectangleArea1(int[] heights) {
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int min = heights[i];
            for (int j = i+1; j < heights.length + 1; j++) {
                if (min > heights[j-1]) {
                    min = heights[j-1];
                }
                int area = min * (j - i);
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    //方法二：暴力2（超时）
    public int largestRectangleArea2(int[] heights) {
        if (heights.length <= 0) {
            return 0;
        }
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            // 找出比当前高度小的左右边界
            int leftBound = i;
            while (leftBound >= 0 && heights[i] <= heights[leftBound]) {
                leftBound--;
            }

            int rightBound = i;
            while (rightBound < heights.length && heights[i] <= heights[rightBound]) {
                rightBound++;
            }

            int area = heights[i] * (rightBound - leftBound - 1);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    //方法三：单调栈
    public int largestRectangleArea3(int[] heights) {
        int[] tmp = new int[heights.length + 2];
        System.arraycopy(heights, 0, tmp, 1, heights.length);

        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < tmp.length; i++) {
            while (!stack.isEmpty() && tmp[stack.peek()] > tmp[i]) {
                int top = stack.pop();
                maxArea = Math.max(maxArea, tmp[top] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        return maxArea;
    }

    /**
     *
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

    }
}

package leetcode;

import leetcode.tree.Num173;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Base1 {
    public static void main(String[] args) {
        Base1 base1 = new Base1();
    }

    // 1
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int j = nums.length - 1;
            while (i < j) {
                //System.out.println("hzjhzj:" + nums[i] + " " + nums[j]);
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    break;
                } else {
                    j--;
                }
            }
        }
        return result;
    }

    // 20
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character character = s.charAt(i);
            if (!stack.isEmpty()) {
                Character top = stack.pop();
                if (!isTwoCharMatch(top, character)) {
                    stack.push(top);
                    stack.push(character);
                }
            } else {
                stack.push(character);
            }
        }
        return stack.isEmpty();
    }

    public boolean isTwoCharMatch(Character character1, Character character2) {
        return character1.equals("(") && character2.equals(")") ||
                character1.equals("{") && character2.equals("}") ||
                character1.equals("[") && character2.equals("]");
    }

    // 394
//    public String decodeString(String s) {
//
//    }
}

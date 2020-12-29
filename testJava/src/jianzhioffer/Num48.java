package jianzhioffer;

import java.util.HashMap;

public class Num48 {

    public static void main(String[] args) {
        Num48 num48 = new Num48();
        int temp = num48.lengthOfLongestSubstring("hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
        System.out.println("输出结果是：" + temp);
    }

    public int lengthOfLongestSubstring(String str) {
        if (str.length() <= 0) {
            return 0;
        }
        int maxLength = 0;
        int curLength = 0;
        HashMap<Character, Integer> strIndexMap = new HashMap();

        for (int i = 0; i < str.length(); i++) {
            if (strIndexMap.containsKey(str.charAt(i))) {
                int distance = i - strIndexMap.get(str.charAt(i));
                if (curLength < distance) {
                    curLength++;
                } else {
                    curLength = distance;
                }
            } else {
                curLength++;
            }
            System.out.println(i + " " + str.charAt(i) + " curLen：" + curLength + " maxLen：" + maxLength);
            strIndexMap.put(str.charAt(i), i);
            if (maxLength < curLength) {
                maxLength = curLength;
            }
        }
        return maxLength;
    }
}

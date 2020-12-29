package leetcode;

import java.util.*;

public class Num567 {

    public static void main(String[] args) {
        Num567 num567 = new Num567();
//        System.out.println("the result is :" + num841.canVisitAllRooms3(lists));
    }

    public boolean checkInclusion(String s1, String s2) {
        int s1length = s1.length();
        HashMap<Character, Integer> s1Map = new HashMap<>();
        HashMap<Character, Integer> s1Map2 = new HashMap<>();
        for (int i = 0; i < s1length; i++) {
            if (s1Map.containsKey(s1.charAt(i))) {
                s1Map.put(s1.charAt(i), s1Map.get(s1.charAt(i)) + 1);
                s1Map2.put(s1.charAt(i), s1Map.get(s1.charAt(i)) + 1);
            } else {
                s1Map.put(s1.charAt(i), i);
                s1Map2.put(s1.charAt(i), i);
            }
        }

        int index = s2.indexOf(s1.charAt(0));
        boolean isIncrementInclude = true;
        for (int i = index; i < index + s1length && i < s2.length(); i++) {
            if (!s1Map.containsKey(s2.charAt(i))) {
                isIncrementInclude = false;
            } else {
                int num = s1Map.get(s2.charAt(i));
                if (num > 0) {
                    s1Map.put(s2.charAt(i), num - 1);
                } else {
                    s1Map.remove(s2.charAt(i));
                }
            }
        }

        boolean isDecrementInclude = true;
        for (int i = index; i > index - s1length && i > 0; i--) {
            if (!s1Map2.containsKey(s2.charAt(i))) {
                isDecrementInclude = false;
            } else {
                int num = s1Map2.get(s2.charAt(i));
                if (num > 0) {
                    s1Map2.put(s2.charAt(i), num - 1);
                } else {
                    s1Map2.remove(s2.charAt(i));
                }
            }
        }
        return isIncrementInclude || isDecrementInclude;
    }
}

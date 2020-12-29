package util;

import linkedlist.BaseLinkedList;
import linkedlist.ListNode;

import java.util.List;

public class CommonUtils {
    public static void printArray(int[] array) {
        for (int arr : array) {
            System.out.print(arr + " ");
        }
        System.out.println(' ');
    }

    public static void printArray2(int[][] array) {
        for (int i = 0; i < array.length; i ++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(' ');
    }

    public static void printArray3(List<Integer> array) {
        for (Integer element : array) {
            System.out.print(element + " ");
        }
        System.out.print("数组长度为：");
        System.out.print(array.size());
        System.out.println();
    }

    public static void printLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println(' ');
    }

    public static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        } else {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}

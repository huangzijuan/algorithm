package sort;

import util.CommonUtils;

public class KthSmallest {
    public static void main(String[] args) {
        KthSmallest kthSmallest = new KthSmallest();
        int[] array = {7, 1, 5, 3, 6, 4};
        //int temp = kthSmallest.kthSmallestByQuick(array, 1);
        int temp = kthSmallest.kthLargestByHeap(array, 1);
        System.out.println(temp);
    }


    /**
     * 快排方式找第K小元素（下标从0开始）
     */
    public static int kthSmallestByQuick(int[] arr, int k) {
        if (arr.length == 0) {
            return Integer.MAX_VALUE;
        }
        int left = 0;
        int right = arr.length - 1;
        int pivot = partition(arr, left, right);
        while (pivot != k) {
            if (pivot < k) {
                left = pivot + 1;
                pivot = partition(arr, left, right);
            } else if (pivot > k) {
                right = pivot - 1;
                pivot = partition(arr, left, right);
            }
        }

        System.out.println("找到的值" + arr[pivot]);
        return arr[pivot];
    }

    public static int partition(int[] arr, int left, int right) {
        int baseValue = arr[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] < baseValue) {
                swap(arr, i, j);
                i = i + 1;
            }
        }
        swap(arr, i, right);
        System.out.println("分区点" + i);
        return i;
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

    /**
     * 堆排序方式找第K大元素（下标从0开始）
     */
    public static int kthLargestByHeap(int[] arr, int k) {
        int[] newArray = new int[arr.length + 1];
        newArray[0] = -1000;
        for (int i = 0; i < arr.length; i++) {
            newArray[i+1] = arr[i];
        }

        HeapSort.buildHeap(newArray, newArray.length - 1);
        for (int i = newArray.length - 1; i >= newArray.length - k; i--) {
            CommonUtils.swap(newArray, 1, i);
            HeapSort.heapAdjust(newArray, i - 1, 1);
        }
        return newArray[1];
    }
}

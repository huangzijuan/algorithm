package sort;

import util.CommonUtils;

/**
 * 堆的特性：
 * 1. 是完全二叉树，适合用数组存储
 * 2. 假设i是当前节点，则其左子节点为2*i，右子节点为2*i+1，父节点为i/2（根结点放在数组为1的位置）
 * 3. 对完全二叉树来说，从n/2+1到n的位置都是叶子节点
 */
public class HeapSort {
    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        int[] array = {-1000, 7, 1, 5, 3, 6, 4};
        //heapSort.buildHeap(array, 6);
        heapSort.HeapSort(array);
        CommonUtils.printArray(array);
    }

    public static void HeapSort(int[] array) {
        buildHeap(array, array.length - 1);
        for (int i = array.length - 1; i > 0; i--) {
            CommonUtils.swap(array, 1, i);
            heapAdjust(array, i - 1, 1);
        }
    }

    // n为最后一个节点的index
    public static void buildHeap(int[] array, int n) {
        for (int i = n/2; i > 0; i--) {
            heapAdjust(array, n, i);
        }
    }

    // 从下向上调整，大顶堆
    public static void heapAdjust(int[] array, int n, int adjustIndex) {
        while (true) {
            int maxValueIndex = adjustIndex;
            if (2 * adjustIndex <= n && array[maxValueIndex] < array[2 * adjustIndex]) {
                maxValueIndex = 2 * adjustIndex;
            }
            if (2 * adjustIndex + 1 <= n && array[maxValueIndex] < array[2 * adjustIndex + 1]) {
                maxValueIndex = 2 * adjustIndex + 1;
            }
            if (maxValueIndex == adjustIndex) break;
            CommonUtils.swap(array, adjustIndex, maxValueIndex);
            adjustIndex = maxValueIndex;
        }
    }


}

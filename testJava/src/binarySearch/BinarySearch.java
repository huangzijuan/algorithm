package binarySearch;

/**
 * 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        BinarySearch dfsAlgo = new BinarySearch();
        int[][] matrix = {{1,  5,  9}, {10, 11, 13}, {12, 13, 15}};
        int[] array = {1,  5,  8, 11, 12, 13};

        int result = dfsAlgo.kSmallest1(array, 7);
        System.out.println(result);
    }

    /**
     * 有序一维数组中寻找元素k
     */
    public static int kSmallest1(int[] array, int k) {
        int left = array[0];
        int right = array[array.length - 1];
        while (left < right) {
            int mid = (left + right) / 2;
            if (mid > k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return  right;
    }

    /**
     * 有序矩阵中第K小的元素
     */
    public static int kSmallest(int[][] matrix, int k) {
        int left = matrix[0][0];
        int right = matrix[matrix.length - 1][matrix[0].length - 1];

        while (left < right) {
            int mid = (left + right) / 2;
            if (noMoreThanBaseValue(matrix, mid) < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    /**
     * 有序矩阵中比基准值小的元素个数
     * 行最大值matrix.length - 1，列最大值matrix[0].length - 1
     */
    public static int noMoreThanBaseValue(int[][] matrix, int baseValue) {
        int nums = 0;
        int x = matrix.length - 1;
        int y = 0;
        while (x >= 0 && y < matrix[0].length) {
            if (matrix[x][y] <= baseValue) {
                nums = nums + x + 1;
                y++;
            } else {
                x--;
            }
        }
        return nums;
    }
}

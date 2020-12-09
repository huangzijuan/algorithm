package algo;

import util.CommonUtils;

public class DynamicFindLengthAlgo {
    public static void main(String[] args) {
        DynamicFindLengthAlgo dynamicFindLengthAlgo = new DynamicFindLengthAlgo();
        int[] array1 = {1, 2, 3, 2, 1, 0};
        int[] array2 = {3, 2, 4, 1, 7, 0};
        int result = dynamicFindLengthAlgo.findLengthStr1(array1, array2);
        //int result = dynamicFindLengthAlgo.findLengthSequence1(array1, array2);
        System.out.println(result);
    }

    /**
     * 找出最长公共子串（使用二维数组）
     */
    public static int findLengthStr1(int[] array1, int[] array2) {
        int maxLength = 0;
        int[][] dp = new int[array1.length + 1][array2.length + 1];
        for (int i = 1; i <= array1.length; i++) {
            for (int j = 1; j <= array2.length; j++) {
                if (array1[i - 1] == array2[j - 1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }
        CommonUtils.printArray2(dp);
        return maxLength;
    }

    /**
     * 找出最长公共子串（使用一维数组）
     */
    public static int findLengthStr2(int[] array1, int[] array2) {
        int maxLength = 0;
        int[] dp = new int[array2.length + 1];
        for (int i = 1; i <= array1.length; i++) {
            for (int j = array2.length; j >= 1; j--) {
                if (array1[i - 1] == array2[j - 1]) {
                    dp[j] = dp[j-1] + 1;
                } else {
                    dp[j] = 0;
                }
                maxLength = Math.max(maxLength, dp[j]);
            }
            CommonUtils.printArray(dp);
        }
        return maxLength;
    }

    /**
     * 找出最长公共子序列（使用二维数组）
     */
    public static int findLengthSequence1(int[] array1, int[] array2) {
        int maxLength = 0;
        int[][] dp = new int[array1.length + 1][array2.length + 1];
        for (int i = 1; i <= array1.length; i++) {
            for (int j = 1; j <= array2.length; j++) {
                if (array1[i - 1] == array2[j - 1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] =  Math.max(dp[i-1][j], dp[i][j-1]);
                }
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }
        CommonUtils.printArray2(dp);
        return maxLength;
    }

    /**
     * 找出最长公共子序列（使用一维数组）
     */
    public static int findLengthSequence2(int[] array1, int[] array2) {
        int maxLength = 0;
        int[] dp = new int[array2.length + 1];
        for (int i = 1; i <= array1.length; i++) {
            for (int j = 1; j <= array2.length; j++) {
                if (array1[i - 1] == array2[j - 1]) {
                    dp[j] = dp[j-1] + 1;
                } else {
                    dp[j] =  Math.max(dp[j], dp[j-1]);
                }
                maxLength = Math.max(maxLength, dp[j]);
            }
            CommonUtils.printArray(dp);
        }
        return maxLength;
    }
}

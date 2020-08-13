package algo;

/**
 * 动态规划解题思路：
 * 1. 定义数组元素含义
 * 2. 找出数组元素之间的关系
 * 3. 找出初始值
 */
public class DynamicAlgo {
    public static void main(String[] args) {
        DynamicAlgo dynamicAlgo = new DynamicAlgo();
        int[] array = {7, 1, 5, 3, 6, 4};
        int maxProfit = dynamicAlgo.maxProfit(array);
        System.out.println(maxProfit);

    }


    /**
     * 买卖股票的最佳时机
     */
    public static int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int bestBuyDay = 0;
        int bestSellDay = 0;
        int minPrice = prices[bestBuyDay];
        int maxProfit = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] < minPrice) {
                bestBuyDay = i;
                minPrice = prices[i];
            } else {
                if (prices[i] - minPrice > maxProfit) {
                    maxProfit = prices[i] - minPrice;
                    bestSellDay = i;
                }
            }
        }
        System.out.println("bestBuyDay 为：" + (bestBuyDay + 1));
        System.out.println("bestSellDay 为：" + (bestSellDay + 1));
        return maxProfit;
    }
}

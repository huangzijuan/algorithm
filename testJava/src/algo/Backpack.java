package algo;

/**
 * 假设山洞里共有a,b,c,d ,e这5件宝物（不是5种宝物），它们的重量分别是2,2,6,5,4，它们的价值分别是6,3,5,4,6，
 * 现在给你个承重为10的背包, 怎么装背包，可以才能带走最多的财富。
 */
public class Backpack {

    public static void main(String[] args) {
        Backpack backpack = new Backpack();
        int n = 5, w = 10;                   //物品个数、背包最大容量
        int[] value = {0,6, 3, 5, 4, 6};     //各个物品的价值
        int[] weight = {0,2, 2, 6, 5, 4};    //各个物品的重量
        System.out.println(backpack.getMaxValue(weight, value, w, n));
    }

    //0-1背包问题，动态规划解法
    public int getMaxValue(int[] weight, int[] value, int w, int n) {
        int[][] table = new int[n + 1][w + 1];// 创建一个二维数组，横列是物品的价值，竖列是物品的重量
        // 蓝色代码注释开始
        for (int i = 1; i <= n; i++) { //物品
            for (int j = 1; j <= w; j++) {  //背包大小
                if (weight[i] > j) {
                    //当前物品i的重量比背包容量j大，装不下，肯定就是不装
                    table[i][j] = table[i - 1][j];
                } else { //装得下，Max{装物品i， 不装物品i}
                    table[i][j] = Math.max(table[i - 1][j], table[i - 1][j - weight[i]] + value[i]);
                }
                System.out.print("[" + i + j+ "]:="+ table[i][j] + "  ");
            }
            System.out.println();
        }
        // 蓝色代码注释结束
        return table[n][w];
    }

}

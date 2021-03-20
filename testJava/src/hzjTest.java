import tree.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

public class hzjTest {

//    public static void main(String[] args) {
//        System.out.println("hzj");
//        int a[]={1,2,-1,-5,-6,7,-7,-10};
//        new hzjTest().setParted1(a,0,a.length-1);
//
//        // 测试笛卡尔积
//        for (int i = 1; i < 10; i++) {
//            System.out.println("hzjhzj:" + cartesianProduct(i));
//        }
//
//        System.out.println("最大公约数： " + gcd(4, 8) + " 最小公倍数： " + getMinMultiple(3, 4));
//    }

    public static void main(String[] args) {
        //对数据做分组（分组条件：组织+对象）
        List<Map<String,Object>> list2 = new ArrayList<>();
        Map<String,Object> map1 = new HashMap<>();
        map1.put("organization", "北京");
        map1.put("object", "故宫");
        map1.put("score", "90");
        list2.add(map1);
        Map<String,Object> map2 = new HashMap<>();
        map2.put("organization", "北京");
        map2.put("object", "故宫");
        map2.put("personQty", "8万");
        list2.add(map2);
        Map<String,Object> map3 = new HashMap<>();
        map3.put("organization", "南京");
        map3.put("object", "博物院");
        map3.put("score", "80");
        list2.add(map3);
        Map<String,Object> map4 = new HashMap<>();
        map4.put("organization", "南京");
        map4.put("object", "博物院");
        map4.put("personQty", "3万");
        list2.add(map4);
        List<Map<String, Object>> groupList = list2.stream().collect(Collectors.groupingBy(d -> d.get("organization")
                + "||" + d.get("object"))).entrySet()
                .stream().map(d -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("entrustList", d.getValue());
                    map.put("groupKey", d.getKey());
                    return map;
                }).collect(Collectors.toList());

        Set<Map.Entry<String, List<Map<String, Object>>>> groupList1 = list2.stream().collect(Collectors.groupingBy(d -> d.get("organization")
                + "||" + d.get("object"))).entrySet();

        System.out.println("hzj" + groupList1.toString());
    }

    public void setParted1(int[] a,int left,int right){
        if(left>=right||left==a.length||right==0){
            for(int i=0;i<a.length;i++){
                System.out.println(a[i]);
            }
            return ;
        }
        while(a[left]<0){
            left++;
        }
        while(a[right]>=0){
            right--;
        }
        if(left>=right||left==a.length||right==0){
            for(int i=0;i<a.length;i++){
                System.out.println(a[i]);
            }
            return;
        }
        swap(a,left,right);
        left++;
        right--;
        setParted1(a,left,right);
    }
    private void swap(int a[],int left,int right){
        int temp=0;
        temp=a[left];
        a[left]=a[right];
        a[right]=temp;
    }


    // 笛卡尔积算法
    public static int cartesianProduct(int n) {
        int[] G = new int[n+1];
        G[0] = 1;
        G[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                G[i] += G[j-1] * G[i-j];
            }
        }
        return G[n];
    }

    public static int height(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        int leftHeight = height(treeNode.left);
        int rightHeight = height(treeNode.right);
        return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
    }

    private static int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }

    // 获取两个数的最大公约数（辗转相除法）
    public static int getMaxDivisor(int first, int second) {
        int max = Math.max(first, second);
        int min = Math.min(first, second);
        if (max % min == 0) {
            return min;
        }
        return getMaxDivisor(min, max % min);
    }

    // 获取两个数的最小公倍数
    public static int getMinMultiple(int first, int second) {
        return first * second / getMaxDivisor(first, second);
    }
}

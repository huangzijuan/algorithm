import tree.TreeNode;

import java.util.ArrayList;

public class hzjTest {

    public static void main(String[] args) {
        System.out.println("hzj");
//        int a[]={1,2,-1,-5,-6,7,-7,-10};
//        new hzjTest().setParted1(a,0,a.length-1);

        for (int i = 1; i < 10; i++) {
            System.out.println("hzjhzj:" + cartesianProduct(i));
        }
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
}

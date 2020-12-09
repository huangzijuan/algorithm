import java.util.ArrayList;

public class hzjTest {

    public static void main(String[] args) {
        System.out.println("hzj");
        int a[]={1,2,-1,-5,-6,7,-7,-10};
        new hzjTest().setParted1(a,0,a.length-1);
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

}

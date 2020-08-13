package sort;

public class BaseSort {
    public static void main(String[] args) {
        BaseSort baseSort = new BaseSort();
        int[] array = {9, 6, 4, 8, 5, 7, 3};
        printArray(array);
        //baseSort.bubbleSort(array);
        //baseSort.selectSort(array);
        //baseSort.insertSort(array);
        baseSort.quickSort1(array, 0, array.length - 1);
        printArray(array);

    }

    // 选择排序
    public static void selectSort(int[] array) {
        int n = array.length;
        int minIndex;
        for (int i = 0; i < n - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
            }
        }
    }

    // 冒泡排序
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = n - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (array[i] < array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    // 插入排序
    public static void insertSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int insertIndex = i;
            int temp = array[i];
            while (insertIndex > 0 && temp < array[insertIndex - 1]) {
                array[insertIndex] = array[insertIndex - 1];
                insertIndex = insertIndex - 1;
            }
            array[insertIndex] = temp;
        }
    }

    /*
     * 快速排序——双边扫描方式
     * 选择左边第一个元素为基准元素，先从右边开始扫描第一个小于基准值的元素，再从左边扫描第一个大于基准值的元素，如果i < j，交换a[i]与a[j]
     * 循环结束后，i指向的元素始终是小于基准值的元素，交换a[i]与基准值
     * */
    public static void quickSort1(int[] array, int left, int right) {
        if (left >= right || left < 0 || right < 0) {
            return;
        }
        int baseValue = array[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (array[j] >= baseValue && i < j) j--;
            while (array[i] <= baseValue && i < j) i++;

            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        array[left] = array[i];
        array[i] = baseValue;
        quickSort1(array, left, i - 1);
        quickSort1(array, i + 1, right);

    }



    /*
     * 快速排序——单边扫描方式
     * 选最右边的元素为基准值
     * i指针指向的始终为从左往右大于基准值的第一个元素
     * (我们通过游标 i 把 A[p…r-1]分成两部分。A[p…i-1]的元素都是小于 pivot 的，我们暂且叫它“已处理区间”，A[i…r-1]是“未处理区间”。
     * 我们每次都从未处理的区间 A[i…r-1]中取一个元素 A[j]，与 pivot 对比，如果小于 pivot，则将其加入到已处理区间的尾部，也就是 A[i]的位置)
     * */
    public static void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = partition(array, left, right);
        quickSort(array, left, pivot - 1);
        quickSort(array, pivot + 1, right);

    }

    public static int partition(int[] array, int left, int right) {
        int baseValue = array[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (array[j] < baseValue) {
                if (i == j) {
                    i = i + 1;
                } else {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    i = i + 1;
                }
            }
        }

        array[right] = array[i];
        array[i] = baseValue;

        return i;
    }


    public static void printArray(int[] array) {
        for (int arr : array) {
            System.out.print(arr + " ");
        }
        System.out.println(' ');
    }
}

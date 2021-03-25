package sort;

import java.util.Arrays;

/**
 *
 */
public class BinaryHeap {
    private static final int d = 2; //节点的度，2表示为二叉树
    private int[] heap;
    private int heapSize;

    public BinaryHeap(int capacity) {
        heapSize = 0;
        heap = new int[capacity + 1];
        Arrays.fill(heap, -1);
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public boolean isFull() {
        return heapSize == heap.length;
    }

    private int parent(int i) {
        return (i - 1) / d;
    }

    private int kthChild(int i, int k) {
        return d * i + k;
    }

    public void insert(int x) {
        if (isFull()) {
            throw new RuntimeException("heap is full");
        }
        heap[heapSize++] = x;
        heapifyUp(heapSize - 1);
    }

    public int delete(int x) {
        if (isEmpty()) {
            throw new RuntimeException("heap is empty");
        }
        int key = heap[x];
        heap[x] = heap[heapSize - 1];
        heapSize--;
        heapifyDown(x);
        return key;
    }

    private void heapifyUp(int i) {
        int temp = heap[i];
        int insertIndex = i;
        while (insertIndex > 0 && temp > heap[parent(insertIndex)]) {
            heap[insertIndex] = heap[parent(insertIndex)];
            insertIndex = parent(insertIndex);
        }
        heap[insertIndex] = temp;
    }

    private void heapifyDown(int i) {
        int temp = heap[i];
        int insertIndex = i;
        while (kthChild(insertIndex, 1) < heapSize) {
            int maxChildIndex = maxChildIndex(insertIndex);
            if (temp >= heap[maxChildIndex]) {
                break;
            }
            heap[insertIndex] = heap[maxChildIndex];
            insertIndex = maxChildIndex;
        }
        heap[insertIndex] = temp;
    }

    private int maxChildIndex(int i) {
        int leftChildIndex = kthChild(i, 1);
        int rightChildIndex = kthChild(i, 2);
        return heap[leftChildIndex] > heap[rightChildIndex] ? leftChildIndex : rightChildIndex;
    }
}

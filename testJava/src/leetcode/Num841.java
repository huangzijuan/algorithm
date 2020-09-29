package leetcode;

import array.BaseArray;

import java.util.*;

public class Num841 {

    public static void main(String[] args) {
        Num841 num841 = new Num841();
        //int[][] array = {{1,3},{3,0,1},{2},{0}};
        List<List<Integer>> lists = new ArrayList<List<Integer>>(){
            {
                add(Arrays.asList(1, 3));
                add(Arrays.asList(3,0,1));
                add(Arrays.asList(2));
                add(Arrays.asList(0));
            }
        };
        System.out.println("the result is :" + num841.canVisitAllRooms3(lists));
    }

    // 利用dfs深度优先搜索算法
    public boolean canVisitAllRooms1(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visitArray = new boolean[n];

        dfsRoom(rooms, 0, visitArray);

        for (boolean visitItem : visitArray) {
            if (!visitItem) {
                return false;
            }
        }
        return true;
    }

    public void dfsRoom(List<List<Integer>> rooms, int roomIndex, boolean[] visitArray) {
        if (visitArray[roomIndex]) return;
        visitArray[roomIndex] = true;
        List<Integer> keys = rooms.get(roomIndex);
        for (int key : keys) {
            dfsRoom(rooms, key, visitArray);
        }
    }


    // 利用bfs广度优先搜索算法
    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visitArray = new boolean[n];
        Queue<Integer> integerQueue = new PriorityQueue<>();
        Set<Integer> integerSet = new HashSet<>();
        for (Integer integer : rooms.get(0)) {
            integerQueue.offer(integer);
            integerSet.add(integer);
        }
        System.out.println("queue is：" + integerQueue.toString());
        visitArray[0] = true;

        while (!integerQueue.isEmpty()) {
            Integer queueHead = integerQueue.poll();
            System.out.println("queue is：" + integerQueue.toString());
            if (!visitArray[queueHead]) {
                visitArray[queueHead] = true;
            }
            if (!rooms.get(queueHead).isEmpty()) {
                for (Integer integer : rooms.get(queueHead)) {
                    if (!integerSet.contains(integer)) {
                        integerQueue.offer(integer);
                        integerSet.add(integer);
                    }
                }
            }
        }

        for (boolean visitItem : visitArray) {
            if (!visitItem) {
                return false;
            }
        }
        return true;
    }

    // 利用bfs广度优先搜索算法
    public boolean canVisitAllRooms3(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visitArray = new boolean[n];
        Queue<Integer> integerQueue = new PriorityQueue<>();
        for (Integer integer : rooms.get(0)) {
            integerQueue.offer(integer);
        }
        System.out.println("queue is：" + integerQueue.toString());
        visitArray[0] = true;

        while (!integerQueue.isEmpty()) {
            Integer queueHead = integerQueue.poll();
            visitArray[queueHead] = true;

            if (!rooms.get(queueHead).isEmpty()) {
                for (Integer integer : rooms.get(queueHead)) {
                    if (!visitArray[integer]) {
                        integerQueue.offer(integer);
                        System.out.println("queue is：" + integerQueue.toString());
                    }
                }
            }
        }

        for (boolean visitItem : visitArray) {
            if (!visitItem) {
                return false;
            }
        }
        return true;
    }
}

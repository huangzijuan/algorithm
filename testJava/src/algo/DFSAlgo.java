package algo;

import util.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯算法
 */
public class DFSAlgo {
    public static void main(String[] args) {
        DFSAlgo dfsAlgo = new DFSAlgo();
        int[] nums = {4, 6, 7, 7};
        List<List<Integer>> result = dfsAlgo.findSubsequences(nums);

        System.out.println(result.size());
        for (List<Integer> integerList: result) {
            CommonUtils.printArray3(integerList);
        }

    }


    /**
     * 找出数组中的所有递增子序列
     * 输入: [4, 6, 7, 7]
     * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
     */
    private List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
        dFSArray(nums, 0, new ArrayList<>());
        return result;
    }

    public void dFSArray(int[] nums, int index, List<Integer> elementList) {
        if (index >= nums.length) {
            if (elementList.size() >= 2) {
                result.add(new ArrayList<>(elementList));
            }
            return;
        }

        // index位置的元素加入数组中
        if (elementList.size() == 0 || nums[index] >= elementList.get(elementList.size() - 1)) {
            elementList.add(nums[index]);
            dFSArray(nums, index + 1, elementList);
            elementList.remove(elementList.size() - 1);
        }

        // 剪枝，去掉重复的子数组
        if (elementList.size() > 0 && nums[index] == elementList.get(elementList.size() - 1)) {
            return;
        }

        // index位置的元素不加入数组中
        dFSArray(nums, index + 1, elementList);
    }
}

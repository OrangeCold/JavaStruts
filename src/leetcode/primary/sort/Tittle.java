package leetcode.primary.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tittle {


    /**
     * 合并两个有序数组
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int j = 0;
        for (int i = 0; i < n; i++) {
            nums1[m+j] = nums2[i];
            j++;
        }
        Arrays.sort(nums1);
    }

    /**
     * 三数之和
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        int length = nums.length;

        if (length < 3){
            return null;
        }

        int l = 0;
        int r = length-1;

        boolean addL = true;

        while ((r-l)>1){
            for (int i = l+1; i < r; i++) {
                if ((nums[l] + nums[i] + nums[r]) ==0){
                    List<Integer> row = new ArrayList<>();
                    row.add(nums[l]);
                    row.add(nums[i]);
                    row.add(nums[r]);
                    result.add(row);
                }
            }
            if (addL){
                l++;
                addL = false;
            }else {
                r--;
                addL = true;
            }
        }

        return result;
    }

}

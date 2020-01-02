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
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        int length = nums.length;

        if (length < 3){
            return result;
        }

        int l = 0;
        int r = length-1;

        while ((r-l)>1){
            for (int i = r; i < 0; i--) {
                for (int j = l+1; j < i; j++) {
                    if ((nums[l]+nums[j]+nums[i]) == 0){
                        List<Integer> row = new ArrayList<>();
                        row.add(nums[l]);
                        row.add(nums[j]);
                        row.add(nums[i]);
                        if (!result.contains(row)){
                            result.add(row);
                        }
                    }
                    
                }
            }

            for (int i = 0; i < r; i++) {
                for (int j = i+1; j < r; j++) {
                    if ((nums[i]+nums[j]+nums[r]) == 0){
                        List<Integer> row = new ArrayList<>();
                        row.add(nums[i]);
                        row.add(nums[j]);
                        row.add(nums[r]);
                        if (!result.contains(row)){
                            result.add(row);
                        }
                    }
                }
            }
            l++;
            r--;
        }

        return result;
    }

}

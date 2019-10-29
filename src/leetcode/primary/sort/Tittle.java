package leetcode.primary.sort;

import org.junit.internal.runners.statements.FailOnTimeout;

import java.util.Arrays;

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

}

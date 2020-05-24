package leetcode.Recursion;

import leetcode.Recursion.entity.ListNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Title {

    public static void main(String[] args) {
        char[] s = new char[]{'h','e','l','l','o'};
        reverseString(s);
        for (char c : s) {
            System.out.print(c+" ");
        }
    }

    /**
     * 杨辉三角
     */
    public List<List<Integer>> generate(int numRows){
        List<List<Integer>> result = new ArrayList<>();
        if (numRows == 0){
            return result;
        }
        if (numRows < 2) {
            List<Integer> firstRow = new ArrayList<>();
            firstRow.add(1);
            result.add(firstRow);
            return result;
        }
        for (int i = 0; i < numRows; i++) {

        }
        return result;
    }

    /**
     * 两两交换链表中的节点
     */
    public static ListNode swapPairs(ListNode head) {

        if (head == null || head.next == null){
            return head;
        }
        ListNode firstNode = head;
        ListNode secondNode = head.next;
        firstNode.next = swapPairs(secondNode.next);
        secondNode.next = firstNode;
        return secondNode;

    }

    /**
     * 反转字符数组
     */
    public static void reverseString(char[] s) {
        helper(0,s);
    }

    public static void helper(int index, char[] s){
        if (s==null || index > s.length/2-1){
            return;
        }
        helper(index+1,s);
        char a = s[index];
        s[index] = s[s.length-index-1];
        s[s.length-index-1] = a;
    }

    /**
     * 字符串反转
     */
    public static String reverseString(String s){
        return "";
    }

}

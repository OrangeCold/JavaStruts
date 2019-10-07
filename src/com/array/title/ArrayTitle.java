package com.array.title;

import org.junit.Test;

public class ArrayTitle {

    /**
     * 稀疏数组:
     * 将普通数组转换为稀疏数组
     */
    @Test
    public void commonTurnSparseArray(){

        // 创建普通二维数组
        int[][] com = new int[11][11];
        for (int[] ints : com) {
            for (int anInt : ints) {
                anInt = 0;
            }
        }
        com[1][2]=1;
        com[2][3]=2;

        System.out.println("普通数组：");
        for (int[] ints : com) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println(" ");
        }


        // 转化为稀疏数组
        int count = 0;
        for (int[] ints : com) {
            for (int anInt : ints) {
                if (anInt != 0){
                    count++;
                }
            }
        }

        int[][] sparse = new int[count+1][3];
        int comRow = com.length;
        int comCol = com[0].length;

        sparse[0][0] = comRow;
        sparse[0][1] = comCol;
        sparse[0][2] = count;

        for (int k = 1; k < sparse.length; ) {
            for (int i = 0; i < com.length; i++) {
                for (int j = 0; j < com[i].length; j++) {
                    if (com[i][j]!=0){
                        sparse[k][0]=i;
                        sparse[k][1]=j;
                        sparse[k][2]=com[i][j];
                        k++;
                    }
                }
            }
        }

        System.out.println("稀疏数组：");
        for (int[] ints : sparse) {

            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }

            System.out.println(" ");
        }


    }

}

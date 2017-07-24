package com.ericsson.eliokog.algrithm;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by eliokog on 2017/6/26.
 */
public class ZeroMatro {

    private static int[][] setMatrixZero(int[][] matrix){
        boolean[] columns = new boolean[matrix[0].length];
        boolean[] rows = new boolean[ matrix.length];

        for(int i=0; i< matrix.length; i++){
            for(int j=0;j<matrix[i].length; j++){
                if(matrix[i][j]==0){
                    rows[i]=true;
                    columns[j]=true;
                }
            }
        }
        for(int i=0; i< matrix.length; i++){
            for(int j=0;j<matrix[i].length; j++){
                if(columns[j]||rows[i]){
                    matrix[i][j]=0;
                }
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4,0},{5,6,7,8}};
         Arrays.stream(setMatrixZero(matrix))
                .flatMapToInt(Arrays::stream).forEach(System.out::println);

    }
}

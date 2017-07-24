    package com.ericsson.eliokog.jzof;

    import java.util.Arrays;

    /**
     * Created by eliokog on 2017/4/6.
     */
    public class DoubleDimensionArry {

        /**
         * @param array  array in the format like {[1,2],{3,4}}
         * @param number
         * @return
         */
        public static boolean contains(int[][] array, int number) {
            for (int i = 0; i < array.length; i++) {
                if (array[i][1] == number) {
                    return true;
                } else if (array[i][1] < number) {
                    continue;
                } else {
                    return array[i][0] == number;
                }
            }
            return false;
        }

        public static void main(String[] args) {
            int[][] array = {{1, 2}, {3, 4}};
            int[][] arrayMutiDem = {{1, 3,5,7,9}, {2, 4,6,8,10},{3,5,7,9,11}};
            System.out.println(contains(array, 3));
            System.out.println(containsMutiDim(array, 3));
            System.out.println(containsMutiDim(arrayMutiDem, 14));

        }

        public static boolean containsMutiDim(int[][] array, int number) {

            if (array == null || array.length < 1 || array[0].length < 1) {
                throw new IllegalArgumentException("array is not valid, array is: " + Arrays.toString(array));
            }

            int rows =array.length-1, colums = array[0].length-1;

           while(rows>-1 && colums>-1 ){
               if(array[rows][colums]== number){
                   return true;
               }else if(array[rows][colums]< number){
                   rows--;
                   colums = array[0].length-1;
               }else{
                   colums--;
               }
           }
           return false;
        }
    }

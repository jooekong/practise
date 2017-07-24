package com.ericsson.eliokog.algrithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by eliokog on 2017/6/27.
 */
public class Codility {


    public static int solution1(int[] A) {
        // write your code in Java SE 8
        int leftSum = 0, rightSum = Arrays.stream(A).sum() - A[0];
        for (int i = 0; i < A.length; i++) {
            if (i > 0 && i < A.length - 1) {
                leftSum += A[i - 1];
                rightSum -= A[i];
            }
            if (rightSum == leftSum) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {-1, 3, -4, 5, 1, -6, 2, 1};
//        System.out.println(solution1(array));
//        solution2(1041);
        int[] a = {3, 8, 9, 7, 6};
        solution(a, 3);
    }



    /**
     * For example, number 9 has binary representation 1001 and contains a binary gap of length 2. The number 529 has binary representation 1000010001 and contains two binary gaps: one of length 4 and one of length 3. The number 20 has binary representation 10100 and contains one binary gap of length 1. The number 15 has binary representation 1111 and has no binary gaps.
     */
    public static int solution2(int N) {
        // write your code in Java SE 8
        char[] hexN = Integer.toBinaryString(N).toCharArray();
        System.out.println(hexN);
        int count = 0;
        int temp = 0;
        boolean isFound = false;
        for (char c : hexN) {
            if (c == 48) {
                temp++;
                isFound = true;
            } else if (isFound) {
                if (count < temp) {
                    count = temp;
                    temp = 0;
                    isFound = false;
                }
            }

        }
        return count;
    }

    /**
     * @param A
     * @return
     */
    public int solution3(int[] A) {
        // write your code in Java SE 8
//        List<Integer> list = Arrays.stream(A).boxed().collect(Collectors.toList());
//        Map<Integer, Long> map = list.stream().collect(Collectors.groupingBy(Function.identity(), counting()));
//        Optional<Integer> keyStr = map.entrySet().stream().filter(e -> e.getValue()==1).map(Map.Entry::getKey).findFirst();
//       return keyStr.orElseGet(()-> -1);
        Map<Integer, Integer> map = new HashMap<>(A.length);
        for (int i : A) {
            map.computeIfPresent(i, (k, v) -> {
                return v + 1;
            });
            map.putIfAbsent(i, 1);
        }
        Optional<Integer> keyStr = map.entrySet().stream().filter(e -> e.getValue() == 1).map(Map.Entry::getKey).findFirst();
        return keyStr.orElseGet(() -> -1);
    }

    /**
     * For example, given array A = [3, 8, 9, 7, 6] and K = 3, the function should return [9, 7, 6, 3, 8].
     * <p>
     * Assume that:
     * <p>
     * N and K are integers within the range [0..100];
     * each element of array A is an integer within the range [−1,000..1,000].
     */
    public static  int[] solution(int[] A, int K) {
        // write your code in Java SE 8
        int[] a = Arrays.copyOfRange(A, A.length - K, A.length);
        int[] b = Arrays.copyOfRange(A, 0, A.length - K );
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));

        int[] result = Arrays.copyOf(a, a.length + b.length);
        System.out.println(Arrays.toString(result));
        System.arraycopy(b, 0, result, a.length, b.length);
        System.out.println(Arrays.toString(result));

        return result;
    }
}

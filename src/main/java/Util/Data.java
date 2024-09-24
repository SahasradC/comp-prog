/*
 * Copyright 2024 Sahasrad Chippa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package Util;

import java.math.BigInteger;
import java.util.List;

/**
 * Provides utility methods for data manipulation, including finding minimum and maximum values,
 * calculating prefix and suffix sums, and performing binary search operations on arrays and lists.
 *
 * Supports various data types including integers, longs, doubles, and generics with Comparable.
 *
 * @author Sahasrad Chippa
 * @version 1.0
 * @since 1.0
 */

public class Data {
    /**
     * Returns the minimum value from an int array.
     *
     * Runs in O(n) time.
     * @param arr The array of integers.
     * @return The minimum integer in the array.
     */
    public static int getMin(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) min = arr[i];
        }
        return min;
    }

    /**
     * Returns the minimum value from a long array.
     *
     * Runs in O(n) time.
     * @param arr The array of longs.
     * @return The minimum long in the array.
     */
    public static long getMin(long[] arr) {
        long min = Long.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) min = arr[i];
        }
        return min;
    }

    /**
     * Returns the minimum value from a double array.
     *
     * Runs in O(n) time.
     * @param arr The array of doubles.
     * @return The minimum double in the array.
     */
    public static double getMin(double[] arr) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) min = arr[i];
        }
        return min;
    }

    /**
     * Returns the minimum value from an array of comparable elements.
     *
     * Runs in O(n) time assuming the compare operation runs in O(1).
     * @param arr The array of Comparable type elements.
     * @param <U> The type of elements in the array, which extends Comparable.
     * @return The minimum element in the array, or null if the array is empty or contains only null.
     */
    public static <U extends Comparable<U>> U getMin(U[] arr) {
        int size = arr.length;
        if (size == 0) return null;
        U min = arr[0];
        for (int i = 1; i < size; i++) {
            U next = arr[i];
            if (next == null) continue;
            if (min == null || next.compareTo(min) < 0) min = next;
        }
        return min;
    }

    /**
     * Returns the minimum value from a list of comparable elements.
     *
     * Runs in O(n) time assuming the compare operation runs in O(1).
     * @param list The list of Comparable elements.
     * @param <U> The type of elements in the list, which extends Comparable.
     * @return The minimum element in the list, or null if the list is empty or contains only null.
     */
    public static <U extends Comparable<U>> U getMin(List<U> list) {
        if (list.isEmpty()) return null;
        U min = list.get(0);
        int size = list.size();
        for (int i = 1; i < size; i++) {
            U next = list.get(i);
            if (next == null) continue;
            if (min == null || next.compareTo(min) < 0) min = next;
        }
        return min;
    }

    /**
     * Returns the maximum value from an int array.
     *
     * Runs in O(n) time.
     * @param arr The array of integers.
     * @return The maximum integer in the array.
     */
    public static int getMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }

    /**
     * Returns the maximum value from a long array.
     *
     * Runs in O(n) time.
     * @param arr The array of longs.
     * @return The maximum long in the array.
     */
    public  static long getMax(long[] arr) {
        long max = Long.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }

    /**
     * Returns the maximum value from a double array.
     *
     * Runs in O(n) time.
     * @param arr The array of doubles.
     * @return The maximum double in the array.
     */
    public static double getMax(double[] arr) {
        double max = Double.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }


    /**
     * Returns the maximum value from a list of comparable elements.
     *
     * Runs in O(n) time assuming the compare operation runs in O(1).
     * @param list The list of Comparable elements.
     * @param <U> The type of elements in the list, which extends Comparable.
     * @return The maximum element in the list, or null if the list is empty or contains only null.
     */
    public static <U extends Comparable<U>> U getMax(List<U> list) {
        if (list.isEmpty()) return null;
        U max = list.get(0);
        int size = list.size();
        for (int i = 1; i < size; i++) {
            U next = list.get(i);
            if (next == null) continue;
            if (max == null || next.compareTo(max) > 0) max = next;
        }
        return max;
    }

    /**
     * Returns the maximum value from an array of comparable elements.
     *
     * Runs in O(n) time assuming the compare operation runs in O(1).
     * @param arr The array of Comparable type elements.
     * @param <U> The type of elements in the array, which extends Comparable.
     * @return The maximum element in the array, or null if the array is empty or contains only null.
     */
    public static <U extends Comparable<U>> U getMax(U[] arr) {
        int size = arr.length;
        if (size == 0) return null;
        U max = arr[0];
        for (int i = 1; i < size; i++) {
            U next = arr[i];
            if (next == null) continue;
            if (max == null || next.compareTo(max) > 0) max = next;
        }
        return max;
    }

    /**
     * Calculates the prefix sum array for an array of integers.
     *
     * Runs in O(n) time.
     * @param arr The array of integers.
     * @return An array representing the prefix sums.
     */
    public static int[] prefSum(int[] arr) {
        int len = arr.length;
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[i] = arr[i] + (i == 0 ? 0 : result[i - 1]);
        }
        return result;
    }

    /**
     * Calculates the prefix sum array for an array of longs.
     *
     * Runs in O(n) time.
     * @param arr The array of longs.
     * @return An array representing the prefix sums.
     */
    public static long[] prefSum(long[] arr) {
        int len = arr.length;
        long[] result = new long[len];
        for (int i = 0; i < len; i++) {
            result[i] = arr[i] + (i == 0 ? 0L : result[i - 1]);
        }
        return result;
    }

    /**
     * Calculates the prefix sum array for an array of doubles.
     *
     * Runs in O(n) time.
     * @param arr The array of doubles.
     * @return An array representing the prefix sums.
     */
    public static double[] prefSum(double[] arr) {
        int len = arr.length;
        double[] result = new double[len];
        for (int i = 0; i < len; i++) {
            result[i] = arr[i] + (i == 0 ? 0 : result[i - 1]);
        }
        return result;
    }

    /**
     * Calculates the prefix sum array for an array of BigIntegers.
     *
     * Runs in O(n) time.
     * @param arr The array of BigIntegers.
     * @return An array representing the prefix sums.
     */
    public static BigInteger[] prefSum(BigInteger[] arr) {
        int len = arr.length;
        BigInteger[] result = new BigInteger[len];
        for (int i = 0; i < len; i++) {
            result[i] = arr[i].add(i == 0 ? BigInteger.ZERO : result[i - 1]);
        }
        return result;
    }

    /**
     * Calculates the suffix sum for an array of integers.
     *
     * Runs in O(n) time.
     * @param arr The array of integers.
     * @return An array representing the suffix sums.
     */
    public static int[] suffixSum(int[] arr) {
        int len = arr.length;
        int[] result = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            result[i] = arr[i] + (i == len - 1 ? 0 : result[i + 1]);
        }
        return result;
    }

    /**
     * Calculates the suffix sum for an array of longs.
     *
     * Runs in O(n) time.
     * @param arr The array of longs.
     * @return An array representing the suffix sums.
     */
    public static long[] suffixSum(long[] arr) {
        int len = arr.length;
        long[] result = new long[len];
        for (int i = len - 1; i >= 0; i--) {
            result[i] = arr[i] + (i == len - 1 ? 0 : result[i + 1]);
        }
        return result;
    }

    /**
     * Calculates the suffix sum for an array of doubles.
     *
     * Runs in O(n) time.
     * @param arr The array of doubles.
     * @return An array representing the suffix sums.
     */
    public static double[] suffixSum(double[] arr) {
        int len = arr.length;
        double[] result = new double[len];
        for (int i = len - 1; i >= 0; i--) {
            result[i] = arr[i] + (i == len - 1 ? 0 : result[i + 1]);
        }
        return result;
    }

    /**
     * Calculates the suffix sum for an array of BigIntegers.
     *
     * Runs in O(n) time.
     * @param arr The array of BigIntegers.
     * @return An array representing the suffix sums.
     */
    public static BigInteger[] suffixSum(BigInteger[] arr) {
        int len = arr.length;
        BigInteger[] result = new BigInteger[len];
        for (int i = len - 1; i >= 0; i--) {
            result[i] = arr[i].add(i == len - 1 ? BigInteger.ZERO : result[i + 1]);
        }
        return result;
    }

    /**
     * Converts an array of primitive ints to an array of Integer objects.
     *
     * Runs in O(n) time.
     * @param arr The primitive int array.
     * @return An array of Integer objects.
     */
    public static Integer[] box(int[] arr) {
        Integer[] boxedArray = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            boxedArray[i] = arr[i];
        }
        return boxedArray;
    }

    /**
     * Converts an array of primitive longs to an array of Long objects.
     *
     * Runs in O(n) time.
     * @param arr The primitive long array.
     * @return An array of Long objects.
     */
    public static Long[] box(long[] arr) {
        Long[] boxedArray = new Long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            boxedArray[i] = arr[i];
        }
        return boxedArray;
    }

    /**
     * Converts an array of primitive doubles to an array of Double objects.
     *
     * Runs in O(n) time.
     * @param arr The primitive double array.
     * @return An array of Double objects.
     */
    public static Double[] box(double[] arr) {
        Double[] boxedArray = new Double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            boxedArray[i] = arr[i];
        }
        return boxedArray;
    }

    /**
     * Converts an array of primitive chars to an array of Character objects.
     *
     * Runs in O(n) time.
     * @param arr The primitive char array.
     * @return An array of Character objects.
     */
    public static Character[] box(char[] arr) {
        Character[] boxedArray = new Character[arr.length];
        for (int i = 0; i < arr.length; i++) {
            boxedArray[i] = arr[i];
        }
        return boxedArray;
    }

    /**
     * Converts an array of primitive booleans to an array of Boolean objects.
     *
     * Runs in O(n) time.
     * @param arr The primitive boolean array.
     * @return An array of Boolean objects.
     */
    public static Boolean[] box(boolean[] arr) {
        Boolean[] boxedArray = new Boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            boxedArray[i] = arr[i];
        }
        return boxedArray;
    }

    /**
     * Finds the index of the first element in a sorted array that is not less than the target value using a lower bound binary search algorithm.
     *
     * Runs in O(log(n)) time assuming the compare operation runs in O(1).
     * @param arr The sorted array of comparable elements.
     * @param target The target value to compare against.
     * @param <U> The type of elements in the array, which extends Comparable.
     * @return The index of the first element in the array that is not less than the target value.
     */
    public static <U extends Comparable<U>> int lowerBound(U[] arr, U target) {
        return Algo.lowerBound(arr, target);
    }

    /**
     * Finds the index of the first element in a sorted array of integers that is not less than the target integer.
     *
     * Runs in O(log(n)) time.
     * @param arr The sorted array of integers.
     * @param target The target integer to compare against.
     * @return The index of the first element in the array that is not less than the target integer.
     */
    public static int lowerBound(int[] arr, int target) {
        return Algo.lowerBound(arr, target);
    }

    /**
     * Finds the index of the first element in a sorted array of longs that is not less than the target long.
     *
     * Runs in O(log(n)) time.
     * @param arr The sorted array of longs.
     * @param target The target long to compare against.
     * @return The index of the first element in the array that is not less than the target long.
     */
    public static int lowerBound(long[] arr, long target) {
        return Algo.lowerBound(arr, target);
    }

    /**
     * Finds the index of the first element in a sorted array of doubles that is not less than the target double.
     *
     * Runs in O(log(n)) time.
     * @param arr The sorted array of doubles.
     * @param target The target double to compare against.
     * @return The index of the first element in the array that is not less than the target double.
     */
    public static int lowerBound(double[] arr, double target) {
        return Algo.lowerBound(arr, target);
    }

    /**
     * Finds the index of the first element in a sorted array of characters that is not less than the target character.
     *
     * Runs in O(log(n)) time.
     * @param arr The sorted array of characters.
     * @param target The target character to compare against.
     * @return The index of the first element in the array that is not less than the target character.
     */
    public static int lowerBound(char[] arr, char target) {
        return Algo.lowerBound(arr, target);
    }

    /**
     * Finds the index of the first element in a sorted list of comparable elements that is not less than the target value using a lower bound binary search algorithm.
     *
     * Runs in O(log(n)) time assuming the compare operation runs in O(1).
     * @param list The sorted list of comparable elements.
     * @param target The target value to compare against.
     * @param <U> The type of elements in the list, which extends Comparable.
     * @return The index of the first element in the list that is not less than the target value.
     */
    public static <U extends Comparable<U>> int lowerBound(List<U> list, U target) {
        return Algo.lowerBound(list, target);
    }

    /**
     * Finds the index of the first element in a sorted array that is greater than the target using an upper bound binary search algorithm.
     *
     * Runs in O(log(n)) time assuming the compare operation runs in O(1).
     * @param arr The sorted array of comparable elements.
     * @param target The target value to compare against.
     * @param <U> The type of elements in the array, which extends Comparable.
     * @return The index of the first element in the array that is greater than the target value.
     */
    public static <U extends Comparable<U>> int upperBound(U[] arr, U target) {
        return Algo.upperBound(arr, target);
    }

    /**
     * Finds the index of the first element in the sorted array of integers that is greater than the target integer.
     *
     * Runs in O(log(n)) time.
     * @param arr The sorted array of integers.
     * @param target The target integer to compare against.
     * @return The index of the first element in the array that is greater than the target integer.
     */
    public static int upperBound(int[] arr, int target) {
        return Algo.upperBound(arr, target);
    }

    /**
     * Finds the index of the first element in the sorted array of longs that is greater than the target long.
     *
     * Runs in O(log(n)) time.
     * @param arr The sorted array of longs.
     * @param target The target long to compare against.
     * @return The index of the first element in the array that is greater than the target long.
     */
    public static int upperBound(long[] arr, long target) {
        return Algo.upperBound(arr, target);
    }

    /**
     * Finds the index of the first element in the sorted array of doubles that is greater than the target double.
     *
     * Runs in O(log(n)) time.
     * @param arr The sorted array of doubles.
     * @param target The target double to compare against.
     * @return The index of the first element in the array that is greater than the target double.
     */
    public static int upperBound(double[] arr, double target) {
        return Algo.upperBound(arr, target);
    }

    /**
     * Finds the index of the first element in the sorted array of characters that is greater than the target character.
     *
     * Runs in O(log(n)) time.
     * @param arr The sorted array of characters.
     * @param target The target character to compare against.
     * @return The index of the first element in the array that is greater than the target character.
     */
    public static int upperBound(char[] arr, char target) {
        return Algo.upperBound(arr, target);
    }

    /**
     * Finds the index of the first element in a sorted list that is greater than the target using an upper bound binary search algorithm.
     *
     * Runs in O(log(n)) time assuming the compare operation runs in O(1).
     * @param list The sorted list of comparable elements.
     * @param target The target value to compare against.
     * @param <U> The type of elements in the list, which extends Comparable.
     * @return The index of the first element in the list that is greater than the target value.
     */
    public static <U extends Comparable<U>> int upperBound(List<U> list, U target) {
        return Algo.upperBound(list, target);
    }
}

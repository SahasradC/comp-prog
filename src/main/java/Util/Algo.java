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

import java.util.List;
import java.util.function.Function;

/**
 * Provides static methods for efficient algorithmic operations
 * like binary search and ternary search on arrays and lists. It supports operations with
 * generic data types to compute insertion points (lower and upper bounds) or find
 * the minimum or maximum of a convex function
 *
 * @author Sahasrad Chippa
 * @version 1.0
 * @since 1.0
 */
public class Algo {

    /**
     * Performs a parameterized binary search to find the smallest index where the target is not greater,
     * comparing using a provided function.
     *
     * Runs in {@code O(log(n))} time assuming the provided function runs in {@code O(1)}.
     * @param l The left boundary of the search interval (inclusive).
     * @param r The right boundary of the search interval (exclusive).
     * @param target The target element to search for.
     * @param function Function converting an index to the comparable item of type U.
     * @param <U> Item type that is Comparable.
     * @return The lower bound index in terms of U.
     */
    public static <U extends Comparable<U>> int lowerBound(int l, int r, U target, Function<Integer, U> function) {
        while (l < r) {
            int mid = (l + r) >> 1;
            U curr = function.apply(mid);
            int c = curr.compareTo(target);
            if (c < 0) {
                l = mid + 1;
            }
            else {
                r = mid;
            }
        }
        return l;
    }

    /**
     * Performs a parameterized binary search using long indices to find the smallest index where the target
     * is not greater, comparing using a provided function.
     *
     * Runs in {@code O(log(n))} time assuming the compare operation and provided function run in {@code O(1)}.
     * @param l The left boundary of the search interval (inclusive).
     * @param r The right boundary of the search interval (exclusive).
     * @param target The target element to search for.
     * @param function Function converting a long index to the comparable item of type U.
     * @param ignored Placeholder to differentiate between int and long method signatures.
     * @param <U> Item type that is Comparable.
     * @return The lower bound index in terms of U.
     */
    public static <U extends Comparable<U>> long lowerBound(long l, long r, U target, Function<Long, U> function, long ignored) {
        while (l < r) {
            long mid = (l + r) >> 1;
            U curr = function.apply(mid);
            int c = curr.compareTo(target);
            if (c < 0) {
                l = mid + 1;
            }
            else {
                r = mid;
            }
        }
        return l;
    }

    /**
     * Finds the index of the first element in a sorted array that is not less than the target value using a lower bound binary search algorithm.
     *
     * Runs in {@code O(log(n))} time assuming the compare operation runs in {@code O(1)}.
     * @param arr The sorted array of comparable elements.
     * @param target The target value to compare against.
     * @param <U> The type of elements in the array, which extends Comparable.
     * @return The index of the first element in the array that is not less than the target value.
     */
    public static <U extends Comparable<U>> int lowerBound(U[] arr, U target) {
        return lowerBound(0, arr.length, target, i -> arr[i]);
    }

    /**
     * Finds the index of the first element in a sorted array of integers that is not less than the target integer.
     *
     * Runs in {@code O(log(n))} time.
     * @param arr The sorted array of integers.
     * @param target The target integer to compare against.
     * @return The index of the first element in the array that is not less than the target integer.
     */
    public static int lowerBound(int[] arr, int target) {
        return lowerBound(0, arr.length, target, i -> arr[i]);
    }

    /**
     * Finds the index of the first element in a sorted array of longs that is not less than the target long.
     *
     * Runs in {@code O(log(n))} time.
     * @param arr The sorted array of longs.
     * @param target The target long to compare against.
     * @return The index of the first element in the array that is not less than the target long.
     */
    public static int lowerBound(long[] arr, long target) {
        return lowerBound(0, arr.length, target, i -> arr[i]);
    }

    /**
     * Finds the index of the first element in a sorted array of doubles that is not less than the target double.
     *
     * Runs in {@code O(log(n))} time.
     * @param arr The sorted array of doubles.
     * @param target The target double to compare against.
     * @return The index of the first element in the array that is not less than the target double.
     */
    public static int lowerBound(double[] arr, double target) {
        return lowerBound(0, arr.length, target, i -> arr[i]);
    }

    /**
     * Finds the index of the first element in a sorted array of characters that is not less than the target character.
     *
     * Runs in {@code O(log(n))} time.
     * @param arr The sorted array of characters.
     * @param target The target character to compare against.
     * @return The index of the first element in the array that is not less than the target character.
     */
    public static int lowerBound(char[] arr, char target) {
        return lowerBound(0, arr.length, target, i -> arr[i]);
    }

    /**
     * Finds the index of the first element in a sorted list of comparable elements that is not less than the target value using a lower bound binary search algorithm.
     *
     * Runs in {@code O(log(n))} time assuming the compare operation runs in {@code O(1)}.
     * @param list The sorted list of comparable elements.
     * @param target The target value to compare against.
     * @param <U> The type of elements in the list, which extends Comparable.
     * @return The index of the first element in the list that is not less than the target value.
     */
    public static <U extends Comparable<U>> int lowerBound(List<U> list, U target) {
        return lowerBound(0, list.size(), target, i -> list.get(i));
    }

    /**
     * Finds the index of the first element greater than the target using a custom function mapping integers
     * to comparable items. This is a generic implementation of an upper bound binary search algorithm.
     *
     * Runs in {@code O(log(n))} time assuming the compare operation and provided function run in {@code O(1)}.
     * @param l The left boundary of the search interval (inclusive).
     * @param r The right boundary of the search interval (exclusive).
     * @param target The target value to find the upper bound for.
     * @param function The function that maps from integer indices to U instances.
     * @param <U> The type of comparable items.
     * @return The index of the first element that is greater than the target.
     */
    public static <U extends Comparable<U>> int upperBound(int l, int r, U target, Function<Integer, U> function) {
        while (l < r) {
            int mid = (l + r) >> 1;
            U curr = function.apply(mid);
            int c = curr.compareTo(target);
            if (c <= 0) {
                l = mid + 1;
            }
            else {
                r = mid;
            }
        }
        return l;
    }

    /**
     * Performs a binary search to find the upper bound (index of first element greater than the target) in a wider range specified using long indices.
     *
     * Runs in {@code O(log(n))} time assuming the compare operation and provided function run in {@code O(1)}.
     * @param l The left boundary of the search interval (inclusive).
     * @param r The right boundary of the search interval (exclusive).
     * @param target The target value to compare against.
     * @param function A function mapping long index values to comparable items.
     * @param ignored A parameter used to distinguish this method signature.
     * @param <U> The type of the elements being compared, which must be Comparable.
     * @return The smallest index at which the elements are greater than the provided target.
     */
    public static <U extends Comparable<U>> long upperBound(long l, long r, U target, Function<Long, U> function, long ignored) {
        while (l < r) {
            long mid = (l + r) >> 1;
            U curr = function.apply(mid);
            int c = curr.compareTo(target);
            if (c <= 0) {
                l = mid + 1;
            }
            else {
                r = mid;
            }
        }
        return l;
    }

    /**
     * Finds the index of the first element in a sorted array that is greater than the target using an upper bound binary search algorithm.
     *
     * Runs in {@code O(log(n))} time assuming the compare operation runs in {@code O(1)}.
     * @param arr The sorted array of comparable elements.
     * @param target The target value to compare against.
     * @param <U> The type of elements in the array, which extends Comparable.
     * @return The index of the first element in the array that is greater than the target value.
     */
    public static <U extends Comparable<U>> int upperBound(U[] arr, U target) {
        return upperBound(0, arr.length, target, i -> arr[i]);
    }

    /**
     * Finds the index of the first element in the sorted array of integers that is greater than the target integer.
     *
     * Runs in {@code O(log(n))} time.
     * @param arr The sorted array of integers.
     * @param target The target integer to compare against.
     * @return The index of the first element in the array that is greater than the target integer.
     */
    public static int upperBound(int[] arr, int target) {
        return upperBound(0, arr.length, target, i -> arr[i]);
    }

    /**
     * Finds the index of the first element in the sorted array of longs that is greater than the target long.
     *
     * Runs in {@code O(log(n))} time.
     * @param arr The sorted array of longs.
     * @param target The target long to compare against.
     * @return The index of the first element in the array that is greater than the target long.
     */
    public static int upperBound(long[] arr, long target) {
        return upperBound(0, arr.length, target, i -> arr[i]);
    }

    /**
     * Finds the index of the first element in the sorted array of doubles that is greater than the target double.
     *
     * Runs in {@code O(log(n))} time.
     * @param arr The sorted array of doubles.
     * @param target The target double to compare against.
     * @return The index of the first element in the array that is greater than the target double.
     */
    public static int upperBound(double[] arr, double target) {
        return upperBound(0, arr.length, target, i -> arr[i]);
    }

    /**
     * Finds the index of the first element in the sorted array of characters that is greater than the target character.
     *
     * Runs in {@code O(log(n))} time.
     * @param arr The sorted array of characters.
     * @param target The target character to compare against.
     * @return The index of the first element in the array that is greater than the target character.
     */
    public static int upperBound(char[] arr, char target) {
        return upperBound(0, arr.length, target, i -> arr[i]);
    }

    /**
     * Finds the index of the first element in a sorted list that is greater than the target using an upper bound binary search algorithm.
     *
     * Runs in {@code O(log(n))} time assuming the compare operation runs in {@code O(1)}.
     * @param list The sorted list of comparable elements.
     * @param target The target value to compare against.
     * @param <U> The type of elements in the list, which extends Comparable.
     * @return The index of the first element in the list that is greater than the target value.
     */
    public static <U extends Comparable<U>> int upperBound(List<U> list, U target) {
        return upperBound(0, list.size(), target, i -> list.get(i));
    }

    /**
     * Performs a ternary search on an integer range to find a local minimum point in a convex or unimodal function.
     *
     * Runs in {@code O(log(n))} time.
     * @param l The left boundary index (inclusive).
     * @param r The right boundary index (inclusive).
     * @param function A function that maps an integer to a comparable item type U.
     * @param <U> The type of the elements being compared, which must extend Comparable.
     * @return The index corresponding to the local minimum of the function.
     */
    public static <U extends Comparable<U>> int convexMin(int l, int r, Function<Integer, U> function) {
        while (l < r) {
            int diff = (r - l) / 3;
            int mid1 = l + diff;
            int mid2 = r - diff;
            U curr1 = function.apply(mid1);
            U curr2 = function.apply(mid2);
            int c = curr1.compareTo(curr2);
            if (c < 0) {
                r = mid2 - 1;
            }
            else if (c > 0) {
                l = mid1 + 1;
            }
            else {
                l = mid1;
                r = mid2 - 1;
            }
        }
        return l;
    }

    /**
     * Performs a ternary search on an integer range to find a local maximum point in a convex or unimodal function.
     *
     * Runs in {@code O(log(n))} time assuming the compare operation and provided function run in {@code O(1)}.
     * @param l The left boundary index (inclusive).
     * @param r The right boundary index (inclusive).
     * @param function A function that maps an integer to a comparable item type U.
     * @param <U> The type of the elements being compared, which must extend Comparable.
     * @return The index corresponding to the local maximum of the function.
     */
    public static <U extends Comparable<U>> int convexMax(int l, int r, Function<Integer, U> function) {
        while (l < r) {
            int diff = (r - l) / 3;
            int mid1 = l + diff;
            int mid2 = r - diff;
            U curr1 = function.apply(mid1);
            U curr2 = function.apply(mid2);
            int c = curr1.compareTo(curr2);
            if (c > 0) {
                r = mid2 - 1;
            }
            else if (c < 0) {
                l = mid1 + 1;
            }
            else {
                l = mid1;
                r = mid2 - 1;
            }
        }
        return l;
    }

    /**
     * Performs a ternary search on a long range to find a local minimum point in a convex or unimodal function.
     *
     * Runs in {@code O(log(n))} time assuming the compare operation and provided function run in {@code O(1)}.
     * @param l The left boundary index (inclusive).
     * @param r The right boundary index (inclusive).
     * @param function A function that maps a long index to a comparable item type U.
     * @param ignored Placeholder parameter to distinguish method signature.
     * @param <U> The type of the elements being compared, which must extend Comparable.
     * @return The index corresponding to the local minimum of the function.
     */
    public static <U extends Comparable<U>> long convexMin(long l, long r, Function<Long, U> function, long ignored) {
        while (l < r) {
            long diff = (r - l) / 3L;
            long mid1 = l + diff;
            long mid2 = r - diff;
            U curr1 = function.apply(mid1);
            U curr2 = function.apply(mid2);
            int c = curr1.compareTo(curr2);
            if (c < 0) {
                r = mid2 - 1;
            }
            else if (c > 0) {
                l = mid1 + 1;
            }
            else {
                l = mid1;
                r = mid2 - 1;
            }
        }
        return l;
    }

    /**
     * Performs a ternary search on a long range to find a local maximum point in a convex or unimodal function.
     *
     * Runs in {@code O(log(n))} time assuming the compare operation and provided function run in {@code O(1)}.
     * @param l The left boundary index (inclusive).
     * @param r The right boundary index (inclusive).
     * @param function A function that maps a long index to a comparable item type U.
     * @param ignored Placeholder parameter to distinguish method signature.
     * @param <U> The type of the elements being compared, which must extend Comparable.
     * @return The index corresponding to the local maximum of the function.
     */
    public static <U extends Comparable<U>> long convexMax(long l, long r, Function<Long, U> function, long ignored) {
        while (l < r) {
            long diff = (r - l) / 3L;
            long mid1 = l + diff;
            long mid2 = r - diff;
            U curr1 = function.apply(mid1);
            U curr2 = function.apply(mid2);
            int c = curr1.compareTo(curr2);
            if (c > 0) {
                r = mid2 - 1;
            }
            else if (c < 0) {
                l = mid1 + 1;
            }
            else {
                l = mid1;
                r = mid2 - 1;
            }
        }
        return l;
    }

    private static double error = 1e-9;

    /**
     * Retrieves the current error tolerance for calculations involving double precision.
     * The error tolerance is used in floating-point comparisons and controls the precision of the
     * approximation in methods like convexMin and convexMax for double types.
     *
     * @return The current error tolerance used in floating-point calculations.
     */
    public static double getError() {
        return error;
    }

    /**
     * Sets a new error tolerance for calculations involving double precision.
     * This error defines the precision level for methods that deal with floating-point numbers and
     * determines how close the approximation should be to the actual value. An error must be a non-negative value.
     *
     * @param error The new error tolerance value to set, which cannot be negative.
     * @throws IllegalArgumentException if the specified error is negative.
     */
    public static void setError(double error) {
        if (error < 0) throw new IllegalArgumentException("Error cannot be negative");
        Algo.error = error;
    }

    /**
     * Performs a ternary search on a double range to find a local minimum point in a convex or unimodal function.
     * This method uses a predefined error tolerance to decide the precision of the result, see {@link #setError(double)}.
     * The {@link #error} defines how close the algorithm needs to approach the minimal value before stopping.
     * Smaller values of {@link #error} increase the precision but also increase computational demand.
     *
     * Runs in {@code O(log(n))} time assuming the compare operation and provided function run in {@code O(1)}.
     * @param l The left boundary value (inclusive).
     * @param r The right boundary value (inclusive).
     * @param function A function that maps a double to a comparable item type U.
     * @param ignored Placeholder parameter to avoid method signature clashes.
     * @param <U> The type of elements being compared, which must extend Comparable.
     * @return The value corresponding to the local minimum of the function.
     */
    public static <U extends Comparable<U>> double convexMin(double l, double r, Function<Double, U> function, boolean ignored) {
        while (r - l >= error) {
            double diff = (r - l) / 3.0;
            double mid1 = l + diff;
            double mid2 = r - diff;
            U curr1 = function.apply(mid1);
            U curr2 = function.apply(mid2);
            int c = curr1.compareTo(curr2);
            if (c < 0) {
                r = mid2;
            }
            else if (c > 0) {
                l = mid1;
            }
            else {
                l = mid1;
                r = mid2;
            }
        }
        return (l + r)/2.0;
    }

    /**
     * Performs a ternary search on a double range to find a local maximum point in a convex or unimodal function.
     * This method uses a predefined error tolerance to decide the precision of the result, see {@link #setError(double)}.
     * The {@link #error} defines how close the algorithm needs to approach the maximal value before stopping.
     * Smaller values of {@link #error} increase the precision but also increase computational demand.
     *
     * Runs in {@code O(log(n))} time assuming the compare operation and provided function run in {@code O(1)}.
     * @param l The left boundary value (inclusive).
     * @param r The right boundary value (inclusive).
     * @param function A function that maps a double to a comparable item type U.
     * @param ignored Placeholder parameter to avoid method signature clashes.
     * @param <U> The type of elements being compared, which must extend Comparable.
     * @return The value corresponding to the local maximum of the function.
     */
    public static <U extends Comparable<U>> double convexMax(double l, double r, Function<Double, U> function, boolean ignored) {
        while (r - l >= error) {
            double diff = (r - l) / 3.0;
            double mid1 = l + diff;
            double mid2 = r - diff;
            U curr1 = function.apply(mid1);
            U curr2 = function.apply(mid2);
            int c = curr1.compareTo(curr2);
            if (c > 0) {
                r = mid2;
            }
            else if (c < 0) {
                l = mid1;
            }
            else {
                l = mid1;
                r = mid2;
            }
        }
        return (l + r)/2.0;
    }
}

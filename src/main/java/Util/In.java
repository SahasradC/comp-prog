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

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * This class provides methods for input operations from various sources such as system input, files, and streams,
 * and supports the parsing of primitive types and strings, including array and list formats.
 *
 * Can use a custom delimiter for tokenizing input strings, but uses whitespace by default
 */
public class In {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer curr;
    private static String delim = " \t\n\r\f";

    /**
     * Gets the current delimiter string used to parse tokens.
     * @return The string representing current delimiters.
     */
    public static String getDelimiter() {
        return delim;
    }

    /**
     * Sets a new delimiter for tokenizing input strings.
     * @param delim The string containing delimiter characters.
     * @return True if there are no more tokens to process from current input line, false otherwise.
     */
    public static boolean setDelimiter(String delim) {
        In.delim = delim;
        return curr == null;
    }

    /**
     * Sets the input source to a specific {@link InputStream}.
     * @param in The input stream to read from.
     */
    public static void setInput(InputStream in) {
        br = new BufferedReader(new InputStreamReader(in));
    }

    /**
     * Sets the input source to a file via its filename.
     * @param fileName The name of the file to read from.
     * @throws RuntimeException if the file is not found.
     */
    public static void setFileInput(String fileName) {
        try {
            br = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads the next token from input.
     * @return The next token as a String
     * @throws RuntimeException if no more tokens are available or an error occurs.
     */
    public static String next() {
        if (curr == null || !curr.hasMoreTokens()) {
            try {
                curr = new StringTokenizer(br.readLine(), delim);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        String result = curr.nextToken();
        if (!curr.hasMoreTokens()) {
            curr = null;
        }
        return result;
    }

    /**
     * Reads the next token and tries to parse it as an int.
     * @return The parsed int.
     */
    public static int nextInt() {
        return Integer.parseInt(next());
    }

    /**
     * Reads the next token and tries to parse it as a long.
     * @return The parsed long.
     */
    public static long nextLong() {
        return Long.parseLong(next());
    }

    /**
     * Reads the next token and tries to parse it as a double.
     * @return The parsed double.
     */
    public static double nextDouble() {
        return Double.parseDouble(next());
    }

    /**
     * Reads the next token and tries to parse it as a {@link BigInteger}.
     * @return The parsed {@link BigInteger}.
     */
    public static BigInteger nextBigInteger() {
        return new BigInteger(next());
    }

    /**
     * Reads the next token and tries to parse it as a boolean.
     * @return The parsed boolean.
     */
    public static boolean nextBoolean() {
        return Boolean.parseBoolean(next());
    }

    /**
     * Reads the next token and interprets it as a binary string where each 'truth' character (typically '1') is true.
     * @param truth The character interpreted as true.
     * @return An array of booleans representing the binary string.
     */
    public static boolean[] nextBinaryString(char truth) {
        String s = next();
        int n = s.length();
        boolean[] result = new boolean[n];
        for (int i = 0; i < n; i++) {
            result[i] = s.charAt(i) == truth;
        }
        return result;
    }

    /**
     * Reads until the end of the current line and returns all remaining tokens as a single string.
     * @return The complete line or remaining tokens fused into one line.
     */
    public static String nextLine() {
        if (curr == null || !curr.hasMoreTokens()) {
            try {
                return br.readLine();
            } catch (IOException e) {
                return null;
            }
        }
        StringBuilder remaining = new StringBuilder();
        remaining.append(curr.nextToken());
        while (curr.hasMoreTokens()) {
            remaining.append(' ').append(curr.nextToken());
        }
        curr = null;
        return remaining.toString();
    }

    // Methods for reading arrays and lists of various types follow similar structure
    // They read multiple elements based on a specified count, parse them, and store them in arrays or lists.

    /**
     * Reads and returns an array of strings from the input.
     * @param n The number of strings to read.
     * @return A string array containing the read strings.
     */
    public static String[] nextStringArray(int n) {
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = next();
        }
        return arr;
    }

    /**
     * Reads and returns an array of integers from the input.
     * @param n The number of integers to read.
     * @return An integer array containing the read integers.
     */
    public static int[] nextIntArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nextInt();
        }
        return arr;
    }

    /**
     * Reads and returns an array of long integers from the input.
     * @param n The number of long integers to read.
     * @return A long integer array containing the read long integers.
     */
    public static long[] nextLongArray(int n) {
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nextLong();
        }
        return arr;
    }

    /**
     * Reads and returns an array of double values from the input.
     * @param n The number of double values to read.
     * @return A double array containing the read values.
     */
    public static double[] nextDoubleArray(int n) {
        double[] arr = new double[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nextDouble();
        }
        return arr;
    }

    /**
     * Reads and returns an array of BigInteger values.
     * @param n The number of BigInteger values to read.
     * @return An array of BigInteger values.
     */
    public static BigInteger[] nextBigIntegerArray(int n) {
        BigInteger[] arr = new BigInteger[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nextBigInteger();
        }
        return arr;
    }

    /**
     * Reads and returns an array of boolean values interpreted from the input.
     * @param n The number of boolean values to read.
     * @return A boolean array containing the read values.
     */
    public static boolean[] nextBooleanArray(int n) {
        boolean[] arr = new boolean[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nextBoolean();
        }
        return arr;
    }

    /**
     * Reads and returns a list of strings from the input.
     * @param n The number of strings to read.
     * @return A list containing the read strings.
     */
    public static List<String> nextStringList(int n) {
        List<String> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(next());
        }
        return list;
    }

    /**
     * Reads and returns a list of integers from the input.
     * @param n The number of integers to read.
     * @return A list containing the read integers.
     */
    public static List<Integer> nextIntList(int n) {
        List<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(nextInt());
        }
        return list;
    }

    /**
     * Reads and returns a list of long integers from the input.
     * @param n The number of long integers to read.
     * @return A list containing the read long integers.
     */
    public static List<Long> nextLongList(int n) {
        List<Long> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(nextLong());
        }
        return list;
    }

    /**
     * Reads and returns a list of double values from the input.
     * @param n The number of double values to read.
     * @return A list containing the read double values.
     */
    public static List<Double> nextDoubleList(int n) {
        List<Double> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(nextDouble());
        }
        return list;
    }

    /**
     * Reads and returns a list of BigInteger values from the input.
     * @param n The number of BigInteger values to read.
     * @return A list containing the read BigInteger values.
     */
    public static List<BigInteger> nextBigIntegerList(int n) {
        List<BigInteger> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(nextBigInteger());
        }
        return list;
    }

    /**
     * Reads and returns a list of boolean values interpreted from the input.
     * @param n The number of boolean values to read.
     * @return A list containing the read boolean values.
     */
    public static List<Boolean> nextBooleanList(int n) {
        List<Boolean> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(nextBoolean());
        }
        return list;
    }
}
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

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * This class serves as a utility for output operations, allowing for redirection
 * of output streams, file output, custom delimiters, and built-in support for printing
 * various data types, including arrays and collections.
 *
 * Output must be flushed with {@link #flush()} by the end of the program
 * especially when {@link Constants#debug} is true, which is by default
 * when there an `ONLINE_JUDGE` system property is present (Codeforces, AtCoder).
 *
 * This is meant to preserve runtime over standard input {@link System#out} by flushing only once.
 *
 * @author Sahasrad Chippa
 * @version 1.0
 * @since 1.0
 */
public class Out {
    private static PrintWriter pw = new PrintWriter(System.out);
    private static String delim = " ";

    /**
     * Retrieves the current writer object.
     * @return The current {@link PrintWriter} instance being used for output.
     */
    public static PrintWriter getWriter() {
        return pw;
    }

    /**
     * Sets the {@link PrintWriter} to a specified writer, allowing redirection of the output.
     * @param writer The new printer writer.
     */
    public static void setWriter(PrintWriter writer) {
        Out.pw = writer;
    }

    /**
     * Sets the current output stream to a new {@link OutputStream}.
     * @param out The output stream to write to.
     */
    public static void setOutput(OutputStream out) {
        pw = new PrintWriter(out);
    }

    /**
     * Sets the output to a file with the specified filename.
     * @param fileName The name of the file to write output to.
     * @throws IllegalArgumentException if a file with {@code fileName} cannot be found or created.
     */
    public static void setOutputFile(String fileName) {
        try {
            pw = new PrintWriter(fileName);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Gets the current delimiter used in output methods that handle iterables and arrays.
     * @return The current delimiter string.
     */
    public static String getDelimiter() {
        return delim;
    }

    /**
     * Sets a new delimiter for output methods to use when handling iterables and arrays.
     * @param delim The new delimiter string to use.
     */
    public static void setDelimiter(String delim) {
        Out.delim = delim;
    }

    /**
     * Outputs a new line character to the output stream.
     */
    public static void println() {
        pw.println();
        if (Constants.debug) pw.flush();
    }

    /**
     * Prints an object to the output. Special handling for array types,
     * prints arrays' contents directly.
     *
     * Output must be flushed with {@link #flush()} by the end of the program
     *
     * @param x The object to print.
     */
    @SuppressWarnings("all")
    public static void print(Object x) {
        if (x instanceof Object[] || x.getClass().isArray()) {
            String result = "";

            if (x instanceof int[]) {
                result = Arrays.toString((int[]) x);
            } else if (x instanceof long[]) {
                result = Arrays.toString((long[]) x);
            } else if (x instanceof double[]) {
                result = Arrays.toString((double[]) x);
            } else if (x instanceof float[]) {
                result = Arrays.toString((float[]) x);
            } else if (x instanceof boolean[]) {
                result = Arrays.toString((boolean[]) x);
            } else if (x instanceof short[]) {
                result = Arrays.toString((short[]) x);
            } else if (x instanceof char[]) {
                result = Arrays.toString((char[]) x);
            } else if (x instanceof byte[]) {
                result = Arrays.toString((byte[]) x);
            } else {
                result = Arrays.toString((Object[]) x);
            }
            pw.print(result);
        } else {
            pw.print(x);
        }
        if (Constants.debug) pw.flush();
    }

    /**
     * Prints an object to the output followed by a newline. It handles arrays
     * by printing their String representation.
     *
     * Output must be flushed with {@link #flush()} by the end of the program
     *
     * @param x The object to print.
     */
    public static void println(Object x) {
        print(x);
        println();
    }

    /**
     * Prints each element of the given iterable to the output, separated by the current delimiter.
     *
     * Output must be flushed with {@link #flush()} by the end of the program
     *
     * @param <T> The type of elements in the iterable.
     * @param arr The iterable to print.
     */
    public static <T> void iterPrint(Iterable<T> arr) {
        boolean space = false;
        for (T t : arr) {
            if (space) print(delim);
            print(t);
            space = true;
        }
        println();
    }

    /**
     * Prints each element of an array to the output, separated by the current delimiter (a space by default).
     *
     * Output must be flushed with {@link #flush()} by the end of the program
     *
     * @param <T> The type of elements in the array.
     * @param arr The array to print.
     */
    public static <T> void iterPrint(T[] arr) {
        boolean space = false;
        for (T t : arr) {
            if (space) print(delim);
            print(t);
            space = true;
        }
        println();
    }

    /**
     * Iterative printing for integer arrays, printing each element separated by the current delimiter (a space by default).
     *
     * @param arr The array to print.
     */
    public static void iterPrint(int[] arr) {
        boolean space = false;
        for (int t : arr) {
            if (space) print(delim);
            print(t);
            space = true;
        }
        println();
    }

    /**
     * Iterative printing for long arrays, printing each element separated by the current delimiter (a space by default).
     *
     * @param arr The array to print.
     */
    public static void iterPrint(long[] arr) {
        boolean space = false;
        for (long t : arr) {
            if (space) print(delim);
            print(t);
            space = true;
        }
        println();
    }

    /**
     * Iterative printing for double arrays, printing each element separated by the current delimiter (a space by default).
     *
     * @param arr The array to print.
     */
    public static void iterPrint(double[] arr) {
        boolean space = false;
        for (double t : arr) {
            if (space) print(delim);
            print(t);
            space = true;
        }
        println();
    }

    /**
     * Iterative printing for char arrays, printing each element separated by the current delimiter (a space by default).
     *
     * @param arr The array to print.
     */
    public static void iterPrint(char[] arr) {
        boolean space = false;
        for (char t : arr) {
            if (space) print(delim);
            print(t);
            space = true;
        }
        println();
    }

    /**
     * Iterative printing for boolean arrays, printing each element separated by the current delimiter (a space by default).
     *
     * @param arr The array to print.
     */
    public static void iterPrint(boolean[] arr) {
        boolean space = false;
        for (boolean t : arr) {
            if (space) print(delim);
            print(t);
            space = true;
        }
        println();
    }

    /**
     * Provides formatted printing using {@link String#format} like syntax.
     * @param format The string format to use.
     * @param args The arguments to use in the format string.
     */
    public static void printf(String format, Object... args) {
        pw.printf(format, args);
        if (Constants.debug) pw.flush();
    }

    /**
     * Flushes the {@link PrintWriter}, ensuring all output is written out immediately.
     */
    public static void flush() {
        pw.flush();
    }
}
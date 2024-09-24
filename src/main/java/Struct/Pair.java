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

package Struct;

/**
 * The {@code Pair} class stores two related objects of potentially different types.
 * It is a simple generic container for two elements, commonly known as a tuple.
 *
 * @param <A> the type of the first element in the pair.
 * @param <B> the type of the second element in the pair.
 *
 * @author Sahasrad Chippa
 * @version 1.0
 * @since 1.0
 */
public class Pair<A, B> {
    /**
     * The value stored as the first element.
     */
    public A a;

    /**
     * The value stored as the second element.
     */
    public B b;

    /**
     * Constructs a default {@code Pair} with both elements initialized to {@code null}.
     */
    public Pair() {
        a = null;
        b = null;
    }

    /**
     * Constructs a {@code Pair} with specified elements.
     *
     * @param a the first element
     * @param b the second element
     */
    public Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Returns a string representation of this pair using default delimiters.
     *
     * @return a string in the format '&lt;a, b&gt;'
     */
    @Override
    public String toString() {
        return toString("<", ", ", ">");
    }

    /**
     * Returns a string representation of this pair using custom specified delimiters.
     *
     * @param prefix    the prefix string
     * @param delimiter the delimiter between elements
     * @param suffix    the suffix string
     * @return a formatted string containing the pair's elements
     */
    public String toString(String prefix, String delimiter, String suffix) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        sb.append(a);
        sb.append(delimiter);
        sb.append(b);
        sb.append(suffix);
        return sb.toString();
    }
}
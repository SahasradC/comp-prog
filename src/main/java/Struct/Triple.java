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
 * The {@code Triple} class stores three related objects of potentially different types.
 * This is a simple generic container for three elements.
 *
 * @param <A> the type of the first element
 * @param <B> the type of the second element
 * @param <C> the type of the third element
 */
public class Triple<A, B, C> {
    /**
     * The value stored as the first element.
     */
    public A a;

    /**
     * The value stored as the second element.
     */
    public B b;

    /**
     * The value stored as the third element.
     */
    public C c;

    /**
     * Constructs a default {@code Triple} with all elements initialized to {@code null}.
     */
    public Triple() {
        a = null;
        b = null;
        c = null;
    }

    /**
     * Constructs a {@code Triple} with the specified elements.
     *
     * @param a the first element
     * @param b the second element
     * @param c the third element
     */
    public Triple(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Returns a string representation using default delimiters.
     *
     * @return a string in the format '&lt;a, b, c&gt;'
     */
    @Override
    public String toString() {
        return toString("<", ", ", ">");
    }

    /**
     * Returns a string representation using custom specified delimiters.
     *
     * @param prefix    the prefix string
     * @param delimiter the delimiter between each element
     * @param suffix    the suffix string
     * @return a formatted string containing the triple's elements
     */
    public String toString(String prefix, String delimiter, String suffix) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        sb.append(a);
        sb.append(delimiter);
        sb.append(b);
        sb.append(delimiter);
        sb.append(c);
        sb.append(suffix);
        return sb.toString();
    }
}
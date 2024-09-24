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
 * A {@code Single} is designed to hold an object of a generic type {@code A}.
 * This class provides a simple way to wrap a single object in a container, allowing for
 * operations on the object like setting, getting, and string representation with custom formatting.
 *
 * @param <A> the type of the object that this class holds.
 *
 * @author Sahasrad Chippa
 * @version 1.0
 * @since 1.0
 */
public class Single<A> {
    /**
     * The value stored in this {@code Single} container.
     */
    public A a;

    /**
     * Constructs an empty {@code Single} instance with {@code null} as the value.
     */
    public Single() {
        a = null;
    }

    /**
     * Constructs a {@code Single} instance that wraps the provided object.
     *
     * @param a the object to wrap in this {@code Single} instance
     */
    public Single(A a) {
        this.a = a;
    }

    /**
     * Returns a string representation of the wrapped object, surrounded by {@code "<>"}.
     *
     * @return a string in the format '&lt;a&gt;' where a is the {@code toString} representation of {@link #a}
     */
    @Override
    public String toString() {
        return toString("<", ">");
    }

    /**
     * Returns a string representation of the wrapped object, using custom specified prefix and suffix.
     *
     * @param prefix the string to use before the object's {@code toString} result
     * @param suffix the string to use after the object's {@code toString} result
     * @return a string consisting of the prefix, the object's {@code toString} result, and the suffix
     */
    public String toString(String prefix, String suffix) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        sb.append(a);
        sb.append(suffix);
        return sb.toString();
    }
}
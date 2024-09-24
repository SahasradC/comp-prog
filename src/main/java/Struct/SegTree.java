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

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A generic segment tree implementation that provides efficient range query and point update operations.
 * The tree allows aggregation of results over a segment of an array and is built based on provided mapping and accumulation functions.
 *
 * @param <T> The type of the input elements stored in the initial array.
 * @param <R> The result or operation type after applying the map and accumulation functions.
 *
 * @author Sahasrad Chippa
 * @version 1.0
 * @since 1.0
 */
public class SegTree<T, R> {
    private final BiFunction<R, R, R> accumulator;
    private final Function<T, R> mapper;
    private final int n;
    private final R[] tree;

    /**
     * Constructs a segment tree from an array.
     *
     * Runs in O(n).
     * @param arr An array of type T from which the segment tree will be built.
     * @param individualMapper A function to convert array elements of type T to the segment type R.
     * @param accumulator A function to accumulate two segments of type R.
     */
    @SuppressWarnings("unchecked")
    public SegTree(T[] arr, Function<T, R> individualMapper, BiFunction<R, R, R> accumulator) {
        tree = (R[]) new Object[arr.length << 2];
        this.n = arr.length;
        this.mapper = individualMapper;
        this.accumulator = accumulator;
        build(arr, 0, 0, n - 1);
    }

    /**
     * Constructs a segment tree from a list.
     *
     * Runs in O(n).
     * @param list A list of type T from which the segment tree will be built.
     * @param individualMapper A function to convert list elements of type T to the segment type R.
     * @param accumulator A function to accumulate two segments of type R.
     */
    @SuppressWarnings("unchecked")
    public SegTree(List<T> list, Function<T, R> individualMapper, BiFunction<R, R, R> accumulator) {
        tree = (R[]) new Object[list.size() << 2];
        this.n = list.size();
        this.mapper = individualMapper;
        this.accumulator = accumulator;
        build(list, 0, 0, n - 1);
    }

    private void build(T[] arr, int i, int l, int r) {
        if (l == r) {
            tree[i] = mapper.apply(arr[l]);
            return;
        }
        int m = (l + r) / 2;
        int i1 = i * 2 + 1, i2 = i1 + 1;
        build(arr, i1, l, m);
        build(arr, i2, m + 1, r);
        tree[i] = accumulator.apply(tree[i1], tree[i2]);
    }

    private void build(List<T> list, int i, int l, int r) {
        if (l == r) {
            tree[i] = mapper.apply(list.get(l));
            return;
        }
        int m = (l + r) / 2;
        int i1 = i * 2 + 1, i2 = i1 + 1;
        build(list, i1, l, m);
        build(list, i2, m + 1, r);
        tree[i] = accumulator.apply(tree[i1], tree[i2]);
    }

    /**
     * Queries the aggregate value over a range [l, r].
     * Runs in O(log(n)).
     *
     * @param l The left index of the range.
     * @param r The right index of the range.
     * @return The aggregated result over the range.
     */
    public R query(int l, int r) {
        return query(0, 0, n - 1, l, r);
    }

    private R query(int i, int cl, int cr, int l, int r) {
        if (l > r || l < 0 || r >= n) throw new IllegalArgumentException("Invalid Range: " + l + ", " + r + " for size " + n);
        if (l == cl && r == cr) return tree[i];
        int m = (cl + cr) / 2;
        int i1 = i * 2 + 1, i2 = i1 + 1;
        if (m >= r) {
            return query(i1, cl, m, l, r);
        }
        else if (m < l) {
            return query(i2, m + 1, cr, l, r);
        }
        else {
            return accumulator.apply(query(i1, cl, m, l, m), query(i2, m + 1, cr, m + 1, r));
        }
    }

    /**
     * Updates the value at a specific index.
     * Runs in O(log(n)).
     *
     * @param index The index to update.
     * @param val The new value of type T.
     */
    public void set(int index, T val) {
        update(0, 0, n - 1, index, val);
    }

    private void update(int i, int l, int r, int index, T val) {
        if (l == r) {
            tree[i] = mapper.apply(val);
            return;
        }
        int m = (l + r) / 2;
        int i1 = i * 2 + 1, i2 = i1 + 1;
        if (index <= m) update(i1, l, m, index, val);
        else update(i2, m + 1, r, index, val);
        tree[i] = accumulator.apply(tree[i1], tree[i2]);
    }
}
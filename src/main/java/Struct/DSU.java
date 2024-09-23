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
 * Represents a Disjoint Set Union (DSU) also known as Union-Find data structure.
 * It supports union and find operations, to determine which set a particular element
 * is in, and to unite two sets if they are disjoint.
 */
public class DSU {
    int[] parents;
    int[] sizes;
    int components;

    /**
     * Initializes the DSU with n elements, each element is its own set initially.
     *
     * Runs in O(n).
     * @param n The total number of elements.
     */
    public DSU(int n) {
        parents = new int[n];
        sizes = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            sizes[i] = 1;
        }
        components = n;
    }

    /**
     * Finds the representative of the set that x is a part of.
     * Uses path compression to flatten the structure of the tree whenever it is used,
     * leading to very efficient queries.
     *
     * Runs in O(α(n)), where α is the inverse Ackermann function.
     * @param x The element to find.
     * @return The representative item of the set containing 'x'.
     */
    public int find(int x) {
        return parents[x] == x ? x : (parents[x] = find(parents[x]));
    }

    /**
     * Unites the set that includes 'x' with the set that includes 'y'.
     * Uses union by size, ensuring the smaller set points to the representative of the larger set.
     *
     * Runs in O(α(n)), where α is the inverse Ackermann function.
     * @param x First element.
     * @param y Second element.
     * @return true if the union was successful and the elements were previously in different sets, false otherwise.
     */
    public boolean union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot == yRoot) return false;
        if (sizes[xRoot] < sizes[yRoot]) { return union(yRoot, xRoot); }
        parents[yRoot] = xRoot;
        sizes[xRoot] += sizes[yRoot];
        components--;
        return true;
    }

    /**
     * Checks if the elements 'x' and 'y' are in the same set.
     *
     * Runs in O(α(n)), where α is the inverse Ackermann function.
     * @param x First element.
     * @param y Second element.
     * @return true if 'x' and 'y' are in the same set, false otherwise.
     */
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    /**
     * Checks if all elements are part of a single set.
     *
     * Runs in O(1).
     * @return true if all elements are in one set, false otherwise.
     */
    public boolean fullyConnected() {
        return components == 1;
    }
}
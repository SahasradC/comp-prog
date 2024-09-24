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

/**
 * Constants provides a central location to keep commonly used constants and configurations
 * in competitive programming. This includes debug flags, modulo values
 * for mathematical operations, and predefined direction vectors for grid operations.
 *
 * These public members are meant for the users, who are free to rewrite their values.
 *
 * @author Sahasrad Chippa
 * @version 1.0
 * @since 1.0
 */
public class Constants {

    /**
     * A debug flag that checks whether the application is running in a competitive programming
     * environment (like Codeforces or AtCoder).
     *
     * When set to false, like under an online judge, the {@link Out} class will not
     * automatically flush output after print statements.
     *
     * The user must write a {@link Out#flush()} statement at the end of their code.
     * This is meant to preserve runtime over standard input {@link System#out} by flushing only once.
     */
    public static boolean debug = System.getProperty("ONLINE_JUDGE") == null;

    /**
     * The most common modulo value used in competitive programming.
     * Used in combinatorics and number theory to prevent overflow and maintain manageable number sizes.
     */
    @SuppressWarnings("all")
    public static int MOD = 1_000_000_007;

    /**
     * Alternative modulo constant used in competitive programming.
     * Used when dealing with polynomial multiplications and transforms.
     */
    @SuppressWarnings("all")
    public static int MOD2 = 998_244_353;

    /**
     * A two-dimensional array representing the four orthogonal directions on a two-dimensional grid.
     * These directions are often used in algorithms related to grid exploration or manipulation,
     * such as breadth-first search or depth-first search on a grid.
     * The directions are: down, left, up, right.
     */
    @SuppressWarnings("all")
    public static int[][] dir2 = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    /**
     * A three-dimensional array representing the six principal direction vectors in a three-dimensional space.
     * This is typically used for navigating or exploring grid-based 3D structures.
     * The directions include movements along positive and negative x, y, and z axes.
     */
    @SuppressWarnings("all")
    public static int[][] dir3 = {{1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};
}

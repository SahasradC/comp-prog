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
 * The Calc class provides a series of static methods for performing various mathematical
 * computations like modular inverses, exponentiation, factorial, combinations,
 * greatest common divisor (GCD), least common multiple (LCM), and Euler's Totient function.
 *
 * @author Sahasrad Chippa
 * @version 1.0
 * @since 1.0
 */
public class Calc {

    /**
     * Computes the modular multiplicative inverse of a value under a given modulo using the extended Euclidean algorithm.
     * This method is efficient for large numbers and recursive in nature.
     *
     * Runs in {@code O(log(value % mod))}, worst case {@code O(mod)}.
     * @param val The value for which to find the inverse.
     * @param mod The modulus under which to compute the inverse.
     * @return The modular inverse of the value.
     */
    public static long modInverse(long val, long mod) {
        return (val %= mod) <= 1 ? val : mod - mod / val * modInverse(mod % val, mod) % mod;
    }

    /**
     * Computes the modular inverse using Fermat's Little Theorem, which requires the modulus to be prime.
     *
     * Runs in {@code O(log(mod))}.
     * @param val The value to compute the inverse of.
     * @param mod The modulus. This should be a prime number.
     * @return The modular inverse of the value.
     */
    public static long modInverse2(long val, long mod) {
        return exp(val, mod - 2, mod);
    }

    /**
     * Performs modular exponentiation efficiently using the right-to-left binary method.
     * This method supports both positive and negative exponents.
     *
     * Runs in {@code O(log(pow))}.
     * @param val The base of the exponentiation.
     * @param pow The exponent (can be negative).
     * @param mod The modulus for the operation.
     * @return The result of (val^pow) % mod.
     */
    public static long exp(long val, long pow, long mod) {
        if (pow < 0) return modInverse(exp(val, -pow, mod), mod);
        val %= mod;
        long result = 1;
        while (pow > 0) {
            if ((pow & 1) > 0) result = (result * val) % mod;
            val = (val * val) % mod;
            pow >>= 1;
        }
        return result;
    }

    /**
     * An array to hold the factorial values (not initialized here).
     */
    private static long[] fact = null;

    /**
     * An array to hold the modular inverse factorial values (not initialized here).
     */
    private static long[] invFact = null;

    /**
     * A helper to track the modulus with which the factorial and inverse factorial arrays were initialized.
     */
    private static long tempMod = -1;

    /**
     * Returns an array of computed factorial values up to the provided maximum.
     * Array size is {@code max + 1}.
     * This version does not utilize modulus.
     *
     * Runs in {@code O(max)}.
     * @param max The maximum value for which the factorial is calculated.
     * @return An array of longs with the factorials up to {@code max!}.
     */
    public static long[] factorials(int max) {
        long[] result = new long[max + 1];
        result[0] = 1;
        for (int i = 1; i <= max; i++) {
            result[i] = i * result[i - 1];
        }
        return result;
    }

    /**
     * Returns an array of computer factorial values up to the provided maximum
     * using the given modulus.
     *
     * Runs in {@code O(max)}.
     * @param max The maximum value for which the factorial is calculated.
     * @param mod The modulus to be used for the computation.
     * @return An array of longs with the factorials up to {@code max! % mod}.
     */
    public static long[] factorials(int max, long mod) {
        long[] result = new long[max + 1];
        result[0] = 1;
        for (int i = 1; i <= max; i++) {
            result[i] = (i * result[i - 1]) % mod;
        }
        return result;
    }

    /**
     * Populates the array of internal factorial values without considering modulus.
     * Used for {@link #fact(int)} and {@link #choose(int, int)}
     *
     * Runs in {@code O(max)}.
     * @param max The maximum value for which the factorial is calculated.
     */
    public static void populateFact(int max) {
        fact = factorials(max);
        tempMod = -1;
    }

    /**
     * Populates the internal array of factorial and inverse factorial values using a given modulus.
     * It initializes the required arrays for computing combinations modularly.
     * Used for {@link #fact(int, long)} and {@link #choose(int, int, long)}
     *
     * Runs in {@code O(max)}.
     * @param max The maximum value for which the factorial is calculated.
     * @param mod The modulus to be used for the computation.
     */
    public static void populateFact(int max, long mod) {
        fact = factorials(max, mod);
        invFact = new long[max + 1];
        invFact[max] = modInverse(fact[max], mod);
        for (int i = max; i >= 1; i--) {
            invFact[i - 1] = (i * invFact[i]) % mod;
        }
        tempMod = mod;
    }

    /**
     * Computes the factorial of a number
     * This version does not utilize modulus.
     * Can be initialized with {@link #populateFact(int)}, otherwise auto-initialized.
     *
     * Runs in {@code O(1)} individually, {@code O(n)} if factorials need repopulation.
     * @param n The input number.
     * @return {@code n!} as a long value.
     */
    public static long fact(int n) {
        if (tempMod != -1 || fact == null || fact.length <= n) populateFact(Math.max(n, n << 1));
        return fact[n];
    }

    /**
     * Computes the factorial of n using the given modulus.
     * Can be initialized with {@link #populateFact(int, long)}, otherwise auto-initialized.
     *
     * Runs in {@code O(1)} individually, {@code O(n)} if factorials need repopulation.
     *
     * Changing mod values each call will make this method run in {@code O(n)} every time.
     * Consider using {@link #factorials(int, long)} to create your own factorial
     * arrays for every unique mod value needed.
     * @param n The input number
     * @param mod The modulus.
     * @return {@code n! % mod} as a long value.
     */
    public static long fact(int n, long mod) {
        if (tempMod != mod || fact == null || fact.length <= n) populateFact(Math.max(n, n << 1), mod);
        return fact[n];
    }

    /**
     * Computes n choose k (combinatorics: combinations of n items taken k at a time).
     * This version does not utilize modulus.
     * This method utilizes an internal factorial array that can be initialized
     * with {@link #populateFact(int)}, otherwise auto-initialized.
     *
     * Runs in {@code O(1)} individually, {@code O(n)} if factorials need repopulation.
     * @param n The total number of items.
     * @param k The number of items to choose.
     * @return The number of combinations.
     */
    public static long choose(int n, int k) {
        if (k > n) return 0;
        if (n > 20) {
            k = Math.min(k, n - k);
            long result = 1;
            for (int x = 0; x < k; x++) {
                result *= (n - x);
                result /= (x + 1);
            }
            return result;
        }
        if (tempMod != -1 || fact == null || fact.length <= n) populateFact(Math.max(n, n << 1));
        return fact[n] / fact[k] / fact[n - k];
    }

    /**
     * Computes n choose k under a specific modulus.
     * This method utilizes internal factorial and inverse factorial arrays that
     * can be initialized with {@link #populateFact(int, long)}, otherwise auto-initialized.
     *
     * Runs in {@code O(1)} individually, {@code O(n)} if factorials need repopulation.
     *
     * Changing mod values each call will make this method run in {@code O(n)} every time.
     * Consider using {@link #factorials(int, long)} to create your own factorial
     * arrays for every unique mod value needed.
     * @param n The total number of items.
     * @param k The number of items to choose.
     * @param mod The modulus.
     * @return The number of combinations computed under the given modulus.
     */
    public static long choose(int n, int k, long mod) {
        if (k > n) return 0;
        if (tempMod != mod || fact == null || fact.length <= n || invFact == null || invFact.length <= n) populateFact(Math.max(n, n << 1), mod);
        return ((fact[n] * invFact[k]) % mod * invFact[n - k]) % mod;
    }

    /**
     * Computes the greatest common divisor (GCD) of two integers using the Euclidean algorithm.
     *
     * Runs in {@code O(log(min(a, b)))} time.
     * @param a The first number.
     * @param b The second number.
     * @return The GCD of a and b.
     */
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    /**
     * Computes the greatest common divisor (GCD) of two long integers using the Euclidean algorithm.
     *
     * Runs in {@code O(log(min(a, b)))} time.
     * @param a The first number.
     * @param b The second number.
     * @return The GCD of a and b.
     */
    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    /**
     * Computes the least common multiple (LCM) of two long integers using the relation LCM(a, b) = (a * b) / GCD(a, b).
     *
     * Runs in {@code O(log(min(a, b)))} time
     * @param a The first number.
     * @param b The second number.
     * @return The LCM of a and b.
     */
    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    /**
     * Computes Euler's Totient function φ(n), which counts the number of integers up to n that are coprime to n.
     *
     * Runs in {@code O(sqrt(n))} time.
     * @param n The input number.
     * @return The value of Euler's Totient function for n.
     */
    public static long phi(long n) {
        long result = n;
        for (long x = 2; x * x <= n; x++) {
            if (n % x == 0) {
                while (n % x == 0) n /= x;
                result -= result/x;
            }
        }
        return result - (n > 1 ? result / n : 0);
    }
}

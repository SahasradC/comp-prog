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
     * Runs in O(log(value % mod)), worst case O(mod).
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
     * Runs in O(log(mod)).
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
     * Runs in O(log(pow)).
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
    protected static long[] fact = null;

    /**
     * An array to hold the modular inverse factorial values (not initialized here).
     */
    protected static long[] invFact = null;

    /**
     * A helper to track the modulus with which the factorial and inverse factorial arrays were initialized.
     */
    protected static long tempMod = -1;

    /**
     * Prepares or populates the array of factorial values not considering modulus.
     *
     * Runs in O(max).
     * @param max The maximum value for which the factorial is calculated.
     */
    public static void popFact(int max) {
        fact = new long[max + 1];
        fact[0] = 1;
        for (int i = 1; i <= max; i++) {
            fact[i] = i * fact[i - 1];
        }
        tempMod = -1;
    }

    /**
     * Prepares or populates the array of factorial and inverse factorial values using a given modulus.
     * It initializes the required arrays for computing combinations modularly.
     *
     * Runs in O(max).
     * @param max The maximum value for which the factorial is calculated.
     * @param mod The modulus to be used for the computation.
     */
    public static void popFact(int max, long mod) {
        fact = new long[max + 1];
        invFact = new long[max + 1];
        fact[0] = 1;
        for (int i = 1; i <= max; i++) {
            fact[i] = (i * fact[i - 1]) % mod;
        }
        invFact[max] = modInverse(fact[max], mod);
        for (int i = max; i >= 1; i--) {
            invFact[i - 1] = (i * invFact[i]) % mod;
        }
        tempMod = mod;
    }

    /**
     * Computes n choose k (combinatorics: combinations of n items taken k at a time).
     * This version does not utilize modulus.
     * Can be initialized with {@link #popFact(int)}, otherwise auto-initialized.
     *
     * Runs in O(1) individually, O(n) if factorials need repopulation.
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
        if (tempMod != -1 || fact == null || fact.length <= n) popFact(Math.max(n, n << 1));
        return fact[n] / fact[k] / fact[n - k];
    }

    /**
     * Computes n choose k under a specific modulus. This method utilizes factorial and inverse factorial arrays,
     * Can be initialized with {@link #popFact(int, long)}, otherwise auto-initialized.
     *
     * Runs in O(1) individually, O(n) if factorials need repopulation.
     * @param n The total number of items.
     * @param k The number of items to choose.
     * @param mod The modulus.
     * @return The number of combinations computed under the given modulus.
     */
    public static long choose(int n, int k, long mod) {
        if (k > n) return 0;
        if (tempMod != mod || fact == null || fact.length <= n || invFact == null || invFact.length <= n) popFact(Math.max(n, n << 1), mod);
        return ((fact[n] * invFact[k]) % mod * invFact[n - k]) % mod;
    }

    /**
     * Computes the greatest common divisor (GCD) of two integers using the Euclidean algorithm.
     *
     * Runs in O(log(min(a, b))) time.
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
     * Runs in O(log(min(a, b))) time.
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
     * Runs in O(log(min(a, b))) time
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
     * Runs in O(sqrt(n)) time.
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

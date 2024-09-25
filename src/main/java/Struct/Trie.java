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

import java.util.Arrays;
import java.util.List;

/**
 * A Trie (or Prefix Tree) data structure implementation that supports efficient
 * insertion and search operations for strings. It optimizes space and time complexities
 * where several strings have common prefixes.
 *
 * @author Sahasrad Chippa
 * @version 1.0
 * @since 1.0
 */
public class Trie {
    TrieNode root;
    int[] map;
    int offset;
    int size;

    /**
     * Constructs an empty Trie with default lowercase alphabet settings (a-z).
     */
    public Trie() {
        map = new int[26];
        size = 26;
        for (int i = 0; i < map.length; i++) {
            map[i] = i;
        }
        offset = 'a';
        root = new TrieNode();
    }

    /**
     * Constructs a Trie specifying allowed characters. It customizes the structure
     * to optimize for the provided set of characters.
     *
     * @param allowedChars The array of allowed characters to include in the Trie.
     */
    public Trie(char[] allowedChars) {
        offset = 65535;
        int max = 0;
        size = allowedChars.length;
        for (int i = 0; i < size; i++) {
            char c = allowedChars[i];
            if (c < offset) {
                offset = c;
            }
            if (c > max) {
                max = c;
            }
        }
        map = new int[Math.max(0, max - offset + 1)];
        Arrays.fill(map, -1);
        for (int i = 0; i < size; i++) {
            map[allowedChars[i] - offset] = i;
        }
        root = new TrieNode();
    }

    /**
     * Constructs a Trie specifying allowed characters. It customizes the structure
     * to optimize for the provided set of characters, intended for situations where
     * allowed characters are contained within a list.
     *
     * @param allowedChars The list of allowed characters to include in the Trie.
     */
    public Trie(List<Character> allowedChars) {
        offset = 65535;
        int max = 0;
        size = allowedChars.size();
        for (int i = 0; i < size; i++) {
            char c = allowedChars.get(i);
            if (c < offset) {
                offset = c;
            }
            if (c > max) {
                max = c;
            }
        }
        map = new int[Math.max(0, max - offset + 1)];
        Arrays.fill(map, -1);
        for (int i = 0; i < size; i++) {
            map[allowedChars.get(i) - offset] = i;
        }
        root = new TrieNode();
    }

    /**
     * Inserts a word into the Trie. If any character in the word is out of the allowed range,
     * it throws an exception.
     * Runs in {@code O(m)} where m is the length of the word.
     *
     * @param word A sequence of characters representing the word to insert.
     * @throws IllegalArgumentException If an invalid character is in the input String.
     */
    public void insert(CharSequence word) {
        TrieNode curr = root;
        int len = word.length();
        for (int i = 0; i < len; i++) {
            int j = word.charAt(i) - offset;
            if (j < 0 || j >= map.length) {
                throw new IllegalArgumentException("Inserted Invalid character: " + word.charAt(i));
            }
            int c = map[j];
            if (c < 0) {
                throw new IllegalArgumentException("Inserted Invalid character: " + word.charAt(i));
            }
            if (curr.children[c] == null) {
                curr.children[c] = new TrieNode();
            }
            curr = curr.children[c];
        }
        curr.end = true;
    }

    /**
     * Checks if a word is in the Trie.
     * Runs in {@code O(m)} where m is the length of the word.
     *
     * @param word The word to check.
     * @return true if the Trie contains the word, false otherwise.
     */
    public boolean contains(CharSequence word) {
        TrieNode curr = root;
        int len = word.length();
        for (int i = 0; i < len; i++) {
            int j = word.charAt(i) - offset;
            if (j < 0 || j >= map.length) {
                return false;
            }
            int c = map[j];
            if (c < 0 || curr.children[c] == null) {
                return false;
            }
            curr = curr.children[c];
        }
        return curr.end;
    }

    /**
     * A node representing a single node in the Trie.
     * Manages child nodes and identifies the end of a word.
     */
    private class TrieNode {
        TrieNode[] children = new TrieNode[size];
        boolean end = false;
    }
}
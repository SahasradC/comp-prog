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
 * Represents a node in a doubly linked list, which can be navigated in both directions: forward and backward.
 * Each node contains a value and links to the previous and next nodes in the list.
 *
 * @param <T> The type of the value stored in each node.
 *
 * @author Sahasrad Chippa
 * @version 1.0
 * @since 1.0
 */
public class ListNode<T> {
    ListNode<T> prev;
    ListNode<T> next;
    T val;

    /**
     * Constructs an empty ListNode with null as its value.
     */
    ListNode() {
        val = null;
    }

    /**
     * Constructs a ListNode with a specified value.
     *
     * @param val The value to store in the node.
     */
    ListNode(T val) {
        this.val = val;
    }

    /**
     * Provides a string representation of this node showing connections to its previous and next node values.
     * This method is particularly useful for debugging purposes to visualize the linkage of the node within a list.
     *
     * @return A string representation of the node connections.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (prev != null) {
            result.append(prev.val);
            result.append(" -> ");
        }
        result.append(val);
        if (next != null) {
            result.append(" <- ");
            result.append(next.val);
        }
        return result.toString();
    }

    /**
     * Creates a string representation of all values from this node forwards until the list end.
     * Uses default formatting with brackets and commas.
     *
     * Runs in O(n) time.
     * @return A formatted string of node values forwards.
     */
    public String forwardString() {
        return forwardString("[", ", ", "]");
    }

    /**
     * Creates a string representation of all values from this node backwards until the list start.
     * Uses default formatting with brackets and commas.
     *
     * Runs in O(n) time.
     * @return A formatted string of node values backwards.
     */
    public String backwardString() {
        return backwardString("[", ", ", "]");
    }

    /**
     * Generates a string from this node values moving forward, formatted with specified delimiters.
     *
     * Runs in O(n) time.
     * @param prefix The prefix to start the list representation.
     * @param delim The delimiter to separate node values.
     * @param suffix The suffix to end the list representation.
     * @return A string representation of forward node values with custom formatting.
     */
    public String forwardString(String prefix, String delim, String suffix) {
        StringBuilder result = new StringBuilder();
        result.append(prefix);
        result.append(val);
        ListNode<T> curr = next;
        while (curr != null) {
            result.append(delim);
            result.append(curr.val);
            curr = curr.next;
        }
        result.append(suffix);
        return result.toString();
    }

    /**
     * Generates a string from this node values moving backward, formatted with specified delimiters.
     *
     * Runs in O(n) time.
     * @param prefix The prefix to start the list representation.
     * @param delim The delimiter to separate node values.
     * @param suffix The suffix to end the list representation.
     * @return A string representation of backward node values with custom formatting.
     */
    private String backwardString(String prefix, String delim, String suffix) {
        StringBuilder result = new StringBuilder();
        result.append(suffix);
        result.insert(0, val);
        ListNode<T> curr = prev;
        while (curr != null) {
            result.insert(0, delim);
            result.insert(0, curr.val);
            curr = curr.prev;
        }
        result.insert(0, prefix);
        return result.toString();
    }
}
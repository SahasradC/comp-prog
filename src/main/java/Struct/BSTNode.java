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

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Represents a node in a binary search tree (BST) for elements of a comparable type.
 * This class provides basic BST operations including insertion and traversal.
 *
 * @param <T> The type of values stored in the BST. Must implement Comparable<T>.
 */
public class BSTNode<T extends Comparable<T>> {
    public BSTNode<T> left;
    public BSTNode<T> right;
    public T val;

    /**
     * Initializes a BST node with the specified value.
     * @param val The value for this node.
     */
    public BSTNode(T val) {
        this.val = val;
    }

    /**
     * Adds a node to the correct position in the subtree. Uses T's compareTo method to determine
     * the correct position in the BST. Duplicate values are added to the left subtree.
     *
     * Runs in O(log(n)) assuming the compare operation runs in O(1).
     * @param node The node to add.
     */
    public void add(BSTNode<T> node) {
        int c = node.val.compareTo(val);
        if (c <= 0) {
            if (left == null) {
                left = node;
            }
            else {
                left.add(node);
            }
        }
        else {
            if (right == null) {
                right = node;
            }
            else {
                right.add(node);
            }
        }
    }

    /**
     * Constructs a list of node values following preorder traversal of the BST.
     *
     * Runs in O(n).
     * @return The list of node values in preorder.
     */
    public List<T> toPreorderList() {
        List<T> result = new ArrayList<>();
        executePreOrder(node -> result.add(node.val));
        return result;
    }

    /**
     * Constructs a list of node values following inorder traversal of the BST.
     *
     * Runs in O(n).
     * @return The list of node values in inorder.
     */
    public List<T> toInorderList() {
        List<T> result = new ArrayList<>();
        executeInOrder(node -> result.add(node.val));
        return result;
    }

    /**
     * Constructs a list of node values following postorder traversal of the BST.
     *
     * Runs in O(n) assuming the provided function runs in O(1).
     * @return The list of node values in postorder.
     */
    public List<T> toPostorderList() {
        List<T> result = new ArrayList<>();
        executePostOrder(node -> result.add(node.val));
        return result;
    }

    /**
     * Executes a consumer function on each node in the BST following a preorder traversal.
     *
     * Runs in O(n) assuming the provided function runs in O(1).
     * @param action The consumer function to apply to each node.
     */
    public void executePreOrder(Consumer<BSTNode<T>> action) {
        action.accept(this);
        if (left != null) left.executePreOrder(action);
        if (right != null) right.executePreOrder(action);
    }

    /**
     * Executes a consumer function on each node in the BST following an inorder traversal.
     *
     * Runs in O(n) assuming the provided function runs in O(1).
     * @param action The consumer function to apply to each node.
     */
    public void executeInOrder(Consumer<BSTNode<T>> action) {
        if (left != null) left.executeInOrder(action);
        action.accept(this);
        if (right != null) right.executeInOrder(action);
    }

    /**
     * Executes a consumer function on each node in the BST following a postorder traversal.
     *
     * Runs in O(n) assuming the provided function runs in O(1).
     * @param action The consumer function to apply to each node.
     */
    public void executePostOrder(Consumer<BSTNode<T>> action) {
        if (left != null) left.executePostOrder(action);
        if (right != null) right.executePostOrder(action);
        action.accept(this);
    }

    /**
     * Aggregates results across the BST nodes using a preorder traversal.
     * Uses a mapper {@link Function<BSTNode>} and accumulating {@link BiFunction<R>}
     * @param initial The initial value for accumulation.
     * @param function A function to apply to each node, which generates a result of type R.
     * @param accumulator A function that combines two results of type R.
     * @param <R> The result type.
     * @return The accumulated result after processing all nodes.
     *
     * Runs in O(n) assuming the provided function runs in O(1).
     */
    public <R> R accumulatePreOrder(R initial, Function<BSTNode<T>, R> function, BiFunction<R, R, R> accumulator) {
        R result = initial;
        result = accumulator.apply(result, function.apply(this));
        if (left != null) result = left.accumulatePreOrder(result, function, accumulator);
        if (right != null) result = right.accumulatePreOrder(result, function, accumulator);
        return result;
    }

    /**
     * Aggregates results across the BST nodes using an inorder traversal.
     * Uses a mapper {@link Function<BSTNode>} and accumulating {@link BiFunction<R>}
     * @param initial The initial value for accumulation.
     * @param function A function to apply to each node, which generates a result of type R.
     * @param accumulator A function that combines two results of type R.
     * @param <R> The result type.
     * @return The accumulated result after processing all nodes.
     *
     * Runs in O(n) assuming the provided function runs in O(1).
     */
    public <R> R accumulateInOrder(R initial, Function<BSTNode<T>, R> function, BiFunction<R, R, R> accumulator) {
        R result = initial;
        if (left != null) result = left.accumulateInOrder(result, function, accumulator);
        result = accumulator.apply(result, function.apply(this));
        if (right != null) result = right.accumulateInOrder(result, function, accumulator);
        return result;
    }

    /**
     * Aggregates results across the BST nodes using a postorder traversal.
     * Uses a mapper {@link Function<BSTNode>} and accumulating {@link BiFunction<R>}
     * @param initial The initial value for accumulation.
     * @param function A function to apply to each node, which generates a result of type R.
     * @param accumulator A function that combines two results of type R.
     * @param <R> The result type.
     * @return The accumulated result after processing all nodes.
     *
     * Runs in O(n) assuming the provided function runs in O(1).
     */
    public <R> R accumulatePostOrder(R initial, Function<BSTNode<T>, R> function, BiFunction<R, R, R> accumulator) {
        R result = initial;
        if (left != null) result = left.accumulatePostOrder(result, function, accumulator);
        if (right != null) result = right.accumulatePostOrder(result, function, accumulator);
        result = accumulator.apply(result, function.apply(this));
        return result;
    }
}
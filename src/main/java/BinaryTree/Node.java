package BinaryTree;

import io.vavr.control.Option;
import lombok.Value;

@Value
public class Node<T extends Comparable<T>> {
    T value;
    Option<Node<T>> left;
    Option<Node<T>> right;

    public static <T extends Comparable<T>> Node<T> newNode(T t) {
        return new Node<T>(t, Option.none(), Option.none());
    }

}
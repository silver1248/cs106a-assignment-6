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

    int count() {
        return count(Option.of(this));
    }

    static <U extends Comparable<U>> int count(Option<Node<U>> node) {
        if (node.isEmpty()) {
            return 0;
        } else {
            return 1 + count(node.get().getLeft()) + count(node.get().getRight());
        }
    }
}
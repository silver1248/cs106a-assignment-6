package BinaryTree;

import io.vavr.control.Option;
import lombok.Value;

@Value
public class Tree<T extends Comparable<T>> {
    Option<Node<T>> root;

    public Tree<T> add(T t) {
        return new Tree<>(Option.of(add(root, t)));
    }

    private Node<T> add(Option<Node<T>> root2, T t) {

        if (root2.isEmpty()) {
            return Node.newNode(t);
        } else {
            Node<T> root2Node = root2.get();
            if (t.compareTo(root2Node.getValue()) != 0) {
                if (t.compareTo(root2Node.getValue()) > 0) {
                    return new Node<T>(root2Node.getValue(), root2Node.getLeft(),
                            Option.of(add(root2Node.getRight(), t)));
                } else {
                    return new Node<T>(root2Node.getValue(), Option.of(add(root2Node.getLeft(), t)),
                            root2Node.getRight());
                }
            } else {
                return root2Node;
            }
        }
    }

    public Option<Node<T>> median() {
        if (root.isEmpty()) {
            return Option.none();
        } else {
            return median(root, 0, 0);
        }
    }

    private Option<Node<T>> median(Option<Node<T>> root2, int oldLeft, int oldRight) {
        int newLeft = Node.count(root2.get().getLeft()) + oldLeft;
        int newRight = Node.count(root2.get().getRight()) + oldRight;
        int diff = newRight - newLeft;
        if (diff <= 1 && diff >= -1) {
            return root2;
        } else if (newLeft > newRight) {
            return median(root2.get().getLeft(), oldLeft, newRight + 1);
        } else {
            return median(root2.get().getRight(), newLeft + 1, oldRight);
        }
    }

    public Tree<T> rebalance() {
        return new Tree<T>(rebalance(root));
    }

    public Option<Node<T>> rebalance(Option<Node<T>> root2) {
        return null;
    }

    public int depth() {
        return depth(root);
    }

    private int depth(Option<Node<T>> root2) {
        if (root2.isEmpty()) {
            return 0;
        } else {
            return 1 + Math.max(depth(root2.get().getLeft()), depth(root2.get().getRight()));
        }
    }

    public int count() {
        return Node.count(root);
    }

    public boolean exists(T t) {
        return exists(root, t);
    }

    private boolean exists(Option<Node<T>> root2, T t) {
        if (root2.isEmpty()) {
            return false;
        } else {
            Node<T> root2Node = root2.get();
            return root2Node.getValue().equals(t)
                    || exists(t.compareTo(root2Node.getValue()) < 0 ? root2Node.getLeft() : root2Node.getRight(), t);
        }
    }

}
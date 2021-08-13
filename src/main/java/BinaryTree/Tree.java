package BinaryTree;

import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
        if (root.isEmpty()) {
            return new Tree<>(Option.none());
        } else {
            List<T> list = toList();
            return new Tree<T>(rebalance(root, list, 0, list.length()-1));
        }
    }

    private Option<Node<T>> rebalance(Option<Node<T>> root2, List<T> inList, int low, int high) {
        if (high == low) {
            return Option.of(Node.newNode(inList.get(low)));
        } else {
            int average = (high+low)/2;
            log.debug("value = {}, average = {}, low = {}, high = {}, list = {}"
                    , root2.get().getValue(), average, low, high, inList);

            Node<T> rebalanced = new Node<T>(inList.get(average)
                    , maybeRebalance(root2, inList, low, average-1)
                    , maybeRebalance(root2, inList, average+1, high));
            return Option.of(rebalanced);
        }
    }

    private Option<Node<T>> maybeRebalance(Option<Node<T>> root2, List<T> inList, int low, int high) {
        log.debug("MBR value = {}, low = {}, high = {}, list = {}"
                , root2.get().getValue(), low, high, inList);
        if (high < low) {
            return Option.none();
        } else {
            return rebalance(root2, inList, low, high);
        }
    }

    public List<T> toList() {
        return toList(root);
    }

    private List<T> toList(Option<Node<T>> root2) {
        if (root2.isEmpty()) {
            return List.empty();
        } else {
            return toList(root2.get().getRight()).prepend(root2.get().getValue())
                    .prependAll(toList(root2.get().getLeft()));
        }
    }

    public T median(List<T> in) {
        return in.get(in.length()/2);
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
                    || exists(t.compareTo(root2Node.getValue()) < 0 
                            ? root2Node.getLeft() : root2Node.getRight(), t);
        }
    }

    public Option<List<T>> getPrevious(T t) {
        return getPrevious(root, t);
    }

    private Option<List<T>> getPrevious(Option<Node<T>> root2, T t) {
        if (root2.isEmpty()) {
            return Option.none();
        } else {
            Node<T> root2Node = root2.get();
            int compare = t.compareTo(root2Node.getValue());
            if (0 == compare) {
                return Option.of(List.of(root2Node.getValue()));
            } else {
                return getPrevious(
                           (0 > compare) ? root2Node.getLeft() : root2Node.getRight()
                           , t)
                       .map(l -> l.prepend(root2Node.getValue()));
            }
        }
    }

}
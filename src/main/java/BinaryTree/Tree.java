package BinaryTree;

import io.vavr.control.Option;

public class Tree {
    static Node topNode;
    static Node nextNode;

    public Tree (Node node) {
        Tree.topNode = node;
        Tree.nextNode = topNode;
    }

    public static void add (Node node) {
        if (nextNode.getValue() > node.getValue()) {
            if (Option.none().equals(nextNode.getLeft().getValue())) {
                nextNode = nextNode.getLeft();
            }
        } else if (nextNode.getValue() < node.getValue()) {
            if (Option.none().equals(nextNode.getRight().getValue())) {
                nextNode = nextNode.getRight();
            }
        }
        add(nextNode);
        nextNode = topNode;
    }
}
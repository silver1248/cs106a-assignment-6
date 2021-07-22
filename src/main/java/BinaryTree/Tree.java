package BinaryTree;

import io.vavr.control.Option;

public class Tree {
    static Nodes topNode;
    static Nodes nextNode;

    public Tree (Nodes node) {
        Tree.topNode = node;
        Tree.nextNode = topNode;
    }

    public static void add (Nodes node) {
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
package BinaryTree;

import io.vavr.control.Option;
import lombok.Value;

@Value
public class Node {
    static Option<Node> left;
    static Option<Node> right;
    int value;
    
    public Node(Option<Node> left, Option<Node> right, int value) {
        Node.left = left;
        Node.right = right;
        this.value = value;
    }
    
    public Node getLeft() {
        return left.get();
    }
    
    public Option<Node> setLeft(Node leftVal) {
        left = Option.some(leftVal);
        return left;
    }
    
    public Node getRight() {
        return right.get();
    }
    
    public Option<Node> setRight(Node rightVal) {
        right = Option.some(rightVal);
        return right;
    }
}
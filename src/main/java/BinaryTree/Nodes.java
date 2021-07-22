package BinaryTree;

import io.vavr.control.Option;
import lombok.Value;

@Value
public class Nodes {
    static Option<Nodes> left;
    static Option<Nodes> right;
    int value;
    
    public Nodes(Option<Nodes> left, Option<Nodes> right, int value) {
        Nodes.left = left;
        Nodes.right = right;
        this.value = value;
    }
    
    public Nodes getLeft() {
        return left.get();
    }
    
    public Option<Nodes> setLeft(Nodes leftVal) {
        left = Option.some(leftVal);
        return left;
    }
    
    public Nodes getRight() {
        return right.get();
    }
    
    public Option<Nodes> setRight(Nodes rightVal) {
        right = Option.some(rightVal);
        return right;
    }
}
package BinaryTree;

import io.vavr.control.Option;
import lombok.Value;

@Value
public class Tree <T extends Comparable<T>> {
    Option<Node<T>> root;

    public Tree<T> add (T t) {
        return new Tree<>(Option.of(add (root, t)));
    }

    private Node<T> add(Option<Node<T>> root2, T t) {

        if (root2.isEmpty()) {
            return Node.newNode(t);
        } else {
            Node<T> root2Node = root2.get();
            if (t.compareTo(root2Node.getValue()) != 0) {
                if (t.compareTo(root2Node.getValue()) > 0) {
                    return new Node<T>(root2Node.getValue(), root2Node.getLeft(), Option.of(add(root2Node.getRight(), t)));
                } else {
                    return new Node<T>(root2Node.getValue(), Option.of(add(root2Node.getLeft(), t)), root2Node.getRight());
                }
            } else {
                return root2Node;
            }
        }
    }

    public int depth () {
        return depth(root);
    }

    private int depth(Option<Node<T>> root2) {
        if (root2.isEmpty()) {
            return 0;
        } else {
            return 1 + Math.max(depth(root2.get().getLeft()), depth(root2.get().getRight()));
        }
    }

    public int count () {
        return count(root);
    }

    private int count(Option<Node<T>> root2) {
        if (root2.isEmpty()) {
            return 0;
        } else {
            return 1 + count(root2.get().getLeft()) + count(root2.get().getRight());
        }
    }

    public boolean exists(T t) {
        return exists(root, t);
    }

    private boolean exists (Option<Node<T>> root2, T t) {
        if (root2.isEmpty()) {
            return false;
        } else {
            Node<T> root2Node = root2.get();
            return root2Node.getValue().equals(t) 
                    || exists(t.compareTo(root2Node.getValue()) < 0 ? root2Node.getLeft() : root2Node.getRight(), t);
        }
    }

    //    public static void add (Node node) {
    //        if (nextNode.getValue() > node.getValue()) {
    //            if (Option.none().equals(nextNode.getLeft().getValue())) {
    //                nextNode = nextNode.getLeft();
    //            }
    //        } else if (nextNode.getValue() < node.getValue()) {
    //            if (Option.none().equals(nextNode.getRight().getValue())) {
    //                nextNode = nextNode.getRight();
    //            }
    //        }
    //        add(nextNode);
    //        nextNode = topNode;
    //    }

    public static void main(String[] args) {
        //        Node<String> ns1 = Node.newNode("abc");
        //        Node<String> ns2 = Node.newNode("abc");
        //        Node<String> ns3 = Node.newNode("def");
        //
        //        System.out.println("ns1.getValue().compareTo(ns2.getValue()) = " + ns1.getValue().compareTo(ns2.getValue()));
        //        System.out.println("ns1.getValue().compareTo(ns3.getValue()) = " + ns1.getValue().compareTo(ns3.getValue()));
        //        System.out.println("ns2.getValue().compareTo(ns3.getValue()) = " + ns2.getValue().compareTo(ns3.getValue()));
        //        System.out.println();
        //        System.out.println("ns1.compareTo(ns2) = " + ns1.compareTo(ns2));
        //        System.out.println("ns1.compareTo(ns3) = " + ns1.compareTo(ns3));
        //        System.out.println("ns2.compareTo(ns3) = " + ns2.compareTo(ns3));
        //        System.out.println();
        //
        //        Node<Integer> ni1 = Node.newNode(17);
        //        Node<Integer> ni2 = Node.newNode(17);
        //        Node<Integer> ni3 = Node.newNode(34);
        //
        //        System.out.println("ni1.getValue().compareTo(ni2.getValue()) = " + ni1.getValue().compareTo(ni2.getValue()));
        //        System.out.println("ni1.getValue().compareTo(ni3.getValue()) = " + ni1.getValue().compareTo(ni3.getValue()));
        //        System.out.println("ni2.getValue().compareTo(ni3.getValue()) = " + ni2.getValue().compareTo(ni3.getValue()));

        Node<Integer> n2 = new Node<Integer>(2, Option.none(), Option.none());
        Node<Integer> n10 =  new Node<Integer>(10, Option.none(), Option.none());
        Node<Integer> n17 =  new Node<Integer>(17, Option.of(n10), Option.none());
        Node<Integer> n3 = new Node<Integer>(3, Option.of(n2), Option.of(n17));
        Tree<Integer> tree = new Tree<>(Option.of(n3));

        System.out.println(tree.depth());
        System.out.println(tree.count());
        System.out.println(tree.add(12));
        System.out.println(tree.add(12).exists(12));
    }

}
package BinaryTree;

import io.vavr.control.Option;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
public class Tree <T extends Comparable<T>> {
    @NonFinal private Option<Node<T>> root = Option.none();

    public static void main(String[] args) {
        Node<String> ns1 = Node.newNode("abc");
        Node<String> ns2 = Node.newNode("abc");
        Node<String> ns3 = Node.newNode("def");

        System.out.println("ns1.getValue().compareTo(ns2.getValue()) = " + ns1.getValue().compareTo(ns2.getValue()));
        System.out.println("ns1.getValue().compareTo(ns3.getValue()) = " + ns1.getValue().compareTo(ns3.getValue()));
        System.out.println("ns2.getValue().compareTo(ns3.getValue()) = " + ns2.getValue().compareTo(ns3.getValue()));
        System.out.println();
        System.out.println("ns1.compareTo(ns2) = " + ns1.compareTo(ns2));
        System.out.println("ns1.compareTo(ns3) = " + ns1.compareTo(ns3));
        System.out.println("ns2.compareTo(ns3) = " + ns2.compareTo(ns3));
        System.out.println();

        Node<Integer> ni1 = Node.newNode(17);
        Node<Integer> ni2 = Node.newNode(17);
        Node<Integer> ni3 = Node.newNode(34);

        System.out.println("ni1.getValue().compareTo(ni2.getValue()) = " + ni1.getValue().compareTo(ni2.getValue()));
        System.out.println("ni1.getValue().compareTo(ni3.getValue()) = " + ni1.getValue().compareTo(ni3.getValue()));
        System.out.println("ni2.getValue().compareTo(ni3.getValue()) = " + ni2.getValue().compareTo(ni3.getValue()));

    }

}
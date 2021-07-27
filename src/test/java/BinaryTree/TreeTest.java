package BinaryTree;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.vavr.control.Option;

public class TreeTest {

    Node<Integer> n2 = new Node<Integer>(2, Option.none(), Option.none());
    Node<Integer> n10 =  new Node<Integer>(10, Option.none(), Option.none());
    Node<Integer> n17 =  new Node<Integer>(17, Option.of(n10), Option.none());
    Node<Integer> n3 = new Node<Integer>(3, Option.of(n2), Option.of(n17));
    Tree<Integer> tree = new Tree<>(Option.of(n3));

    @Test (dataProvider="addTestDP")
    public void addTest(int in, Tree<Integer> expected) {
        Tree<Integer> actual = tree.add(in);
        assertEquals(actual, expected);
    }

    @Test
    public void countTest() {
        assertEquals(tree.count(), 4);
    }

    @Test
    public void depthTest() {
        assertEquals(tree.depth(), 3);
    }

    @Test (dataProvider="existsTestDP")
    public void existsTest(int in, boolean expected) {
        assertEquals(tree.exists(in), expected);
    }

    @DataProvider
    Object[][] addTestDP() {
        return new Object[][] {
            {17, new Tree<>(Option.of(n3))},
            {3, new Tree<>(Option.of(n3))},

            {1, new Tree<>(Option.of(new Node<Integer>(3, Option.of(new Node<Integer>(2, Option.of(Node.newNode(1)), Option.none())), Option.of(n17))))},
            {83, new Tree<>(Option.of(new Node<Integer>(3, Option.of(n2), Option.of(new Node<Integer>(17, Option.of(n10), Option.some(Node.newNode(83)))))))},
        };
    } //Node(value=3, left=Some(Node(value=2, left=None, right=None)), right=Some(Node(value=17, left=Some(Node(value=10, left=None, right=None)), right=None)))] but found []
    //  Tree(root=Some(Node(value=3, left=Some(Node(value=2, left=None, right=None)), right=Some(Node(value=17, left=Some(Node(value=10, left=None, right=None)), right=None)))))


    @DataProvider
    Object[][] existsTestDP() {
        return new Object[][] {
            {17, true},
            {1, false},
            {83, false},
            {3, true}
        };
    }
}

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
    Tree<Integer> bigTree = new Tree<>(Option.of(n3));


    Node<Integer> n4 = new Node<Integer>(4, Option.none(), Option.none());
    Tree<Integer> smallTree = new Tree<>(Option.of(n4));

    Tree<Integer> emptyTree = new Tree<>(Option.none());

    @Test (dataProvider="addTestDP")
    public void addTest(Tree<Integer> inTree, int in, Tree<Integer> expected) {
        Tree<Integer> actual = inTree.add(in);
        assertEquals(actual, expected);
    }

    @Test(dataProvider="countTestDP")
    public void countTest(Tree<Integer> inTree, int expected) {
        assertEquals(inTree.count(), expected);
    }

    @Test (dataProvider="depthTestDP")
    public void depthTest(Tree<Integer> inTree, int expected) {
        assertEquals(inTree.depth(), expected);
    }

    @Test (dataProvider="existsTestDP")
    public void existsTest(Tree<Integer> inTree, int in, boolean expected) {
        assertEquals(inTree.exists(in), expected);
    }

    @DataProvider
    Object[][] addTestDP() {
        return new Object[][] {
            {bigTree, 17, bigTree},
            {bigTree, 3, bigTree},

            {bigTree, 1, new Tree<>(Option.of(new Node<Integer>(3, Option.of(new Node<Integer>(2, Option.of(Node.newNode(1)), Option.none())), Option.of(n17))))},
            {bigTree, 83, new Tree<>(Option.of(new Node<Integer>(3, Option.of(n2), Option.of(new Node<Integer>(17, Option.of(n10), Option.some(Node.newNode(83)))))))},

            {smallTree, 4, smallTree},

            {smallTree, 2, new Tree<>(Option.of(new Node<Integer>(4, Option.of(Node.newNode(2)), Option.none())))},
            {smallTree, 19, new Tree<>(Option.of(new Node<Integer>(4, Option.none(), Option.some(Node.newNode(19)))))},

            {emptyTree, 185, new Tree<>(Option.of(Node.newNode(185)))},
        };
    } 

    @DataProvider
    Object[][] countTestDP() {
        return new Object[][] {
            {bigTree, 4},
            {smallTree, 1},
            {emptyTree, 0},
        };
    }

    @DataProvider
    Object[][] depthTestDP() {
        return new Object[][] {
            {bigTree, 3},
            {smallTree, 1},
            {emptyTree, 0},
        };
    }

    @DataProvider
    Object[][] existsTestDP() {
        return new Object[][] {
            {bigTree, 17, true},
            {bigTree, 1, false},
            {bigTree, 83, false},
            {bigTree, 3, true},

            {smallTree, 3, false},
            {smallTree, 4, true},
            {smallTree, 9125, false},

            {emptyTree, 3, false},
            {emptyTree, 12, false},
            {emptyTree, 9, false},
        };
    }
}

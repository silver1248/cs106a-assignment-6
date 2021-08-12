package BinaryTree;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.vavr.collection.List;
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

    Node<Integer> n20 = new Node<Integer>(20, Option.none(), Option.none());
    Node<Integer> n72 = new Node<Integer>(72, Option.none(), Option.none());
    Node<Integer> n85 = new Node<Integer>(85, Option.none(), Option.none());
    Node<Integer> n21 = new Node<Integer>(21, Option.of(n20), Option.none());
    Node<Integer> n84 = new Node<Integer>(84, Option.of(n72), Option.of(n85));
    Node<Integer> n23 = new Node<Integer>(23, Option.of(n21), Option.of(n84));
    Node<Integer> n19 = new Node<Integer>(19, Option.of(n17), Option.of(n23));
    Tree<Integer> complexTree = new Tree<Integer>(Option.of(n19));

    @Test (dataProvider="addTestDP")
    public void addTest(Tree<Integer> inTree, int in, Tree<Integer> expected) {
        Tree<Integer> actual = inTree.add(in);
        assertEquals(actual, expected);
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

    @Test (dataProvider="medianTestDP")
    public void medianTest(Tree<Integer> inTree, Option<Node<Integer>> expected) {
        Option<Node<Integer>> actual = inTree.median();
        assertEquals(actual, expected);
    }

    @DataProvider
    Object[][] medianTestDP() {
        return new Object[][] {
            {bigTree, Option.of(n3)},
            {smallTree, Option.of(n4)},
            {emptyTree, Option.none()},
            {complexTree, Option.of(n21)},
        };
    }

    public boolean balanced(Option<Node<Integer>> in) {
        if (in.isEmpty()) {
            return true;
        } else {
            Option<Node<Integer>> left = in.get().getLeft();
            Option<Node<Integer>> right = in.get().getRight();
            int ncLeft = Node.count(left);
            int ncRight = Node.count(right);
            if (ncLeft - ncRight <= 1 && ncLeft- ncRight >= -1) {
                return balanced(left) && balanced(right);
            } else {
                return false;
            }
        }
    }


    public boolean balanced(Tree<Integer> in) {
        return balanced(in.getRoot());
    }

    @Test (dataProvider="rebalanceTestDP")
    public void rebalanceTest(Tree<Integer> inTree, Tree<Integer> expected) {
        Tree<Integer> actual = inTree.rebalance();
        assertTrue(balanced(actual));
    }

    @DataProvider
    Object[][] rebalanceTestDP() {
        return new Object[][] {
            {bigTree, bigTree},
            {smallTree, smallTree},
            {emptyTree, emptyTree},
            {complexTree, emptyTree.add(21).add(20).add(23).add(17).add(84).
                add(10).add(19).add(72).add(85)},
        };
    }

    @Test (dataProvider="toListTestDP")
    public void toListTest(Tree<Integer> inTree, List<Integer> expected) {
        List<Integer> actual = inTree.toList();
        assertEquals(actual, expected);
    }

    @DataProvider
    Object[][] toListTestDP() {
        return new Object[][] {
            {bigTree, List.of(2, 3, 10, 17)},
            {complexTree, List.of(10, 17, 19, 20, 21, 23, 72, 84, 85)},
        };
    }

    @Test(dataProvider="countTestDP")
    public void countTest(Tree<Integer> inTree, int expected) {
        assertEquals(inTree.count(), expected);
    }

    @DataProvider
    Object[][] countTestDP() {
        return new Object[][] {
            {bigTree, 4},
            {smallTree, 1},
            {emptyTree, 0},
        };
    }

    @Test (dataProvider="depthTestDP")
    public void depthTest(Tree<Integer> inTree, int expected) {
        assertEquals(inTree.depth(), expected);
    }

    @DataProvider
    Object[][] depthTestDP() {
        return new Object[][] {
            {bigTree, 3},
            {smallTree, 1},
            {emptyTree, 0},
        };
    }

    @Test (dataProvider="existsTestDP")
    public void existsTest(Tree<Integer> inTree, int in, boolean expected) {
        assertEquals(inTree.exists(in), expected);
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

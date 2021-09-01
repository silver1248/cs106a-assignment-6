package BinaryTree;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.function.Function;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.vavr.Function1;
import io.vavr.collection.List;
import io.vavr.control.Option;

public class TreeTest {

    Node<Integer> n2 = new Node<Integer>(2, Option.none(), Option.none());
    Node<Integer> n10 =  new Node<Integer>(10, Option.none(), Option.none());
    Node<Integer> n17 =  new Node<Integer>(17, Option.of(n10), Option.none());
    Node<Integer> n3 = new Node<Integer>(3, Option.of(n2), Option.of(n17));
    Tree<Integer> bigTree = new Tree<Integer>(Option.of(n3));


    Node<Integer> n4 = new Node<Integer>(4, Option.none(), Option.none());
    Tree<Integer> smallTree = new Tree<Integer>(Option.of(n4));

    Tree<Integer> emptyTree = new Tree<Integer>(Option.none());

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

    @Test (dataProvider="getPreviousTestDP")
    public void getPreviousTest(Tree<Integer> inTree, int in, Option<List<Integer>> expected) {
        assertEquals(inTree.getPrevious(in), expected);
    }

    @DataProvider
    Object[][] getPreviousTestDP() {
        return new Object[][] {
            {emptyTree, 3, Option.none()},                          //this tree has no elements
            {smallTree, 3, Option.none()},                          //this tree doesn't have the element

            {bigTree, 17, Option.of(List.of(3, 17))},               //this node has 1 child
            {complexTree, 20, Option.of(List.of(19, 23, 21, 20))},  //this node is a leaf
            {smallTree, 4, Option.of(List.of(4))},                  //this node is a root w/o children
            {complexTree, 19, Option.of(List.of(19))},              //this node is a root w/ children
        };
    }

    @Test (dataProvider="lastCommonAncestorTestDP")
    public void lastCommonAncestorTest(Tree<Integer> inTree, int in1, int in2, Option<Integer> expected) {
        assertEquals(inTree.lastCommonAncestor(in1, in2), expected);
    }

    @DataProvider
    Object[][] lastCommonAncestorTestDP() {
        return new Object[][] {
            {emptyTree, 3, 5, Option.none()},                      //this tree has no elements

            {smallTree, 4, 4, Option.of(4)},                       //this tree has only a 4 as the root
            {bigTree, 10, 2, Option.of(3)},                        //the second node is the root
            {complexTree, 85, 72, Option.of(84)},                  //these nodes are both directly below 21
            {complexTree, 21, 72, Option.of(23)},                  /*one of these is directly below 19 and 
                                                                    the other one is as far as it can get while
                                                                    still connecting to it*/
        };
    }

    @Test (dataProvider="mapTestDP")
    public void mapTest(Tree<Integer> inTree
            , Function <Integer, String> mapper
            , Tree<String> expected) {
        assertEquals(inTree.map(mapper), expected);
    }

    @DataProvider
    Object[][] mapTestDP() {
        Function1<Integer, String> mapper = x -> x.toString();       //yes having to do this is annoying no it doesn't work like it should yes i tried
        Node<String> ns2 = new Node<String>("2", Option.none(), Option.none());
        Node<String> ns10 =  new Node<String>("10", Option.none(), Option.none());
        Node<String> ns17 =  new Node<String>("17", Option.of(ns10), Option.none());
        Node<String> ns3 = new Node<String>("3", Option.of(ns2), Option.of(ns17));

        Node<String> ns4 = new Node<String>("4", Option.none(), Option.none());
        return new Object[][] {
            {emptyTree, mapper, new Tree<String>(Option.none())},                      //this tree has no elements

            {smallTree, mapper, new Tree<String>(Option.of(ns4))},                     //this tree has only a 4 as the root
            {bigTree, mapper, new Tree<String>(Option.of(ns3))},                       //this tree has only 4 elements
        };
    }
}
package DataStructure;

import DataStructure.Tree.AvlTree;
import DataStructure.Tree.BTree;
import DataStructure.Tree.Tree;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class TreeTest {

    @Test
    public void testTree() {
        Tree<Integer> tree = new Tree<Integer>(Comparator.<Integer>naturalOrder());
        tree.insert(4);
        tree.insert(6);
        tree.insert(2);
        tree.insert(5);
        tree.insert(3);
        tree.insert(1);
        tree.insert(7);
        assertNotNull(tree.search(3));
        assertNull(tree.search(8));
    }

    @Test
    public void testTree2() {
        AvlTree<Integer> tree = new AvlTree<>(Comparator.<Integer>naturalOrder());
        for (int i = 1; i <= 31; i++){
            tree.insert(i);
        }
        for (int i = 1; i < 32; i++){
            assertNotNull(tree.search(i));
        }
        assertNull(tree.search(32));
    }

    @Test
    public void testTree3() {
        BTree<Integer> tree = new BTree<>(1, Comparator.<Integer>naturalOrder());
        for (int i = 1; i <= 31; i++){
            tree.insert(i);
        }
        for (int i = 1; i < 32; i++){
            assertNotNull(tree.search(i));
        }
        assertNull(tree.search(32));
    }

}

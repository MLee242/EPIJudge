package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.LinkedList;
import java.util.List;
public class TreeWithParentInorder {
  @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")

  public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {

    List<Integer> ans = new LinkedList<>();
    tree = min(tree);
    while(tree != null){
      ans.add(tree.data);
      tree = findSuccessor(tree);
    }
    return ans;
  }

  private static BinaryTree<Integer> min(BinaryTree<Integer> tree){
    if(tree == null) return null;
    while(tree.left != null){
      tree = tree.left;
    }
    return tree;
  }

  private static BinaryTree<Integer> findSuccessor(BinaryTree<Integer> tree){
    if(tree == null) return null;

    BinaryTree<Integer> right = min(tree.right);
    if(right != null) return right;

    BinaryTree<Integer> iter = tree;
    while(iter.parent != null && iter.parent.right == iter){
      iter = iter.parent;
    }
    return iter.parent;
  }


  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeWithParentInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

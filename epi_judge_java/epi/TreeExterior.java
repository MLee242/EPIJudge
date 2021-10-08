package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class TreeExterior {



  public static List<BinaryTreeNode<Integer>>
  exteriorBinaryTree(BinaryTreeNode<Integer> tree) {
    List<BinaryTreeNode<Integer>> ans = new LinkedList<>();
    if(tree == null){
      return ans;
    }
    ans.add(tree);
    ans.addAll(leftBoundaryAndLeaves(tree.left, true));
    ans.addAll(rightBoundary(tree.right, true));

    return ans;
  }

  private static List<BinaryTreeNode<Integer>> leftBoundaryAndLeaves(BinaryTreeNode<Integer> tree, boolean boundary) {
    if(tree == null) return new LinkedList<>();

    List<BinaryTreeNode<Integer>> result = new LinkedList<>();

    if (boundary || (tree.left == null && tree.right == null)) {
      result.add(tree);
    }
    result.addAll(leftBoundaryAndLeaves(tree.left, boundary));
    result.addAll(leftBoundaryAndLeaves(tree.right, boundary && tree.left == null));
    return result;
  }


  private static List<BinaryTreeNode<Integer>> rightBoundary(BinaryTreeNode<Integer> tree, boolean boundary){
    if(tree == null) return new LinkedList<>();
    List<BinaryTreeNode<Integer>> result = new LinkedList<>();

    result.addAll(rightBoundary(tree.left, tree.right == null && boundary));
    result.addAll(rightBoundary(tree.right,boundary));
    if (boundary || (tree.left == null && tree.right == null)) {
      result.add(tree);
    }
    return result;
  }






  private static List<Integer> createOutputList(List<BinaryTreeNode<Integer>> L)
      throws TestFailure {
    if (L.contains(null)) {
      throw new TestFailure("Resulting list contains null");
    }
    List<Integer> output = new ArrayList<>();
    for (BinaryTreeNode<Integer> l : L) {
      output.add(l.data);
    }
    return output;
  }

  @EpiTest(testDataFile = "tree_exterior.tsv")
  public static List<Integer>
  exteriorBinaryTreeWrapper(TimedExecutor executor,
                            BinaryTreeNode<Integer> tree) throws Exception {
    List<BinaryTreeNode<Integer>> result =
        executor.run(() -> exteriorBinaryTree(tree));

    return createOutputList(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeExterior.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeFromPreorderInorder {
  @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")

  public static BinaryTreeNode<Integer>
  binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {
    map = new HashMap<>();
    index = 0;
    for(int i = 0 ; i < inorder.size(); i++){
      map.put(inorder.get(i), i);
    }
    return buildTree(preorder, 0, preorder.size() - 1, inorder);
  }

  static Map<Integer, Integer> map;
  static int index;
  private static BinaryTreeNode<Integer> buildTree(List<Integer> preorder, int lo, int hi, List<Integer> inorder){
    if(lo > hi) return null;
    if(index == preorder.size()) return null;

    int pos = map.get(preorder.get(index));
    BinaryTreeNode<Integer> root = new BinaryTreeNode<>(preorder.get(index++));
    root.left = buildTree(preorder, lo, pos - 1, inorder);
    root.right = buildTree(preorder, pos + 1, hi, inorder);
    return root;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;
public class TreeFromPreorderWithNull {
  public static BinaryTreeNode<Integer>
  reconstructPreorder(List<Integer> preorder) {
    index = 0;
    return buildTree(preorder);
  }

  static int index = 0;

  private static BinaryTreeNode<Integer> buildTree(List<Integer> preorder){

    if(index == preorder.size()) return null;


    if(preorder.get(index) == null){
      index++;
      return null;
    }else{
      BinaryTreeNode<Integer> root = new BinaryTreeNode<>(preorder.get(index));
      index++;
      root.left = buildTree(preorder);
      root.right = buildTree(preorder);
      return root;
    }

  }





  @EpiTest(testDataFile = "tree_from_preorder_with_null.tsv")
  public static BinaryTreeNode<Integer>
  reconstructPreorderWrapper(TimedExecutor executor, List<String> strings)
      throws Exception {
    List<Integer> ints = new ArrayList<>();
    for (String s : strings) {
      if (s.equals("null")) {
        ints.add(null);
      } else {
        ints.add(Integer.parseInt(s));
      }
    }

    return executor.run(() -> reconstructPreorder(ints));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderWithNull.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

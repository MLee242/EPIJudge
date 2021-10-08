package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {


  private static class BinaryTreeInfo{
      boolean balanced;
      int height;
      public BinaryTreeInfo(boolean balanced, int height){
        this.balanced = balanced;
        this.height = height;
      }
  }

  @EpiTest(testDataFile = "is_tree_balanced.tsv")
  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    return helper(tree).balanced;
  }

  private static BinaryTreeInfo helper(BinaryTreeNode<Integer> tree) {
    if(tree == null) return new BinaryTreeInfo(true, 0);

    BinaryTreeInfo left = helper(tree.left);
    BinaryTreeInfo right = helper(tree.right);

    boolean isBalanced = Math.abs(left.height - right.height) <= 1 &&
      left.balanced && right.balanced;

    return new BinaryTreeInfo( isBalanced,1 + Math.max(left.height,right.height));

  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

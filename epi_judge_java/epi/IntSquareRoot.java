package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntSquareRoot {
  @EpiTest(testDataFile = "int_square_root.tsv")

  public static int squareRoot(int k) {
    int lo = 1;
    int hi = k;


    while(lo < hi){

      int mid = lo + (hi - lo) / 2;

      int remain = k / mid;



      if(mid > k / mid || (mid == k / mid && k % mid == 0)){
        hi = mid;
      }else{
        lo = mid + 1;
      }
    }

    return lo * lo == k ? lo : lo - 1;

  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class RealSquareRoot {
  @EpiTest(testDataFile = "real_square_root.tsv")

  public static double squareRoot(double x) {

    double lo = 1.0;
    double hi = x;
    if(x < 1.0){
      lo = x;
      hi = 1;
    }

    while(compare(lo, hi) == Ordering.SMALLER){

      double mid = lo + 0.5 * (hi - lo);
      double midSquared = mid * mid;
      if(compare(midSquared, x) == Ordering.EQUAL){
        return mid;
      }else if(compare(midSquared, x) == Ordering.SMALLER){
        lo = mid;
      }else {
        hi = mid;
      }
    }
    return lo;




  }

  private static enum Ordering {SMALLER, EQUAL, LARGER};

  private static Ordering compare(double a, double b){
    double EPSILON = 0.0000001;
    double diff = (a - b) / b;
    if(diff < -EPSILON) return Ordering.SMALLER;
    if(diff > EPSILON) return Ordering.LARGER;
    return Ordering.EQUAL;

  }

  public static void main(String[] args) {

    System.exit(
        GenericTest
            .runFromAnnotations(args, "RealSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());


  }
}

package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveDivide {
  @EpiTest(testDataFile = "primitive_divide.tsv")
  public static int divide(int x, int y) {


    int power = 32;
    long yPower = (long) y << (power);
    long result = 0;

    while(x >= y){

      while(yPower > x){
        yPower >>>= 1;
        power--;
      }

      System.out.println(1L << power);
      result = result + (1L << power);
      x -= yPower;
    }

    return (int)result;




  }

  public static void main(String[] args) {
    System.out.println(divide(64, 3));
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveDivide.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

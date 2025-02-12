package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseDigits {
  @EpiTest(testDataFile = "reverse_digits.tsv")
  public static long reverse(int x) {
    long result = 0L;

    while(x != 0){
      result = result * 10 + x % 10;
      x = x / 10;
    }
    return result;

  }

  public static void main(String[] args) {
    System.out.println(reverse(1799113645));
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseDigits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

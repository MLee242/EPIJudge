package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsNumberPalindromic {
  @EpiTest(testDataFile = "is_number_palindromic.tsv")
  public static boolean isPalindromeNumber(int x) {

    if(x < 0) return false;
    if(x < 10) return true;
    if(x % 10 == 0) return false;

    long result = 0;
    while(x > result){
      result = result * 10 + x % 10;
      x = x / 10;
    }
    if(x == result || result / 10 == x) return true;
    return false;
  }

  public static void main(String[] args) {
    System.out.println(isPalindromeNumber(413));
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsNumberPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class SearchFirstKey {
  @EpiTest(testDataFile = "search_first_key.tsv")

  public static int searchFirstOfK(List<Integer> A, int k) {

    if(A.size() == 0) return - 1;

    int lo = 0, hi = A.size() - 1;
    while(lo < hi){
      int mid = lo + (hi - lo) / 2;
      if(A.get(mid) >= k){
        hi = mid;
      }else{
        lo = mid + 1;
      }
    }

    return A.get(lo) == k ? lo : -1;


  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

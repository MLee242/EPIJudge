package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class SortIncreasingDecreasingArray {
  @EpiTest(testDataFile = "sort_increasing_decreasing_array.tsv")

  public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> A) {
    if(A.isEmpty() || A.size() == 1) return A;
    List<Integer> ans = new LinkedList<>();
    PriorityQueue<int[]> pq = new PriorityQueue<>( (a,b) -> {
      return Integer.compare(A.get(a[0]), A.get(b[0]));
    });
    int start = 0;
    int end = 1;
    while(end < A.size()){
      int prev = Integer.compare(A.get(end), A.get(end - 1));
      end++;
      while(end < A.size() && Integer.compare(A.get(end), A.get(end - 1)) == prev){
        end++;
      }

      if(prev >= 0){
        if(prev == 0) prev = 1;
        pq.add(new int[]{start, end - 1, prev});
      }else{
        pq.add(new int[]{end -1, start, prev});
      }
      start = end;
    }
    while(!pq.isEmpty()){
      int[] curr = pq.poll();
      ans.add(A.get(curr[0]));
      if(curr[2] > 0 && curr[0] + curr[2] <= curr[1]){
        pq.add(new int[]{curr[0] + curr[2], curr[1], curr[2]});
      }else if(curr[2] < 0 && curr[0] + curr[2] >= curr[1]){
        pq.add(new int[]{curr[0] + curr[2], curr[1], curr[2]});
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortIncreasingDecreasingArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

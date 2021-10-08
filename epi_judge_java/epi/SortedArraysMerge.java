package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class SortedArraysMerge {
  @EpiTest(testDataFile = "sorted_arrays_merge.tsv")

  public static List<Integer>
  mergeSortedArrays(List<List<Integer>> sortedArrays) {
    List<Integer> result = new LinkedList<>();

    PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>( (a,b) -> {
      return sortedArrays.get(a.key).get(a.val) - sortedArrays.get(b.key).get(b.val);
    });

    for(int i = 0 ; i < sortedArrays.size(); i++){
      List<Integer> t = sortedArrays.get(i);
      if(t.size() > 0){
        pq.add(new Pair<>(i, 0));
      }
    }

    while(!pq.isEmpty()){
      Pair<Integer, Integer> curr = pq.poll();
      int index = curr.key;
      int val = curr.val;
      result.add(sortedArrays.get(index).get(val));

      if(sortedArrays.get(index).size() - 1 != val){
        pq.add(new Pair<>(index, val + 1));
      }
    }
    return result;

    //(N * log (k - NUMBER OF FILES))


  }

  private static class Pair <K,V> {
    K key;
    V val;
    public Pair(K key, V val){
      this.key = key;
      this.val = val;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

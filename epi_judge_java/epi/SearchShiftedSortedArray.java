package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchShiftedSortedArray {
    @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")

    public static int searchSmallest(List<Integer> A) {

        int pos = 0;
        int lo = 0;
        int hi = A.size() - 1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if(A.get(mid) < A.get(hi)){
                hi = mid;
            }else{
                lo = mid + 1;
            }
        }


        return lo;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchShiftedSortedArray.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}

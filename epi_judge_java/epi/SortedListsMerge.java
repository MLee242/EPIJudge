package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                      ListNode<Integer> L2) {
    if(L1 == null && L2 == null) return null;

    if(L1 == null) return L2;

    ListNode<Integer> dummyNode = new ListNode<>(0, null);
    ListNode<Integer> tail = dummyNode;

    while(L1 != null || L2 != null){ // O(M + N)
      if(L2 == null){
        tail.next = new ListNode<>(L1.data, null);
        L1 = L1.next;
      }else if(L1 == null){
        tail.next = new ListNode<>(L2.data, null);
        L2 = L2.next;
      }else{
        if(L1.data <= L2.data){
          tail.next = new ListNode<>(L1.data, null);
          L1 = L1.next;
        }else{
          tail.next = new ListNode<>(L2.data, null);
          L2 = L2.next;
        }
      }
      tail = tail.next;
    }
    return dummyNode.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

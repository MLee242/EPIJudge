package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class RemoveDuplicatesFromSortedList {
  @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")

  public static ListNode<Integer> removeDuplicates(ListNode<Integer> L) {

    if(L == null) return null;
    ListNode<Integer> dummyHead = new ListNode<>(null, L);
    ListNode<Integer> prev = dummyHead;

    ListNode curr = L;

    while(curr != null){
      if(prev.data == curr.data){
        prev.next = curr.next;
      }else{
        prev = curr;
      }
      curr = curr.next;
    }

    return dummyHead.next;

  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RemoveDuplicatesFromSortedList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

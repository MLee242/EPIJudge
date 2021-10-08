package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class EvenOddListMerge {
  @EpiTest(testDataFile = "even_odd_list_merge.tsv")

  public static ListNode<Integer> evenOddMerge(ListNode<Integer> L) {
    if(L == null) return null;
    ListNode<Integer> evenHead = new ListNode<>(0, null);
    ListNode<Integer> evenTail = evenHead;

    ListNode<Integer> oddHead = new ListNode<>(0, null);
    ListNode<Integer> oddTail = oddHead;

    ListNode<Integer> curr = L;

    boolean even = true;
    while(curr != null){
      if(even){
        evenTail.next = curr;
        evenTail = evenTail.next;
      }else{
        oddTail.next = curr;
        oddTail = oddTail.next;
      }
      even = !even;
      curr = curr.next;
    }

    evenTail.next = oddHead.next;
    oddTail.next = null;

    return evenHead.next;

  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvenOddListMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

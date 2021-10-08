package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ListCyclicRightShift {
  @EpiTest(testDataFile = "list_cyclic_right_shift.tsv")

  public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> L,
                                                           int k) {
    if(L == null) return null;
    ListNode<Integer> curr = L;
    int len = 0;
    while(curr != null){
      len++;
      curr = curr.next;
    }

    k = k % len;

    if(k == 0) return L;

    curr = L;

    for(int i = 1; i <= k ; i++){
      curr = curr.next;
    }

    ListNode<Integer> tail = L;

    while(curr.next != null){
      tail = tail.next;
      curr = curr.next;
    }

    ListNode<Integer> newHead = tail.next;
    tail.next = null;
    curr.next = L;
    return newHead;



  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ListCyclicRightShift.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class DeleteKthLastFromList {
  @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")

  // Assumes L has at least k nodes, deletes the k-th last node in L.
  public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {

    if(L == null) return null;
    ListNode<Integer> dummyHead = new ListNode<>(0, L);
    ListNode<Integer> curr = dummyHead;

    for(int i = 1; i <= k ; i++){
      curr = curr.next;
    }
    ListNode<Integer> slow = dummyHead;
    while(curr.next != null){
      slow = slow.next;
      curr = curr.next;
    }

    slow.next = slow.next.next;

    return dummyHead.next;

  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DeleteKthLastFromList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

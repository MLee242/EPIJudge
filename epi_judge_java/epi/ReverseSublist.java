package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {

    if(L == null) return null;
    ListNode<Integer> dummyhead = new ListNode<>(0, L);
    ListNode<Integer> slow = dummyhead, fast = dummyhead;

    for(int i = 1; i <= finish; i++){
      if(i <= start - 1){
        slow = slow.next;
      }
      fast = fast.next;
    }

    ListNode nextHead = slow.next;
    slow.next = null;

    ListNode thirdHead = fast.next;
    fast.next = null;

    //reverse sublist:
    ListNode curr = nextHead.next;
    nextHead.next = thirdHead;

    while(curr != null){
      ListNode temp = curr.next;
      curr.next = nextHead;
      nextHead = curr;
      curr = temp;
    }

    slow.next = nextHead;

    return dummyhead.next;







  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

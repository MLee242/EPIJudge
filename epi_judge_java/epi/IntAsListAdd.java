package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntAsListAdd {
  @EpiTest(testDataFile = "int_as_list_add.tsv")

  public static ListNode<Integer> addTwoNumbers(ListNode<Integer> L1,
                                                ListNode<Integer> L2) {
    int carry = 0;
    ListNode<Integer> dummyHead = new ListNode<>(0, null);
    ListNode<Integer> tail = dummyHead;

    while(L1 != null || L2 != null || carry != 0){

      int result = carry;

      if(L1 != null){
        result += L1.data;
        L1 = L1.next;
      }
      if(L2 != null){
        result += L2.data;
        L2 = L2.next;
      }

      tail.next = new ListNode<>(result % 10, null);
      tail = tail.next;
      carry = result / 10;
    }

    return dummyHead.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsListAdd.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class DoTerminatedListsOverlap {

  public static ListNode<Integer>
  overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {

    if(l0 == null || l1 == null) return null;

    ListNode<Integer> currA = l0;
    ListNode<Integer> currB = l1;

    while(currA != currB){

      currA = currA.next;
      currB = currB.next;

      if(currA == null && currB == null) break;

      if(currA == null) currA = l1;
      if(currB == null) currB = l0;
    }
    return currA;
  }

  // a1 -> a2 -> a3 -> c1 -> c2 -> c3 -> b1 -> b2 -> c1 -> c2 -> c3
  // b1 -> b2 -> c1 -> c2 -> c3 -> a1 -> a2 -> a3 -> c1 -> c2 -> c3
  @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
  public static void
  overlappingNoCycleListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                                 ListNode<Integer> l1, ListNode<Integer> common)
      throws Exception {
    if (common != null) {
      if (l0 != null) {
        ListNode<Integer> i = l0;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l0 = common;
      }

      if (l1 != null) {
        ListNode<Integer> i = l1;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l1 = common;
      }
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingNoCycleLists(finalL0, finalL1));

    if (result != common) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoTerminatedListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

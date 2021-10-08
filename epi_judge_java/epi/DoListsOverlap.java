package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DoListsOverlap {

    public static ListNode<Integer> overlappingLists(ListNode<Integer> l0,
                                                     ListNode<Integer> l1) {
        System.out.println("");
        ListNode<Integer> currA = hasCycle(l0);
        ListNode<Integer> currB = hasCycle(l1);


        if (currA == null && currB == null) {
            currA = l0;
            currB = l1;

            if (currA == null || currB == null) return null;
            while (currA != currB) {
                currA = currA.next;
                currB = currB.next;
                if (currA == currB) break;
                if (currA == null) currA = l1;
                if (currB == null) currB = l0;
            }
        }else if(currA != null && currB != null && currA != currB){
            ListNode curr = currA.next;
            while(curr != currA && curr != currB){
                curr = curr.next;
            }
            currA = curr;
        }

        return currA == currB ? currA : null;
    }

    private static ListNode<Integer> hasCycle(ListNode<Integer> curr) {


        if (curr == null || curr.next == null) return null;

        ListNode<Integer> fast = curr;
        ListNode<Integer> slow = curr;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }

        if (fast != slow) return null;

        slow = curr;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;


    }

    @EpiTest(testDataFile = "do_lists_overlap.tsv")
    public static void
    overlappingListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                            ListNode<Integer> l1, ListNode<Integer> common,
                            int cycle0, int cycle1) throws Exception {
        if (common != null) {
            if (l0 == null) {
                l0 = common;
            } else {
                ListNode<Integer> it = l0;
                while (it.next != null) {
                    it = it.next;
                }
                it.next = common;
            }

            if (l1 == null) {
                l1 = common;
            } else {
                ListNode<Integer> it = l1;
                while (it.next != null) {
                    it = it.next;
                }
                it.next = common;
            }
        }

        if (cycle0 != -1 && l0 != null) {
            ListNode<Integer> last = l0;
            while (last.next != null) {
                last = last.next;
            }
            ListNode<Integer> it = l0;
            while (cycle0-- > 0) {
                if (it == null) {
                    throw new RuntimeException("Invalid input data");
                }
                it = it.next;
            }
            last.next = it;
        }

        if (cycle1 != -1 && l1 != null) {
            ListNode<Integer> last = l1;
            while (last.next != null) {
                last = last.next;
            }
            ListNode<Integer> it = l1;
            while (cycle1-- > 0) {
                if (it == null) {
                    throw new RuntimeException("Invalid input data");
                }
                it = it.next;
            }
            last.next = it;
        }

        Set<Integer> commonNodes = new HashSet<>();
        ListNode<Integer> it = common;
        while (it != null && !commonNodes.contains(it.data)) {
            commonNodes.add(it.data);
            it = it.next;
        }

        final ListNode<Integer> finalL0 = l0;
        final ListNode<Integer> finalL1 = l1;


        ListNode<Integer> result =
                executor.run(() -> overlappingLists(finalL0, finalL1));



        if (!((commonNodes.isEmpty() && result == null) ||
                (result != null && commonNodes.contains(result.data)))) {
            throw new TestFailure("Invalid result");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DoListsOverlap.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}

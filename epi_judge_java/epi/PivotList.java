package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PivotList {

    public static ListNode<Integer> listPivoting(ListNode<Integer> L, int x) {

        if (L == null) return null;

        ListNode<Integer> lessHead = new ListNode(0, null);
        ListNode<Integer> equalHead = new ListNode(0, null);
        ListNode<Integer> largerHead = new ListNode(0, null);

        ListNode<Integer> lessTail = lessHead;
        ListNode<Integer> equalTail = equalHead;
        ListNode<Integer> largerTail = largerHead;

        ListNode<Integer> curr = L;

        while (curr != null) {
            if (curr.data < x) {
                lessTail.next = curr;
                lessTail = lessTail.next;
            } else if (curr.data == x) {
                equalTail.next = curr;
                equalTail = equalTail.next;
            } else {
                largerTail.next = curr;
                largerTail = largerTail.next;
            }
            curr = curr.next;
        }
        largerTail.next = null;
        equalTail.next = largerHead.next;
        lessTail.next = equalHead.next;
        return lessHead.next;


    }

    public static List<Integer> linkedToList(ListNode<Integer> l) {
        List<Integer> v = new ArrayList<>();
        while (l != null) {
            v.add(l.data);
            l = l.next;
        }
        return v;
    }

    @EpiTest(testDataFile = "pivot_list.tsv")
    public static void listPivotingWrapper(TimedExecutor executor,
                                           ListNode<Integer> l, int x)
            throws Exception {
        List<Integer> original = linkedToList(l);

        final ListNode<Integer> finalL = l;
        l = executor.run(() -> listPivoting(finalL, x));

        List<Integer> pivoted = linkedToList(l);

        int mode = -1;
        for (Integer i : pivoted) {
            switch (mode) {
                case -1:
                    if (i == x) {
                        mode = 0;
                    } else if (i > x) {
                        mode = 1;
                    }
                    break;
                case 0:
                    if (i < x) {
                        throw new TestFailure("List is not pivoted");
                    } else if (i > x) {
                        mode = 1;
                    }
                    break;
                case 1:
                    if (i <= x) {
                        throw new TestFailure("List is not pivoted");
                    }
            }
        }

        Collections.sort(original);
        Collections.sort(pivoted);
        if (!original.equals(pivoted))
            throw new TestFailure("Result list contains different values");
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PivotList.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}

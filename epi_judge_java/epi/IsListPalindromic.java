package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsListPalindromic {
    @EpiTest(testDataFile = "is_list_palindromic.tsv")

    public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {

        if (L == null || L.next == null) return true;

        ListNode<Integer> fast = L;
        ListNode<Integer> slow = L;

        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode<Integer> newNode = slow.next;
        slow.next = null;


        ListNode<Integer> curr = newNode.next;
        newNode.next = null;

        while(curr != null){
            ListNode<Integer> temp = curr.next;
            curr.next = newNode;
            newNode = curr;
            curr = temp;
        }

        curr = L;
        while(newNode != null){
            if(Integer.compare(curr.data, newNode.data) != 0){
                return false;
            }
            curr = curr.next;
            newNode = newNode.next;
        }

        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsListPalindromic.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}

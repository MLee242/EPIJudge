package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;

public class IsValidParenthesization {
  @EpiTest(testDataFile = "is_valid_parenthesization.tsv")

  public static boolean isWellFormed(String s) {
    Deque<Character> dq = new LinkedList<>();

    for(int i = 0 ; i < s.length(); i++){
      char c = s.charAt(i);
      if(c == ')' || c == ']' || c == '}'){
        if(!dq.isEmpty()){
          char v = dq.pop();
          if(c == ')' && v != '(') return false;
          if(c == ']' && v != '[') return false;
          if(c == '}' && v != '{') return false;
        }else{
          return false;
        }
      }else{
        dq.push(c);
      }
    }
    return dq.isEmpty();

  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidParenthesization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

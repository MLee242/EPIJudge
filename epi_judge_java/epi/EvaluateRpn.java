package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;

public class EvaluateRpn {
  @EpiTest(testDataFile = "evaluate_rpn.tsv")

  public static int eval(String expression) {

    String[] s = expression.split(",");
    Deque<Integer> dq = new LinkedList<>();
    for(int i = 0 ; i < s.length; i++){

      String str = s[i];
      char c = str.charAt(0);
      if(str.length() == 1 && !Character.isDigit(c)){

        int b = dq.pop();
        int a = dq.pop();
        if(c == '+'){
          dq.push(a + b);
        }else if(c == '-'){
          dq.push(a - b);
        }else if(c == '*'){
          dq.push(a * b);
        }else{
          dq.push(a / b);
        }

      }else{
        dq.push(Integer.parseInt(str));
      }
    }

    return dq.pop();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvaluateRpn.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

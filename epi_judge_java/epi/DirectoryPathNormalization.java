package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class DirectoryPathNormalization {
  @EpiTest(testDataFile = "directory_path_normalization.tsv")

  public static String shortestEquivalentPath(String path) {

    if(path == null || path.length() == 0) return path;

    boolean absolutePath = false;
    if(path.charAt(0) == '/'){
      absolutePath = true;
      path = path.substring(1);
    }

    String[] str = path.split("/");

    Deque<String> dq = new LinkedList<>();

    for(int i = 0 ; i < str.length; i++){
      String p = str[i];
      if(p.equals(".")){
        continue;
      }else if(p.equals("..")){
        if(!dq.isEmpty() && !dq.peek().equals("..")) dq.pop();
        else dq.push("..");
      }else{
        if(p.length() > 0){
          dq.push(p);
        }
      }
    }
    StringBuilder ans = new StringBuilder();

    while(!dq.isEmpty()){
      if(ans.length() > 0){
        ans.insert(0, "/");
      }
      ans.insert(0, dq.poll());
    }
    if(absolutePath){
      ans.insert(0, "/");
    }

    return ans.toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DirectoryPathNormalization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

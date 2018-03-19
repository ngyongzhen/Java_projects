import java.util.*;

// Prints out the possible steps taken
class WaysToReachWithPrint{
  public static void main(String[] args){
    int i = 10;
    List<List<List<Integer>>> memo = new ArrayList<List<List<Integer>>>();
    
    // For 0
    memo.add(new ArrayList<List<Integer>>());
    memo.get(0).add(new ArrayList<Integer>());
    
    // For n=1, there is 1 way to move
    List<List<Integer>> x = new ArrayList<List<Integer>>();
    x.add(new ArrayList<Integer>());
    x.get(0).add(1);
    memo.add(x);
    // For n=2, there are 2 ways to move; [1,1] + [2]
    List<List<Integer>> y = new ArrayList<List<Integer>>();
    y.add(new ArrayList<Integer>());
    y.get(0).add(1);
    y.get(0).add(1);
    y.add(new ArrayList<Integer>());
    y.get(1).add(2);
    memo.add(y);
    
    run(i, memo);
    
    for(List<Integer> l : memo.get(i)){
      System.out.println(l);
    }
    System.out.println("Total ways: " + memo.get(i).size());
  }
  
  private static void run(int n, List<List<List<Integer>>> memo){
    for(int i = 3; i <= n; i++){
      // List of moves to be added for current i
      List<List<Integer>> z = new ArrayList<List<Integer>>();
      
      for(int j = 1; j <= 3; j++){
        for(List<Integer> l : memo.get(i-j)){
            List<Integer> temp = new ArrayList<Integer>(l);
            l.add(j);
            z.add(l);        
        }
      }      
      memo.add(z);
    }
  }
}
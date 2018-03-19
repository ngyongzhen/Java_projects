import java.util.*;


class WaysToReach{
  public static void main(String[] args){
    int i = 10;
    // Init memoized table
    int[] memo = new int[i+1];
    Arrays.fill(memo, -1);
    // Base cases. Num of ways to traverse corresponding steps
    memo[0] = 1;
    memo[1] = 1;
    memo[2] = 2;
    
    run(i, memo);
    System.out.println(memo[i]);
  }
  
  private static void run(int n, int[] memo){
    for(int i = 3; i <= n; i++){
      if(memo[i] == -1){
        memo[i] = memo[i-3] + memo[i-2] + memo[i-1];
      }
    } 
  }
}
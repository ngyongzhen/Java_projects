class NumOfWays{
	
	public static void main(String[] args){
    int n = 10;
    int max = 3;
    int[] stepCounts = new int[n+1];
    stepCounts[0] = 1;
    stepCounts[1] = 1;
    stepCounts[2] = 2;
    for(int i = 3; i <= max; i++){
      for(int j = 1; j <= i; j++){
        stepCounts[i] += stepCounts[i-j];
      }
    }
    
    countSteps(n, max, stepCounts);
    System.out.println(stepCounts[n]);
  }
    
  private static void countSteps(int n, int maxSteps, int[] stepCounts){
    for(int i = maxSteps + 1; i <= n; i++){
      for(int j = 1; j <= maxSteps; j++){
        stepCounts[i] += stepCounts[i-j];
      }
    }
  }
  
}
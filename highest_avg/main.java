class Solution{
  public static void main(String[] args){
    String[][] arr = {{"Charles","80"},
                  {"Bob","75", "95"},
                  {"Darwin","22"}, 
                  {"Lewis","60"}};
    findHighestAverage(arr);
    
  }
  
  public static void findHighestAverage(String[][] arr){
    int highestAvg = 0;
    String owner = "";
    for(int i = 0; i< arr.length; i++){
      int avg = 0;
      for(int j = 1; j < arr[i].length; j++){
        avg += Integer.parseInt(arr[i][j]);
      }
      avg /= arr[i].length-1;
      if(avg > highestAvg){
        highestAvg = avg;
        owner = arr[i][0];
      }
    }
    System.out.println(owner + ": " + highestAvg);
  }
}
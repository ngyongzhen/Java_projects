class FindAllSubsetAddsToSum{
	public static void main(String[] args){
    int[] x = {12, 2,5,7,9, 12};
    int n = 12;
    Set<String> tree = new TreeSet<String>();
    combi(x, new ArrayList<Integer>(), n, tree);
    System.out.println(tree);
    System.out.println(tree.size());
  }
  
  public static void combi(int[] arr, List<Integer> output, int n, Set<String> tree){
    if(output.size() > 0){
      // Check sum
      int sum = 0;
      for(int i : output){
        sum += i;
      }
      if(sum == n){
        Collections.sort(output);
        tree.add(output.toString());
      }else if(sum > n){
        return;
      }
    }
    // Iterate through arr. Remove  current
    // used element before calling recursive
    // function again. 
    // The output should include the cur element
    // before calling recursion. Remove the cur
    // element on return to revert output back
    // to original state
    for(int i = 0; i < arr.length; i++){
      int[] newArr = new int[arr.length-1];
      int index = 0;
      for(int j = 0; j < arr.length; j++){
        if(i==j){continue;}
        newArr[index] = arr[j];
        index++;
      }
      output.add(arr[i]);
      combi(newArr, output, n, tree);
      output.remove(output.size()-1);
    }
  }
}
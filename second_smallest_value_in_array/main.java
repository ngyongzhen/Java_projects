public static void main(String[] args){
    int[] arr = {1,2,9,1,2};
    Arrays.sort(arr);
    int smallest = arr[0];
    for(int i : arr){
      if(i != smallest){
        System.out.println(i);
        break;
      }
    }
  }
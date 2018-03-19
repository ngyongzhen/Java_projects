import java.util.*;

class RadixSort{
public static void main(String[] args) {
    // Construct array using user's input
    Scanner reader = new Scanner(System.in);    
    System.out.println("Enter array size");
    int size = reader.nextInt();
    int[] arr = new int[size];
    for(int i = 0; i < size; i++){
      System.out.println("Enter num for element " + (i + 1));
      arr[i] = reader.nextInt();
    }
    // Start of radix sort logic
    
    // Determine num of passes
    int max = findMax(arr);
    int numOfPasses = 0;
    int value = max;
    while(value > 0){
      value /= 10;
      numOfPasses++;
    }
    List<List<Integer>> bucket = new ArrayList<List<Integer>>();
    
    for(int i = 0; i < numOfPasses; i++){
      // Reset buckets
      bucket.clear();
      for(int a = 0; a< 10; a++){bucket.add(new ArrayList<Integer>());}
      
      for(int j : arr){
        int index = j;
        for(int k = 0; k < i; k++){
          // Find the integer corresponding to the current pass
          index /= 10;
        }
        index %= 10;
        bucket.get(index).add(j);
      }
      // Extract values from buckets into array
      int arrIndex = 0;
      for(List<Integer> l : bucket){
        for(int j : l){
          arr[arrIndex] = j;
          arrIndex++;
        }
      }
    }
    // Print sorted array
    for(int i : arr){
      System.out.println(i);
    }
  }
  
  private static int findMax(int[] arr){
    int largest = arr[0];
    for(int i = 1, n = arr.length; i < n; i++){
      if(arr[i] > largest){
        largest = arr[i];
      }
    }
    return largest;
  }
}
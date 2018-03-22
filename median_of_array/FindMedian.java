/*
 * Recusively call partition until the correct median index is returned.
 * Return the value of that index.
 *
 */
class FindMedian {
  public static void main(String[] args) {
    int[] arr = {3,7,2,1,6,5,4,10};
    // Let median be floor(array.len/2)
    System.out.println(median(arr, arr.length/2));
  }
  
  private static int median(int[] arr, int medianIndex){
   
    int index = -1;
    int start = 0;
    int end = arr.length-1;
    while(index != medianIndex){
      index = partition(arr, start, end);
      if(index < medianIndex){
        start = index + 1;
      }else{
        end = index - 1;
      }
    index = partition(arr, start, end);
    }
    for(int i : arr)
    System.out.println(i);
    return arr[index];
  }
  
  private static int partition(int[] arr, int start, int end){
    int pivot = arr[start];
    int pivotIndex = start;
    for(int i = start + 1; i <= end; i++){
      if(arr[i] < pivot){
        swap(arr, ++pivotIndex, i);
      }
    }
    swap(arr, start, pivotIndex);    
    return pivotIndex;
  }
  
  private static void swap(int[] arr, int index1, int index2){
    if(index1 == index2){return;}
    arr[index1] = arr[index1] ^ arr[index2];
    arr[index2] = arr[index1] ^ arr[index2];
    arr[index1] = arr[index1] ^ arr[index2];
  }
}

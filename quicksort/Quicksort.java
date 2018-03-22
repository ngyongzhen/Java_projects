class Quicksort{
public static void main(String[] args) {
    List<Integer> ip = new ArrayList<Integer>();
    ip.add(2);
    ip.add(91);
    ip.add(23);
    ip.add(666);
    ip.add(1);
    ip.add(0);
    ip.add(-10);
    ip.add(72);
    ip.add(4);
    List<Integer> op = new ArrayList<Integer>();
    // strandSort(ip, op);
    // System.out.println(median(ip, 0));
    quickSort(ip, 0, ip.size()-1);
    System.out.println(ip);
  }
  private static void quickSort(List<Integer> arr, int start, int end){
    if(start < end){
      int partitionIndex = partition(arr, start, end);
      quickSort(arr, start, partitionIndex - 1);
      quickSort(arr, partitionIndex + 1, end);
    }
  }
  
  private static int partition(List<Integer> arr, int start, int end){
    int pivot = arr.get(end);
    int partitionIndex = start;
    for(int i = start; i < end; i++){
      if(arr.get(i) <= pivot){
        // Swap
        int temp = arr.get(i);
        arr.set(i, arr.get(partitionIndex));
        arr.set(partitionIndex, temp);
        
        partitionIndex++;
      }
    }
    // Swap pivot to pivotIndex
    int temp = arr.get(end);
    arr.set(end, arr.get(partitionIndex));
    arr.set(partitionIndex, temp);
    return partitionIndex;
  }
}


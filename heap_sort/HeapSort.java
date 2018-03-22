class HeapSort{
  public static void main(String[] args){
    int[] arr = new int[]{91,99,23,59,1, 17, 99123,299, 912, 0, 56};
    MinHeap minHeap = new MinHeap(arr);
    minHeap.sort();
    for(int i : minHeap.getArr()){
      System.out.println(i);
    }
  }
}

class MinHeap{

  int[] heapArr;
  
  public MinHeap(int[] arr){
    heapArr = arr;
    heapify(0);
  }
  
  public int peek(){
    if(heapArr.length < 0){throw new IllegalStateException();}
    return heapArr[0];
  }
  
  public int[] getArr(){
    return heapArr;
  }
  
  // Sorts the heap 
  public void sort(){
    int[] sorted = new int[heapArr.length];
    int sortedIndex = 0;
     
    while(heapArr.length > 0){
      sorted[sortedIndex] = pop();
      sortedIndex++;
    }    
    heapArr = sorted;
  }
  
  public void add(int num){
    int[] heapArrNew = new int[heapArr.length+1];
    System.arraycopy(heapArr, 0, heapArrNew, 1, heapArr.length);
    heapArrNew[0] = num;
    heapArr = heapArrNew;
    heapify(0);
  }
  
  public int pop(){
    int root = heapArr[0];
    swap(0, heapArr.length-1);
    int[] heapArrNew = new int[heapArr.length-1];
    System.arraycopy(heapArr, 0, heapArrNew, 0, heapArr.length-1);
    heapArr = heapArrNew;
    heapify(0);
    return root;
  }
  
  private int leftChildIndexOf(int n){
    if(2*n + 1 >= heapArr.length){return -1;}
    return (2*n + 1);
  }
  
  private int rightChildIndexOf(int n){
    if(2*n + 2 >= heapArr.length){return -1;}
    return (2*n + 2);
  }
  
  private int getLesserChildIndexOf(int n){
    if(rightChildIndexOf(n) == -1){
      return leftChildIndexOf(n);
    }else{
      if(heapArr[leftChildIndexOf(n)] > heapArr[rightChildIndexOf(n)])
        return rightChildIndexOf(n);
      else
        return leftChildIndexOf(n);
    }
  }
    
  private void swap(int index1, int index2){
    int temp = heapArr[index1];
    heapArr[index1] = heapArr[index2];
    heapArr[index2] = temp;
  }
   
  public void heapify(int n){
    if(leftChildIndexOf(n) != -1){
      heapify(leftChildIndexOf(n));
    }
    if(rightChildIndexOf(n) != -1){
      heapify(rightChildIndexOf(n));
    }
    
    int lesserChildIndex = getLesserChildIndexOf(n);
    if(lesserChildIndex == -1){
      return;
    }
    if(heapArr[n] > heapArr[lesserChildIndex]){
      swap(n, lesserChildIndex);
    }
  }
}
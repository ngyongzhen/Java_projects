public static int median(int[] a1, int[] a2){
    // What is median?
    // floor(length/2) -> Middle element (if odd)
    // (length/2 + (length/2 - 1))/2 (if even)
    int index1 = -1;
    int index2 = -1;
  
    int totalLength = a1.length + a2.length;
    while((index1 + 1) + (index2 + 1) < totalLength/2 + (1 - totalLength%2)){
      if(a1[index1+1] < a2[index2+1]){
        index1++;
      }else{
        index2++;
      }
    }
    // If odd, return largest value out of the index
    if(totalLength%2 == 1){
      if(index1 == -1){
        return a2[index2];
      }
      if(index2 == -1){
        return a1[index1];
      }
      return a1[index1] > a2[index2] ? a1[index1] : a2[index2];
    }
    // If even, return sum of largest 2 value divided by 2
    if(index1 == -1){
      return (a2[index2] + a2[index2-1])/2;
    }
    if(index2 == -1){
      return (a1[index1] + a1[index1-1])/2;
    }
    int sum = 0;
    for(int i = 0; i < 2; i++){
      if(a1[index1] > a2[index2]){
        sum += a1[index1];
        index1--;
        if(index1 == -1){
          sum += a2[index2];
          break;
        }
      }else{
        sum += a2[index2];
        index2--;
        if(index2 == -1){
          sum += a1[index1];
          break;
        }
      }
    }
    return sum/2;
  }
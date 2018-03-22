class BinaryRepresentation{
	  public static void main(String[] args){
    int input =16;
    List<Integer> pow = new ArrayList<Integer>();
    // 2 pow 0 = 1
    pow.add(1);
    populatePowTable(input, pow);
    convertToBinary(input, pow);
    
  }
  
  public static void populatePowTable(int n, List<Integer> pow){
    if(pow.get(pow.size()-1) > n){
      return;
    }
    while(pow.get(pow.size()-1) < n){
      pow.add(pow.get(pow.size()-1) * 2);
    }
  }
  public static void convertToBinary(int n, List<Integer> pow){
    String binary = "";
    for(int i = pow.size()-1; i >= 0; i--){
      if(n - pow.get(i) >= 0){
        binary += "1";
        n-=pow.get(i);
      }else{
        // No need to add 0's if empty string (trailing 0s)
        if(!binary.isEmpty()){
          binary += "0";
        }
      }
    }
    // Beautifier
    for(int i = binary.length()-4; i >= 0; i-=4){
      binary = binary.substring(0, i) + " " + binary.substring(i,binary.length());
    }
    System.out.println(binary);
  }
}
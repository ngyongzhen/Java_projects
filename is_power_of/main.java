class Solution{
  public static void main(String[] args){
    int n = 27;
    int pow = 3;
    System.out.println(isPowerOf(n, pow));
  }
  
    public static boolean isPowerOf(int n, int pow){
    // Power of means the number n can be repeatedly
    // divided by supposed power until it reaches 1
    while(n != 1){
      if(n%pow == 0){
        n/=pow;
      }else{
        return false;
      }
    }
    return true;
  }
}
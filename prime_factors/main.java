class FindPrimeFactor{
	private static void findPrimeFactors(int n){
    if(n == 1){
      System.out.println(1);
      return;
    }
    int remainder = n;
    List<Integer> primes = new ArrayList<Integer>();
    primes.add(2);
    for(int i = 2; i <= n; i++){
      // Build up prime number pool
      if(!primes.contains(i)){
        boolean isPrime = false;
        for(int j : primes){
          // If divisible by any other prime numbers,
          // i is not prime
          if(i%j == 0){
            isPrime = true;
            break;
          }
        }
        if(!isPrime){
          primes.add(i);
        }
      }
      
     for(int j : primes){
       while(n%j == 0){
        n/=j;
        System.out.println(j);
       }
      }
    } 
  }
}
class PalindromicPermutations{
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.println("Please enter text:");
		String input = reader.nextLine();
		Map<Character, Integer> letterCount = new HashMap<Character, Integer>();
		// Count occurence of letters
		for(int i = 0; i < input.length(); i++){
		  char letter = input.charAt(i);
		  if(letterCount.get(letter) == null){letterCount.put(letter, 1);}
		  else{
			letterCount.put(letter, letterCount.get(letter) + 1);
		  }
		}
		boolean alreadyHasOdd = false;
		int count = 0;
		int repeat = 0;
		for(Character c : letterCount.keySet()){
		  if(letterCount.get(c) == 1){
			// Should have at most one odd
			if(alreadyHasOdd){
			  System.out.println("0");
			  return;
			}
			alreadyHasOdd = true;
		  }else{
			// Each pair will be counted in
			int pairs = letterCount.get(c)/2;
			count+=pairs;
			if(pairs > 1){
			  // If more than 1 pair, add the excess pairs 
			  repeat += (pairs-1);
			}
			if(letterCount.get(c)%2 == 1){
			  // Should have at most one odd
			  if(alreadyHasOdd){
				System.out.println("0");
				return;
			  }
			}
		  }
		}
		System.out.println(choose(count, repeat));
	  }
	  
	  // for n characters with k repeating, use n!/k!
	  private static int choose(int n, int k){
		int res = 1;
		for(int i = (k+1); i<= n; i++){
		  res *= i;
		}
		return res;
	  }
 }
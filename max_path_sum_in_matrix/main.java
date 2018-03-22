class MyClass{
	public static void main(String args[]){
		int[][] arr = new int[][]{
			{20,10, 2, 0, 0, 4},
			{ 1, 0, 0,30, 2, 5},
			{ 0,10, 4, 0, 2, 0},
			{ 1, 0, 2,20, 0, 4}
		};
		
		int prevChosenIndex = 0;
		int sum = 0;
		for(int y = 0; y < arr.length; y++){
			int largest = arr[y][prevChosenIndex];
			if(y == 0){
				for(int x = 0; x < arr[y].length; x++){
					if(arr[y][x] > largest){
						largest = arr[y][x];
						prevChosenIndex = x;
					}
				}
			}else{
				int newChosenOffset = 0;
				if(prevChosenIndex > 0){
					if(arr[y][prevChosenIndex-1] > largest){
						largest = arr[y][prevChosenIndex-1];
						newChosenOffset = -1;
					} 
				}
				System.out.println(arr[y][prevChosenIndex]);
				if(arr[y][prevChosenIndex] > largest){
					largest = arr[y][prevChosenIndex];
					newChosenOffset = 0;
				}
				
				if(prevChosenIndex < arr[y].length-1){
					if(arr[y][prevChosenIndex+1] > largest){
						largest = arr[y][prevChosenIndex + 1];
						newChosenOffset = 1;
					}
				}
				prevChosenIndex += newChosenOffset;
			}
			System.out.println(largest + ", ");
			sum += largest;
		}
		
		System.out.println(sum);
	}
}
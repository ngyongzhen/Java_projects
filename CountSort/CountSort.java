import java.util.*;

class CountSort{
	public static void main(String[] args) {
		// Prepare and read inputs
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter num of arr elements");
		int c = reader.nextInt();
		int[] arr = new int[c];
		for(int i = 0; i < c; i++){
		  System.out.println("Enter num");
		  arr[i] = reader.nextInt();
		}
		// Find min and max
		int max = arr[0];
		int min = arr[0];
		for (int i : arr) {
			if (i > max) {
				max = i;
			}
			else if (i < min) {
				min = i;
			}
		}
		int[] bucket = new int[max - min + 1];
		for (int i : arr) {
			bucket[(i - min)]++;
		}
		int[] sumArr = new int[bucket.length];
		for (int i = 0, n = bucket.length; i < n; i++) {
			if (i == 0) {
				sumArr[i] = bucket[i];
			}
			else {
				sumArr[i] = sumArr[i - 1] + bucket[i];
			}
		}
		int[] output = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			int index = arr[i] - min;
			int outputIndex = sumArr[index] - 1;
			output[outputIndex] = arr[i];
		}
		for (int i : output) {
			// Sorted
			System.out.println("Sorted array as follows");
			System.out.println(i);
		}
	}
}
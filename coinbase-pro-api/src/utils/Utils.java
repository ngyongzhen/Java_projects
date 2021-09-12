package utils;

import tree.Order;

public class Utils {
	public static void reverseArray(Object[] array) {
		int i1 = 0;
		int i2 = array.length - 1;
		while(i1 < i2) {
			Object temp = array[i1];
			array[i1++] = array[i2];
			array[i2--] = temp;
		}
	}
}

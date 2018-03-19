import java.util.*;

class UndulatingNumber{
	public static void main(String args[]) {
        int n = 121212123;
        String a = Integer.toString(n);
        if(a.length() < 3){
            System.out.println("No");
            return;
        }
        char[] b = new char[2];
        b[0] = a.charAt(0);
        b[1] = a.charAt(1);
        if(b[0] == b[1]){
            System.out.println("No");
            return;
        }
    	for(int i = 2; i < a.length(); i++){
    	    if(a.charAt(i) != b[i%2]){
    	        System.out.println("No");
    	        return;
    	    }
    	}
		System.out.println("Yes");
    }
}
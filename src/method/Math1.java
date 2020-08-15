package method;

public class Math1 {
	
	public int comp(int num1, int num2, int func) {
		if(func == 1) {
			if(num1 < num2) {
				return num2;
			} else {
				return num1;
			}
		}else {
			if(num1 < num2) {
				return num1;
			} else {
				return num2;
			}
		}
	}
}

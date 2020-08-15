package java_study;

public class Example04 {

	public static void main(String[] args) {
		String[] name = {"철수","영희","짱구"};
		
//		for(int i = 0; i < 3; i++) {
//			System.out.print(name[i]);
//		}
		
		for(int i = 0; i < name.length; i++) {
			System.out.print(name[i]);
		}
		
		name[1] = "";
	}
	
}

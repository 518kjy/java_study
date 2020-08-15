package java_study;

import java.util.Scanner;

public class Example03 {

	public static void main(String[] args) {
		
		System.out.print("숫자를 입력해주세요 : ");
		
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		for(int i = 1; i <=  num; i++) {
			System.out.print(i+" ");
		}
	}
	
}

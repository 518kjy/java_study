package java_study;

import java.util.ArrayList;
import java.util.Scanner;

public class Example05 {
	
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0; i < 5; i++) {
			System.out.print(i+1+"번째 이름을 입력하세요\t:");
			list.add(sc.next());
			
//			if(i == 4) {
//			}
		}
		System.out.print(list.toString());
	}
}

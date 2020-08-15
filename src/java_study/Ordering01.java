package java_study;

import java.util.Scanner;

import method.Math1;

public class Ordering01 {
	
	public static int[] num = {9, 10, 1, 7, 5};
	
	public static void main(String[] args) {
		Math1 math = new Math1();
		System.out.print("설정 배열 : ");
		for(int i = 0; i < num.length; i++) {
			System.out.print(num[i] + " ");
		}
		System.out.print("\n\n기능 선택 <1: 최댓값 출력 / 2: 순서 재배열> : ");
		
		Scanner sc = new Scanner(System.in);
		int func = 0, res = num[0];
		int big, small;
		func = sc.nextInt();
		
		if(func == 1) {
			for(int i = 0; i < num.length; i++) {
				res = math.comp(res, num[i], 1);
			}
			System.out.print("\n\n배열 중 최댓값은 " + res + "입니다.\n");
		}else {
			for(int i = 0; i < num.length-1; i++) {
				big = math.comp(num[i], num[i+1], 1);
				small = math.comp(num[i], num[i+1], 2) ;
				
				num[i] = big;
				num[i+1] = small;
			}
			System.out.print("\n\n배열 오름차순 정렬 : ");
			for(int i = 0; i < num.length; i++) {
				System.out.print(num[i] + " ");
			}
			System.out.print("\n배열 내림차순 정렬 : ");
			for(int i = num.length; i > 0; i--) {
				System.out.print(num[i-1] + " ");
			}
		}
	}
	
	
}

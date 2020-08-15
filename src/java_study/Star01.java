package java_study;

import java.util.Scanner;

public class Star01 {
	
	public static void main(String[] args) {
		int boundary = 0;
		Scanner input = new Scanner(System.in);
		
		System.out.print("너의 별자리는 몇 줄이니?\t");
		boundary = input.nextInt();
		
		for(int i = 0; i < boundary; i++) {
			for(int j = 0; j < boundary - i -1; j++) {
				System.out.print("~");
			}
			for(int j = 0; j < i+1; j++) {
				System.out.print("*");
			}
			for(int j = 0; j < i+1; j++) {
				System.out.print("*");
			}
			for(int j = 0; j < boundary - i -1; j++) {
				System.out.print("~");
			}
			System.out.print("\n");
		}

		for(int i = 0; i < boundary; i++) {
			for(int j = 0; j < i; j++) {
				System.out.print("~");
			}
			for(int j = 0; j < boundary - i; j++) {
				System.out.print("*");
			}
			for(int j = 0; j < boundary - i; j++) {
				System.out.print("*");
			}
			for(int j = 0; j < i; j++) {
				System.out.print("~");
			}
			System.out.print("\n");
		}
	}
}

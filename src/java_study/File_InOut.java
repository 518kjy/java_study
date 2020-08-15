package java_study;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import method.StudentInfoManager;
import method.StudentVo;

public class File_InOut {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		ArrayList<StudentVo> list = new ArrayList<StudentVo>();
		StudentInfoManager manager = new StudentInfoManager();
		
		int menu = 1;
		int func = 0;
		int state = 1;
		
		try {
	        // 바이트 단위로 파일읽기
	        String filePath = "D:/eclipse_projects/java_study/src/Student_List.txt"; // 대상 파일
	        FileInputStream fileStream = null; // 파일 스트림
		        
	        fileStream = new FileInputStream(filePath);// 파일 스트림 생성
	        //버퍼 선언
		    byte[] readBuffer = new byte[fileStream.available()];
	        while (fileStream.read(readBuffer) != -1){}
	        System.out.println(new String(readBuffer)); //출력

		    fileStream.close(); //스트림 닫기
		} catch (Exception e) {
		    e.getStackTrace();
		}
		
		while(menu == 1) {
			System.out.println("==============================================");
			System.out.println("원하시는 기능을 선택해주세요.\n");
			System.out.println("1. 학생 성적 입력");
			System.out.println("2. 학생 성적 조회");
			System.out.println("3. 학생 성적 수정");
			System.out.println("4. 학생 성적 삭제");			
			System.out.println("5. 프로그램 종료");
			System.out.print("\t\t\t기능 : ");
			state = 1;
			try {
				func = sc.nextInt();
			}catch(InputMismatchException ime){
				sc = new Scanner(System.in);
				func = 0;
			}
			
			switch(func) {
				case 1:
					while(state == 1) {
						StudentVo vo = new StudentVo();
						System.out.println("==============================================");
						System.out.print("이름을 입력하세요\t: ");
						vo.setName(sc.next());
						if(manager.checkName(list, vo.getName())) {
							System.out.println("동일한 이름이 이미 존재합니다. 다시 확인바랍니다.");
							state--;
						}
						if(state == 0) { break; }
						System.out.print("국어 성적을 입력하세요\t: ");
						vo.setKor(sc.nextInt());
						System.out.print("영어 성적을 입력하세요\t: ");
						vo.setEng(sc.nextInt());
						System.out.print("수학 성적을 입력하세요\t: ");
						vo.setMat(sc.nextInt());
						list.add(vo);
						System.out.print("내용을 더 추가하시겠습니까?(Y/N) : ");
						if(sc.next().equals("N")) { state--; };
					}
					break;
				case 2:
					System.out.println("==============================================");
					for(int i = 0; i < list.size(); i++) {
						manager.printInfo(list, i);
					}
					break;
				case 3:
					System.out.println("==============================================");
					System.out.print("수정할 학생의 이름을 입력해주세요 : ");
					for(int i = 0; i < list.size(); i++) {
						if(manager.checkName(list, list.get(i).getName())) {
							System.out.println("이름\t국어\t영어\t수학\t평균");
							manager.printInfo(list, i);
							while(state == 1) {
								System.out.println("==============================================");
								System.out.print("수정할 과목을 하나 골라주세요 : ");
								switch(sc.next()) {
								case "국어" :
									System.out.print("국어 성적을 입력하세요\t: ");
									list.get(i).setKor(sc.nextInt());
									break;
								case "엉어" :
									System.out.print("영어 성적을 입력하세요\t:");
									list.get(i).setEng(sc.nextInt());
									break;
								case "수학" :
									System.out.print("수학 성적을 입력하세요\t:");
									list.get(i).setMat(sc.nextInt());
									break;
								default :
									System.out.println("입력 오류입니다. 다시 입력 바랍니다.");
									break;
								}
								System.out.println("==============================================");
								System.out.println("이름\t국어\t영어\t수학\t평균");
								manager.printInfo(list, i);
								System.out.print("추가로 더 수정시겠습니까?(Y/N) : ");
								if(sc.next().equals("N")) { state--; i=99;}
							}
						}else {
							System.out.println("해당하는 이름의 학생 정보는 없습니다.");
							break;
						}
					}
					break;
				
				case 5:
					BufferedOutputStream bs = null;
					try {
						bs = new BufferedOutputStream(new FileOutputStream("D:/eclipse_projects/java_study/src/Student_List.txt"));
						String str = "이름\t국어\t영어\t수학\t평균\n";
						bs.write(str.getBytes()); //Byte형으로만 넣을 수 있음
						for(int i = 0; i <= list.size(); i++) {
							str = list.get(i).getName()+"\t"+list.get(i).getKor()+"\t"+list.get(i).getEng()+"\t"+list.get(i).getMat()+"\t"+list.get(i).getAvg()+"\n";
							bs.write(str.getBytes()); //Byte형으로만 넣을 수 있음
						}
						bs.write(("////").getBytes());
					} catch (Exception e) {
						e.getStackTrace();
						// TODO: handle exception
					}finally {
						bs.close();
					}
					System.out.println("==============================================");
					System.out.println("이용해주셔서 감사합니다.");
					menu--;
					break;
				default:
					System.out.println("==============================================");
					System.out.println("잘못 입력하셨습니다. 다시 입력바랍니다.");
			}
		}
	}
	
}

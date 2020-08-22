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

public class ERP_Student {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		ArrayList<StudentVo> list = new ArrayList<StudentVo>();
		StudentInfoManager manager = new StudentInfoManager();
		
		int menu = 1;
		int func = 0;
		int state = 1;
		
		try {
	        // 바이트 단위로 파일읽기
	        String filePath = "D:/eclipse_projects/java_study/src/Student_List.txt"; // 대상 파일 경로
	        FileInputStream fileStream = null; 						// 파일 스트림 선언
	        fileStream = new FileInputStream(filePath);				// 경로의 대상 파일로 파일 스트림 생성
	        //버퍼 선언
		    byte[] readContents = new byte[fileStream.available()];	// 파일 스트림의 문자수와 동일한 버퍼(문자열) 생성
		    while(fileStream.read(readContents) != -1) { }			// 파일 스트림을 따라 파일을 불러와 버퍼에 저장													
	        String context = new String(readContents); 				// 문장 로드 완료
		    fileStream.close(); 									// 스트림 닫기
		    //list 정보 불러오기(구문 쪼개기)
		    String[] context_line = context.split("\n");			// 줄단위 쪼개기
			for(int i = 1; i < context_line.length; i++) {
			    String[] context_item = context_line[i].split("\t");// 단어단위 쪼개기
			    StudentVo vo = new StudentVo();						// list 재생성
			    vo.setName(context_item[0]);
			    vo.setKor(Integer.parseInt(context_item[1]));
			    vo.setEng(Integer.parseInt(context_item[2]));
			    vo.setMat(Integer.parseInt(context_item[3]));
			    list.add(vo);
			}
			System.out.println("백업 데이터 로드 완료!");
		} catch (Exception e) {
		    e.getStackTrace();
		}
		
		while(menu == 1) {
			try {
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
							if(sc.next().equalsIgnoreCase("N")) { state--; };
						}
						break;
					case 2:
						System.out.println("==============================================");
						if(list.size() == 0) {
							System.out.println("입력 정보가 없습니다. 학생 정보를 입력해주세요.");
						}else {
							for(int i = 0; i < list.size(); i++) {
								manager.printInfo(list, i);
							}
						}
						break;
					case 3:
						System.out.println("==============================================");
						System.out.print("수정할 학생의 이름을 입력해주세요 : ");
						String name = sc.next();
						for(int i = 0; i < list.size(); i++) {				//loop 구문도 사용해보자!!
							if(list.get(i).getName().equals(name)) {
								System.out.println("==============================================");
								System.out.println("이름\t국어\t영어\t수학\t평균");
								manager.printInfo(list, i);
								while(state == 1) {
									System.out.print("수정할 과목을 하나 골라주세요 : ");
									switch(sc.next()) {
									case "국어" :
										System.out.print("국어 성적을 입력하세요\t: ");
										list.get(i).setKor(sc.nextInt());
										break;
									case "영어" :
										System.out.print("영어 성적을 입력하세요\t:");
										list.get(i).setEng(sc.nextInt());
										break;
									case "수학" :
										System.out.print("수학 성적을 입력하세요\t:");
											list.get(i).setMat(sc.nextInt());
										break;
									default :
										System.out.println("입력 오류입니다. 다시 입력 바랍니다.");
									}
									System.out.println("==============================================");
									System.out.println("이름\t국어\t영어\t수학\t평균");
									manager.printInfo(list, i);
									System.out.print("추가로 더 수정시겠습니까?(Y/N) : ");
									if(sc.next().equalsIgnoreCase("N")) { state--; i = 99;}
								}
							}else {
								if(i == list.size() -1) {				
									System.out.println("해당하는 이름의 학생 정보는 없습니다. 다시 확인 바랍니다.");
								}
							}
						}
						break;
					case 4:
						System.out.println("==============================================");
						System.out.print("삭제할 학생의 이름을 입력해주세요 : ");
						name = sc.next();
						loop : for(int i = 0; i < list.size(); i++) {				
							if(list.get(i).getName().equals(name)) {
								System.out.println("==============================================");
								System.out.println("이름\t국어\t영어\t수학\t평균");
								manager.printInfo(list, i);
								System.out.print("정말로 지우시겠습니까?(Y/N) : ");
								if(sc.next().equalsIgnoreCase("Y")) {
									list.remove(i);
									break loop;
								}else {
									System.out.println("삭제를 취소하셨습니다.");
									break loop;
								}
							}else if(i == list.size()-1) {
								System.out.println("해당하는 이름의 학생 정보는 없습니다. 다시 확인 바랍니다.");
								break loop;
							}
						}
						break;
					case 5:
						BufferedOutputStream bs = null;
						try {
							bs = new BufferedOutputStream(new FileOutputStream("D:/eclipse_projects/java_study/src/Student_List.txt"));
							String str = "이름\t국어\t영어\t수학\t평균\n";
							bs.write(str.getBytes()); //Byte형으로만 넣을 수 있음
							for(int i = 0; i < list.size(); i++) {
								str = list.get(i).getName()+"\t"+list.get(i).getKor()+"\t"+list.get(i).getEng()+"\t"+list.get(i).getMat()+"\t"+list.get(i).getAvg()+"\n";
								bs.write(str.getBytes()); //Byte형으로만 넣을 수 있음
							}
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
			}catch(Exception e) {
				System.out.println("==============================================");
				System.out.println("잘못 입력하셨습니다. 다시 입력바랍니다.");
			}
		}
	}
	
}

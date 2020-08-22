package order_cafe;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import method.ItemVo;

public class YGY_cafe {
	
	public static void main(String[] args) {
		boolean system = true, admin = true;
		String id = "", pw = "", selector_yn = "", 
				editor_cafe = "", editor_coffee = "";
		String ID_CHECK = "518kjy", PW_CHECK = "121anwkd!";
		int LOGIN = 0,
			MAIN = 1,
			ORDER = 2,
			USER = 3,
			CHARGE = 4,
			ADMIN = 816438;
		int selector_menu = LOGIN, selector_cafe = 0, selector_coffee = 0, 
				cash_bag = 10000, cash_add = 0, pw_try = 0, editor_price = 0;
		
		Scanner sc = new Scanner(System.in);
		ArrayList<String> cafe_list = new ArrayList<String>();
		ArrayList<ItemVo> coffee = new ArrayList<ItemVo>();
		Map<String, ArrayList<ItemVo>> cafe = new HashMap<String, ArrayList<ItemVo>>();
		// 메뉴 데이터 불러오기
		try {
	        // 바이트 단위로 파일읽기
	        String filePath = "D:/eclipse_projects/java_study/src/Coffee_List.txt"; // 대상 파일 경로
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
			    ItemVo vo = new ItemVo();							// list 재생성
			    if(!cafe_list.contains(context_item[0])) {
				    if(!cafe_list.isEmpty()) {
			    	cafe.put(cafe_list.get(cafe_list.size()-1), coffee);
					coffee = new ArrayList<ItemVo>();
				    }
			    	cafe_list.add(context_item[0]);
			    }
			    vo.setCoffee(context_item[1]);
			    vo.setPrice(Integer.parseInt(context_item[2]));
			    coffee.add(vo);
		    	if(i == context_line.length - 1) {
		    		cafe.put(cafe_list.get(cafe_list.size()-1), coffee);
		    	}
			}
//			for(int j = 0; j < cafe_list.size(); j++) {
//				System.out.println(cafe_list.get(j));
//				for(int i = 0; i < cafe.get(cafe_list.get(j)).size(); i++) {
//					System.out.print(cafe.get(cafe_list.get(j)).get(i).getMenu() + "\t");
//					System.out.println(cafe.get(cafe_list.get(j)).get(i).getPrice());
//				}
//			}
			System.out.println("\t환영합니다!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
		while(system) {
			try {
				switch(selector_menu) {
					// Login 창
					case 0:					
						// 회원정보 입력
						System.out.println("===================================");
						System.out.print(" ID를 입력해주세요\t: ");
						id = sc.next();
						System.out.print(" 비밀번호를 입력해주세요\t: ");
						pw = sc.next();
						// 회원정보 확인
						if(id.equals(ID_CHECK)) {
							if(pw.equals(PW_CHECK)) {
								selector_menu = MAIN;
								pw_try = 0;
								System.out.println("===================================");
								System.out.println("환영합니다! " + id + " 님");
							}else {
								System.out.println("===================================");
								System.out.println("비밀번호가 일치하지 않습니다. 다시 입력바랍니다.");
							}
						}else {
							System.out.println("===================================");
							System.out.println("아이디가 일치하지 않습니다. 다시 입력바랍니다.");
						}
						break;
					// 메인 콘솔 창	
					case 1:
						System.out.println("===================================");
						System.out.println("현재 주문 가능한 카페 리스트");
						for(int i = 0; i < cafe_list.size(); i++) {
							System.out.println(i + 1 + ". " + cafe_list.get(i));
						}
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						System.out.println(cafe_list.size() + 1 + ". 사용자 메뉴\n");
						System.out.print("원하시는 기능을 골라주세요\t: ");
						selector_cafe = sc.nextInt() - 1;
						if(selector_cafe >= 0 && selector_cafe < cafe_list.size()) {
							selector_menu = ORDER;
						}else if(selector_cafe == cafe_list.size()) {
							selector_menu = USER;
						}else if(selector_cafe == ADMIN - 1) {
							if(!admin) {
								System.out.println("잘못된 접근(접근권한이 없습니다)");
							}else {
								selector_menu = ADMIN;
							}
						}else {
							throw new Exception();
						}
						break;
					//주문, 결재창
					case 2:
						System.out.println("===================================");
						System.out.println("해당 카페( " + cafe_list.get(selector_cafe) + " )의 음료들입니다");
						for(int i = 0; i < cafe.get(cafe_list.get(selector_cafe)).size(); i++) {
							System.out.print(i+1 + ". " + cafe.get(cafe_list.get(selector_cafe)).get(i).getMenu() + "\t");
							System.out.println(cafe.get(cafe_list.get(selector_cafe)).get(i).getPrice());
						}
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						System.out.println((cafe.get(cafe_list.get(selector_cafe)).size()+1) + ". 이전 메뉴(카페 재선택)\n");
						System.out.print("원하시는 음료를 골라주세요\t: ");
						selector_coffee = sc.nextInt()-1;
						if(selector_coffee == cafe.get(cafe_list.get(selector_cafe)).size()) {
							System.out.println("===================================");
							System.out.println("카페 선택 메뉴로 돌아갑니다.");
							selector_menu = MAIN;
							break;
						}
						System.out.println("===================================");
						System.out.print("\t" + cafe.get(cafe_list.get(selector_cafe)).get(selector_coffee).getMenu() + "\t");
						System.out.println("  " + cafe.get(cafe_list.get(selector_cafe)).get(selector_coffee).getPrice());
						System.out.println("\t현재 잔액\t: " + cash_bag + "\n");
						System.out.print("계산을 이대로 진행하시겠습니까?( Y/N )\t: ");
						selector_yn = sc.next();
						if(selector_yn.equalsIgnoreCase("Y")) {
							if(cash_bag < cafe.get(cafe_list.get(selector_cafe)).get(selector_coffee).getPrice()) {
								System.out.println("잔액이 부족합니다. 사용자 메뉴에서 충전 후 다시 이용해주세요.");
							}else {
								cash_bag -= cafe.get(cafe_list.get(selector_cafe)).get(selector_coffee).getPrice();
								System.out.println("구매가 완료되었습니다. 이용해주셔서 감사합니다!\n현재 잔액 : " + cash_bag + "\n");
								System.out.print("음료를 다시 선택하시겠습니까?( Y : 음료 재선택 / N : 카페 선택 창)\t: ");
								selector_yn = sc.next();
								if(selector_yn.equalsIgnoreCase("Y")) {
									System.out.println("음료를 다시 선택해주세요.");
								}else if(selector_yn.equalsIgnoreCase("N")){
									selector_menu = MAIN;
								}else {
									System.out.println("입력 오류. 음료선택창으로 이동");	// Y,N 버튼으로 설정하면 이 구문은 일어날 수가 없다. 그래서 대충씀...
								}
								break;
							}
							selector_menu = MAIN;
							break;
						}else if(selector_yn.equalsIgnoreCase("N")) {
							System.out.println("===================================");
							System.out.println("구매를 취소하셨습니다.");
							System.out.print("음료를 다시 선택하시겠습니까?( Y : 음료 재선택 / N : 카페 선택 창)\t: ");
							selector_yn = sc.next();
							if(selector_yn.equalsIgnoreCase("N")) {
								selector_menu = MAIN;
								break;
							}else if(selector_yn.equalsIgnoreCase("Y")){
								System.out.println("음료를 다시 선택해주세요.");
							}else {
								System.out.println("입력 오류. 음료선택창으로 이동");	// Y,N 버튼으로 설정하면 이 구문은 일어날 수가 없다. 그래서 대충씀...
							}
						}else {
							System.out.println("입력 오류. 음료선택창으로 이동");		// Y,N 버튼으로 설정하면 이 구문은 일어날 수가 없다. 그래서 대충씀...
						}
						break;
					// 사용자 메뉴
					case 3:
						System.out.println("===================================");
						System.out.println("1. 사용자 정보 확인");
						System.out.println("2. 비밀번호 변경");
						System.out.println("3. 적립금액 충전");
						System.out.println("4. 로그아웃");								// 프로그램 종료는 굳이 추가하지 않았습니다.
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						System.out.println("5. 이전 메뉴\n");
						System.out.print("원하시는 기능을 골라주세요\t: ");
						selector_cafe = sc.nextInt();
						if(selector_cafe == 1) {
							//사용자 정보 불러오기(여기선 id와 비밀번호만 구현)
							System.out.println("===================================");
							System.out.println("사용자 ID\t: " + id);
							System.out.println("비밀번호\t: " + pw);
							System.out.println("현재 잔액\t: " + cash_bag);
						}else if(selector_cafe == 2) {
							System.out.println("===================================");
							System.out.print("본인확인을 위해 현재 비밀번호를 입력해주세요 : ");
							pw = sc.next();
							if(pw.equals(PW_CHECK)) {
								pw_try = 0;
								System.out.print("\n변경할 비밀번호\t: ");
								pw = sc.next();
								if(pw.equals(PW_CHECK)) {
									System.out.println("기존 비밀번호와 동일합니다. 다시 확인해주세요.");
									break;
								}else if(pw.equals(ID_CHECK)) {
									System.out.println("아이디와 같은 비밀번호는 설정불가합니다. 다시 확인해주세요.");
									break;
								}else {
									System.out.print("비밀번호 확인\t: ");
									if(pw.equals(sc.next())) {
										System.out.print("비밀번호를 변경합니다. 이대로 진행할까요?(Y/N)\t: ");
										selector_yn = sc.next();
										if(selector_yn.equalsIgnoreCase("Y")) {
											PW_CHECK = pw;
											selector_menu = LOGIN;
											System.out.println("\n비밀번호가 변경되었습니다. 다시 로그인해주세요.");
										}else {
											System.out.println("\n변경을 취소하셨습니다. 사용자메뉴로 돌아갑니다.");
										}
									}else {
										System.out.println("비밀번호 확인이 동일하지 않습니다. 다시 시도해주세요.");
									}
								}
							}else {
								pw_try++;
								if(pw_try == 3) {
									selector_menu = LOGIN;
									System.out.println("비밀번호 3회 오류로 자동 로그아웃됩니다. 다시 접속해주세요.");
								}else {
									System.out.println("비밀번호가 맞지 않습니다. 다시 시도해주세요.");
								}
							}
						}else if(selector_cafe == 3) {
							selector_menu = CHARGE;
						}else if(selector_cafe == 4) {
							System.out.print("로그아웃하시겠습니까?( Y/N )\t: ");
							selector_yn = sc.next();
							if(selector_yn.equalsIgnoreCase("Y")) {
								selector_menu = LOGIN;
								System.out.println("/n이용해주셔서 감사합니다.");
							}else if(selector_yn.equalsIgnoreCase("N")){
								System.out.println("/n로그아웃을 취소하셨습니다.");
							}else {
								System.out.println("입력 오류. 사용자메뉴 선택창으로 이동");	// Y,N 버튼으로 설정하면 이 구문은 일어날 수가 없다. 그래서 대충씀...
							}
							break;
						}else if(selector_cafe == 5){
							selector_menu = MAIN;
						}else {
							throw new Exception();
						}
						break;
					//잔액충전 창
					case 4:
						System.out.println("===================================");
						System.out.println("현재 잔액\t: " + cash_bag);
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						System.out.println("1.  1,000 원");
						System.out.println("2.  5,000 원");
						System.out.println("3. 10,000 원");
						System.out.println("4. 15,000 원");
						System.out.println("5. 20,000 원");
						System.out.println("6. 30,000 원");
						System.out.println("7.   기타 금액");
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						System.out.println("8. 이전 메뉴(사용자 메뉴)\n");
						System.out.print("원하시는 기능을 골라주세요\t: ");
						selector_cafe = sc.nextInt();
						
						if(selector_cafe > 0 && selector_cafe < 7) {
							switch(selector_cafe) {
								case 1:					// 1,000원
									cash_add = 1000;
									break;
								case 2:					// 5,000원
									cash_add = 5000;
									break;
								case 3:					// 10,000원
									cash_add = 1000;
									break;
								case 4:					// 15,000원
									cash_add = 15000;
									break;
								case 5:					// 20,000원
									cash_add = 20000;
									break;
								case 6:					// 30,000원
									cash_add = 30000;
									break;
								default:				// 발생 불가 에러. 생기면 비정상
									System.out.println("시스템 에러. 어떻게 이곳에 오셨죠....?");
									throw new Exception();
							}
						}else if(selector_cafe == 7) {
							System.out.print("충전할 금액을 입력해주세요\t: ");
							cash_add = sc.nextInt();
							if(cash_add < 0 || cash_add%10 != 0) {
								System.out.println("\n충전이 불가한 금액을 입력하셨습니다. 다시 확인바랍니다.");
								break;
							}
						}else if(selector_cafe == 8){
							selector_menu = USER;
						}else {
							throw new Exception();
						}
						System.out.println("\n충전 요청 금액 :" + cash_add + "\t충전 후 금액 : " + (cash_bag + cash_add));
						System.out.print("충전을 이대로 진행하시겠습니까?( Y/N )\t: ");
						selector_yn = sc.next();
						if(selector_yn.equalsIgnoreCase("Y")) {
							cash_bag += cash_add;
							System.out.print("\n충전을 완료했습니다.\n사용자 메뉴로 돌아갑까요?( Y : 사용자 메뉴 / N : 카페 선택 창 )\t:");
							selector_yn = sc.next();
							if(selector_yn.equalsIgnoreCase("Y")) {
								selector_menu = USER;
							}else if(selector_yn.equalsIgnoreCase("N")){
								selector_menu = MAIN;
							}else {
								selector_menu = USER;
								System.out.println("입력 오류. 사용자 메뉴창으로 이동");	// Y,N 버튼으로 설정하면 이 구문은 일어날 수가 없다. 그래서 대충씀...
							}
						}else if(selector_yn.equalsIgnoreCase("N")){
							selector_menu = USER;
							System.out.println("충전을 취소하셨습니다. 사용자 메뉴로 돌아갑니다.");
						}else {
							selector_menu = USER;
							System.out.println("입력 오류. 사용자 메뉴창으로 이동");		// Y,N 버튼으로 설정하면 이 구문은 일어날 수가 없다. 그래서 대충씀...
						}
						break;
					// 관리자 메뉴
					case 816438:
						editor_cafe = "";
						System.out.println("===================================");
						System.out.println("1. 카페, 메뉴 입력");
						System.out.println("2. 카페, 메뉴 제거");				
						System.out.println("3. 관리자 권한 부여 / 해제");
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						System.out.println("4. 이전 메뉴(카페 선택 창)\n");
						System.out.print("원하시는 기능을 선택하세요\t: ");
						switch(sc.nextInt()) {
							case 1:
								System.out.println("===================================");
								System.out.print("메뉴를 추가할 카페 이름을 입력해주세요\t: ");
								editor_cafe = sc.next();
//								if(!cafe.containsKey(cafe_editor)) {
//									cafe_list.add(cafe_editor);
//								}
								System.out.print("메뉴 이름을 입력해주세요\t\t\t: ");
								editor_coffee = sc.next();
								ItemVo vo = new ItemVo();
								vo.setCoffee(editor_coffee);
								vo.setPrice(editor_price);
								if(cafe_list.contains(editor_cafe)) {
									if(cafe.get(editor_cafe).indexOf(vo) != -1) {
										
									}
								}else {
									
								}
							case 2:
								if(editor_cafe.isEmpty())
								System.out.println("===================================");
								break;
							case 3:
								System.out.println("===================================");
							case 4:
								selector_menu = MAIN;
								break;
							default:				// 발생 불가 에러. 생기면 비정상
								System.out.println("시스템 에러. 어떻게 이곳에 오셨죠....?");
								throw new Exception();			
						}
						break;
					default:
						selector_menu = LOGIN;
						System.out.println("기능 미구현입니다. 로그인창으로 돌아갑니다.");
				}		
			}catch(Exception e) {
				sc = new Scanner(System.in);
				System.out.println("입력정보가 옳지 않습니다. 다시 입력바랍니다.");
				e.printStackTrace();
			}
		}
	}
}

package method;

import java.util.ArrayList;

public class StudentInfoManager {

	public void printInfo(ArrayList<StudentVo> list, int idx) {
		
		System.out.print(list.get(idx).getName()+"\t");
		System.out.print(list.get(idx).getKor()+"\t");
		System.out.print(list.get(idx).getEng()+"\t");
		System.out.print(list.get(idx).getMat()+"\t");
		System.out.println(list.get(idx).getAvg());
		
		return;
	}
	
	public boolean checkName(ArrayList<StudentVo> list, String name) {
		boolean isSame = false;
		if(list.size() == 0) {
			isSame = false;
		}else {
			for(int i = 0; i < list.size(); i++) {
				if(name.equals(list.get(i).getName())){
					return true;
				}else {
					isSame = false;
				}
			}
		}
		return isSame;				//반복문, 조건문 내부에서 반환하는데 왜 밖에 써주지 않으면 에러가 날까...?
	}
}

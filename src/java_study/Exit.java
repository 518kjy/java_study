package java_study;

public class Exit {
    public static void main(String[] args) {
 
        int r1 = 0;
        int r2 = 0;
        
        roop : while(true) {    // roop라고 반복문 이름을 주었다  : 키워드로 붙여준다
            r1++;
            System.out.println("첫번째 반복문 "+r1);
            
            while(true) {
                r2++;
                System.out.println("두번째 반복문 "+r2);
                
                if(r2==3)
                    break roop; // roop라는 반복문을 탈출
            }
            
        }
        
    }
}
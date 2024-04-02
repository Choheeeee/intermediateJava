package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//IO작업의 최상위 목표인 "객체"입출력 보조스트림 예제(직렬화 및 역직렬화)

public class T15ObjectIOStreamTest {

	public static void main(String[] args) {

		//4개의 Member 인스턴스 생성하기 - 객체 자체를 파일에 저장하고 싶음 
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("일지매", 20, "부산");
		Member mem3 = new Member("성춘향", 20, "광주");
		Member mem4 = new Member("이몽룡", 20, "대구");
		
		//객체 자체를 파일에 저장하기 위해
		ObjectOutputStream oos = null;
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream("d:/D_Other/memObj.bin"));
			
			oos.writeObject(mem1);
			oos.writeObject(mem2);
			oos.writeObject(mem3);
			oos.writeObject(mem4);
			
			System.out.println("객체 쓰기 작업 완료!");
			System.out.println("================직렬화 하여 저장(출력)==================");
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/////////////////////////////////////////////////////
		//위에서 직렬화하여 저장한 객체를 읽어와 사용하기
		
		ObjectInputStream  ois = null;
		try {
			
			ois = new ObjectInputStream(new FileInputStream("d:/D_Other/memObj.bin"));
			
			Object obj = null;
			
			while ((obj = ois.readObject()) != null) {
				
				//"읽어온 데이터를 원래의 객체형으로 변환"(=이게 역직렬화) 후 사용
				Member mem = (Member)obj;
				System.out.println("이름 : "+mem.getName());
				System.out.println("나이 : "+mem.getAge());
				System.out.println("주소 : "+mem.getAddr());
				System.out.println("===============================================");
			}
			
		} catch (IOException e) {
			//e.printStackTrace();	//에러 메세지 안보기 위해 주석처리
		} catch (ClassNotFoundException e) {	//클래스를 찾을수 없다는 오류가 나올 수 있는 이유 : 개인과 주고받은 파일을 역직렬화해서 읽어올때, 상대방에게 Member클래스가 없으면 발생함
			e.printStackTrace();
		}finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}


//입출력 해보기위한 Member객체
class Member implements Serializable {	//Member객체는 Serializable(직렬화) 타입이기도 하다.
										//"객체"를 연속적인 바이트기반 데이터스트림으로 출력(저장)할때 = 직렬화
										//디스크에 연속적으로 직렬화돼 저장돼있던 데이터를 읽어와서 객체로 생성하는 작업 = 역직렬화
										//추상메서드가 없는 껍데기만 있는 메서드 = Tag인터페이스 = 객체가 Serializable 타입이란걸 알려주기위한 인터페이스
										//자바는 Serializable 인터페이스를 구현한 클래스만 직렬화 할 수 있도록 제한하고 있음.
//	private String name;
	private transient String name;	//transient는 직렬화에서 제외하고 싶은 경우 선언하는 키워드 ex) 개인정보, 패스워드와 같은 보안정보를 전송하고싶지 않을때
									//transient : 직렬화 하고싶지 않은 인스턴스 멤버변수에 붙인다.
									//객체(=인스턴스 필드)들만 IO작업을 하기때문에, static 필드도 직렬화 대상이 아님
									//직렬화가 되지않는 멤버변수는 기본값으로 저장된다. (참조변수 : null, 숫자형 변수 :0)
	private int age;
	private String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}
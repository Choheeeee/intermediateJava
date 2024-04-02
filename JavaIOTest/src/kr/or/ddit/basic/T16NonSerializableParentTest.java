package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class T16NonSerializableParentTest {

/*
 	부모클래스가 Serializable 인터페이스를 구현하고 있지 않을때, 부모객체의 필드값 처리방법
 	
 		1. 부모클래스가 Serializable 인터페이스를 구현하도록 해야한다.
 		2. 자식클래스가 writeObject()와 readObject()메서드를 이용하여 부모객체의 필드값을 처리할 수 있도록 직접 구현한다.
 		
 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		//객체를 파일로 출력해보자.
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d:/D_Other/nonSerializable.bin"));
		
		Child child = new Child();
		child.setParentName("부모");
		child.setChildName("자식");
		
		oos.writeObject(child);	//내부적으로 직렬화가 일어나, 파일에 출력됨
		
		oos.close();
		
		/////////////////////////////////////////////////////////
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:/D_Other/nonSerializable.bin"));
		
		Child child2 = (Child) ois.readObject();	//내부적으로 역직렬화가 일어나 데이터를 읽어옴
		
		System.out.println("parentName : "+child2.getParentName());
		System.out.println("childName : "+ child2.getChildName());
		
		ois.close();
	}

}



//Serializable을 구현하지 않은 부모 클래스를 정의해보자.
class Parent {
	//	class Parent implements Serializable  { 부모가 직렬화 인터페이스를 구현하고, 자식그걸 구현한 부모만 상속받으면 
	//											자식도 자동으로 직렬화인터페이스를도상속받게 된다.
	//											하지만, 자바는 API를 개발할때 그 부모를 상속받는 모든 자식들이 다 직렬화를 상속받게 되면
	
	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
}


//Serializable을 구현한 자식클래스
class Child extends Parent implements Serializable{	
//	class Child extends Parent {	
	
	private String childName;

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}
	
	
	
	/*
	  	직렬화될때 자동으로 호출됨
	  	접근제한자가 반드시 private여야 자동호출됨
	  	이름도 writeObject여야 함.
	 */
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeUTF(getParentName());
		out.defaultWriteObject();	//직렬화때 기본적으로 했던 작업을 호출함
	}
	
	
	
	/*
	 * 역직렬화될때 자동으로 호출됨
	 * 접근제한자가 private이 아니면 자동호출되지 않는다.
	 * 이름도 writeObject여야 함.
	 */
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		setParentName(in.readUTF());	//writeObject()에서 출력한 순서대로 읽어와야함.(UTF 인코딩형식으로 읽어온 부모이름으로, set해줌)
		in.defaultReadObject();		//역직렬화때 기본적으로 했던 작업을 호출함
	}
	
}

package kr.or.ddit.basic;

class TestClass {
	void instanceMethod() {}
	static void staticMethod() {}
	
	void instanceMethod2() {
		instanceMethod();
		staticMethod();
	}
	
	static void staticMethod2() {
		TestClass2 tc = new TestClass2();
		tc.instanceMethod();
		
		staticMethod();
	}

}

class TestClass2 {
	int iv;
	static int cv;
	
	void instanceMethod() {
		System.out.println(iv);
		System.out.println(cv);
	}
	
	static void staticMethod() {
		TestClass2 tc = new TestClass2();	//객체를 생성해준 후에야 클래스변수에 접근 가능!
		System.out.println(tc.cv);	//객체생서 후 이므로 클래스변수에 접근 할 수 있다!
	}
}


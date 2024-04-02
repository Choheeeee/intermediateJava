package kr.or.ddit.basic;

public class T14ThreadShareDataTest {
/*
 	스레드에서 데이터를 공통(공용)으로 사용하는 방법
 	공유객체를 쓰는 이유 : 스레드들간에 정보교환을 위해(의사소통)
 	
 	1. 스레드들이 공통으로 사용할 데이터를 클래스로 정의한다.
 	2. 공유객체를 생성한다.
 	3. 이 공유객체를 각각의 스레드에 제공한다.
 	4. 각각의 스레드는 이 공유객체의 데이터를 이용하여 작업을 처리한다.
 */

	public static void main(String[] args) {
		
		ShareData sd = new ShareData();
		
		Thread th1 = new CalcPIThread(sd);
		Thread th2 = new PrintPIThread(sd);
		
		th1.start();
		th2.start();
		
	}
}



//원주율 정보를 위한 공유클래스
class ShareData {
	
	private double result;	//원주율을 저장할 변수
	
	
	/*
	 	volatile => 선언된 변수를 컴파일러의 최적화 대상에서 제외시키는 키워드.
	 				(실제 원본데이터가 있는 메모리를 읽어서 작업므로 동기화가 발생	=> 원본데이터를 읽어오므로 처리속도는 느려짐)
	 				즉, 값이 변경되는 즉시 변수에 적용시킨다. (일종의 동기화)
	 */
	private volatile boolean isOk;	//원주율 계산이 완료되었는지를 확인하기 위한 변수

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}
	
}


//원주율 계산하는 스레드
class CalcPIThread extends Thread {
	private ShareData sd;
	
	public CalcPIThread(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		/*
		 	원주율 = (1/1 - 1/3 +1/5 -1/7 + 1/9 ......) * 4;
		 			1 - 3 + 5 - 7 + 9	=> 분모값
		 			0	1	2	3	4	=> 분모를 2로 나눈 몫
		 */
		
		double sum = 0.0;
		
		for (int i = 1; i < 1500000000; i+=2) {
			if (( (i/2) % 2) == 0) {	//2로 나눈 몫이 짝수이면 +
				sum += (1.0/i);
			}else {	//2로 나눈 몫이 홀수이면 -
				sum -= (1.0/i);
			}
		}
		
		sd.setResult(sum * 4);
		sd.setOk(true);
		
	}
}


class PrintPIThread extends Thread {
	private ShareData sd = new ShareData();

	public PrintPIThread(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		while (true	) {
			//원주율 계산이 완료되었는지 체크
			if (sd.isOk()) {
				break;
			}
		}
		
		System.out.println();
		System.out.println("계산된 원주율 : "+sd.getResult());
		System.out.println("PI : "+Math.PI);
	}
	
}
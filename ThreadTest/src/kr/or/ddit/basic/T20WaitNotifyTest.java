package kr.or.ddit.basic;

public class T20WaitNotifyTest {

	public static void main(String[] args) {
		
		DataBox dataBox = new DataBox();
		
		Thread pTh = new ProducerThread(dataBox);
		pTh.start();
		
		Thread cTh = new ConsumerThread(dataBox);
		cTh.start();
		
	}
}

//공유객체용 클래스
class DataBox {
	
	private String data;

	//data가 null이 아닐때 data값을 반환하는 메서드
	public synchronized String getData() {
		
		System.out.println(Thread.currentThread().getName() + " : getData()메서드 진입!");
		
		if (this.data == null) {
			try {
				System.out.println(Thread.currentThread().getName() + " : getData() 메서드 안에서 wait()호출!");
				
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		String returnData = this.data;
		System.out.println("읽어온 데이터 : "+ returnData);
		this.data = null;	//기존 데이터 제거
		
		System.out.println(Thread.currentThread().getName()+ " : getData() 메서드 끝!");
		
		return returnData;
	}

	public synchronized void setData(String data) {
		
		System.out.println(Thread.currentThread().getName()+" : setData() 진입!");
		
		if (this.data != null) {	//데이터가 이미 있으면 설정할 필요가 없으므로, 작업할 필요가 없으므로 wait()호출
			try {
				System.out.println(Thread.currentThread().getName()+" : setData()메서드 안에서 wait()호출!");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.data=data;	//if문에 해당이 안된다면, 데이터가 비었다는 의미이므로 데이터 설정하는 작업 실행
		System.out.println("세팅한 데이터 : "+ this.data);
		
		System.out.println(Thread.currentThread().getName()+" : setData() 메서드 안에서 notify() 호출!");
		notify();	//sdtData()의 작업완료 상황을 알려서, 다른 메서드가 들어와 작업할 수 있도록 알리는 메서드 notify()호출
		System.out.println(Thread.currentThread().getName()+" : setData()메서드 끝!");
	}
}


//데이터를 세팅만 하는 메서드
class ProducerThread extends Thread {
	
	private DataBox dataBox;

	public ProducerThread(DataBox dataBox) {
		super("ProducerThread");
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <=5; i++) {
			String data = "Data-" + i;
			System.out.println(this.getName()+ "가 dataBox.setData("+ data + ") 호출하려고 함");
			dataBox.setData(data);
		}
	}
}


class ConsumerThread extends Thread {
	
	private DataBox dataBox;

	public ConsumerThread(DataBox dataBox) {
		super("ConsumerThread");
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 5; i++) {
			String data = dataBox.getData();
			System.out.println(this.getName()+ "가 dataBox.getData() 호출 후 받은 결과 : "+data);
		}
	}
}

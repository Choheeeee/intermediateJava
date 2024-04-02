package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class H05HorseRace {

	static int rank = 1;
	static List<Horse> horseList = new ArrayList<>();

	public static void main(String[] args) {

		horseList.add(new Horse("01번말"));
		horseList.add(new Horse("02번말"));
		horseList.add(new Horse("03번말"));
		horseList.add(new Horse("04번말"));
		horseList.add(new Horse("05번말"));
		horseList.add(new Horse("06번말"));
		horseList.add(new Horse("07번말"));
		horseList.add(new Horse("08번말"));
		horseList.add(new Horse("09번말"));
		horseList.add(new Horse("10번말"));

		for (Horse horse : horseList) {
			horse.start();
		}

		Thread t2 = new HorsePositionDisplay();
		t2.start();

		for (int i = 0; i < horseList.size(); i++) {
			try {
				horseList.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class Horse extends Thread implements Comparable<Horse> {

	private String name;
	private int rank;
	private int position;

	public Horse(String name) {
		super(name); // 스레드 이름 설정
		this.name = name;
	}

	public String getHorseName() {
		return name;
	}

	public void setHorseName(String name) {
		this.name = name;
	}

	public int getHorseRank() {
		return rank;
	}

	public void setHorseRank(int rank) {
		this.rank = rank;
	}

	public int getHorsePosition() {
		return position;
	}

	public void setHorsePosition(int position) {
		this.position = position;
	}

	@Override
	public int compareTo(Horse o) {
		return Integer.compare(rank, o.getHorseRank());
	}

	@Override
	public void run() {

		for (int i = 0; i < 50; i++) {
			try {
				Thread.sleep((int) (Math.random() * 500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setHorsePosition(i);

			displayPosition();
		}
		this.rank = H05HorseRace.rank;
		H05HorseRace.rank++;
	}

	private void displayPosition() {
		String track = "--------------------------------------------------";
		if (getHorsePosition() != 49) {
			System.out.print(this.getHorseName() + " : ");
			System.out.print(track.substring(0, this.getHorsePosition()) + ">");
			System.out.println(track.substring(this.getHorsePosition() + 1, 50));
		} else {
			System.out.print(this.getHorseName() + " : ");
			System.out.print(track.substring(0, this.getHorsePosition() + 1) + " 도착!");
			System.out.println();
		}
	}
}

class HorsePositionDisplay extends Thread {

//	public void clear(int lineCnt) {
//		for (int i = 0; i < lineCnt; i++) {
//			System.out.println();
//		}
//	}

	@Override
	public void run() {

		while (true) {

//			clear(50);

			int finishedCnt = 0;
			System.out.println("경마가 시작되었습니다!");
			System.out.println("============================================================");
			System.out.println();

			for (int i = 0; i < H05HorseRace.horseList.size(); i++) {
				String track = "--------------------------------------------------";
				Horse horse = H05HorseRace.horseList.get(i);

				if (horse.getHorsePosition() != 49) {
					System.out.print(horse.getHorseName() + " : ");
					System.out.print(track.substring(0, horse.getHorsePosition()) + ">");
					System.out.print(track.substring(horse.getHorsePosition() + 1, 50));
				} else {
					System.out.print(horse.getHorseName() + " : ");
					System.out.print(track.substring(0, horse.getHorsePosition() + 1) + " 도착!");
					System.out.println();

					finishedCnt++;
				}

				if (finishedCnt == H05HorseRace.horseList.size()) {
					return;
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
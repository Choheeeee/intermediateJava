package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class H01Student {

	public static void main(String[] args) {

		List<Student> stdList = new ArrayList<Student>();
		stdList.add(new Student("3", "김초희", 100, 100, 60));
		stdList.add(new Student("7", "김종국", 100, 100, 60));
		stdList.add(new Student("2", "김길동", 90, 100, 80));
		stdList.add(new Student("1", "송혜교", 75, 95, 60));
		stdList.add(new Student("8", "유재석", 100, 100, 60));
		stdList.add(new Student("5", "김태희", 89, 72, 55));
		stdList.add(new Student("9", "박명수", 100, 100, 60));
		stdList.add(new Student("4", "손예진", 70, 65, 100));

		for (Student std : stdList) {
			int rank = 1;
			for (Student std2 : stdList) {
				if (std.getTotal() < std2.getTotal())
					rank++;
			}
			std.setRank(rank);
			System.out.println("정렬 전 : " + std);
		}
		System.out.println("====================================================================================================");
		Collections.sort(stdList);
		
		for (Student std : stdList) {
			System.out.println("학번의 오름차순으로 정렬 후 : "+std);
		}
		System.out.println("====================================================================================================");
		Collections.shuffle(stdList);
		System.out.println("다시 섞은 후 : ");
		for (Student std : stdList) {
			System.out.println(std);
		}
		System.out.println("====================================================================================================");
		Collections.sort(stdList, new DescTotal());
		System.out.println("총점의 내림차순으로 정렬 후 : ");
		for (Student std : stdList) {
			System.out.println(std);
		}
		
	}

}


class DescTotal implements Comparator<Student>{	//정렬기준을 제공해줄 외부 클래스(총점의 내림차순, 총점이 같으면 학번의 내림차순)

	@Override
	public int compare(Student std1, Student std2) {
		if (std1.getTotal()==std2.getTotal()) {
			//return (Integer.parseInt(std1.getStdId())-Integer.parseInt(std2.getStdId()))*-1;
			return std1.getStdId().compareTo(std2.getStdId())*-1;
		}
		else if (std1.getTotal()>std2.getTotal()) {
			return -1;
		}else {
			return 1;
		}
	}
	
}

class Student implements Comparable<Student>{
	
	
	@Override							//학번의 오름차순
	public int compareTo(Student std) {	//같은 한 클래스 안에서, 자신과 매개변수로 들어온 객체를 비교할 때의 정렬기준을 정의함
		return this.stdId.compareTo(std.getStdId());
//		return Integer.parseInt(this.stdId)-Integer.parseInt(std.getStdId());
	}
	

	private String stdId;
	private String stdName;
	private int kor;
	private int eng;
	private int math;
	private int total;
	private int rank;

	public Student(String stdId, String stdName, int kor, int eng, int math) {
		this.stdId = stdId;
		this.stdName = stdName;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.total = kor + eng + math;

	}

	public String getStdId() {
		return stdId;
	}

	public void setStdId(String stdId) {
		this.stdId = stdId;
	}

	public String getStdName() {
		return stdName;
	}

	public void setStdName(String stdName) {
		this.stdName = stdName;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "학생리스트 [학번 = " + stdId + ", 학생명 = " + stdName + ", 국어 = " + kor + ", 영어 = " + eng + ", 수학 = " + math
				+ ", 총점 = " + total + ", 석차 = " + rank + "]";
	}


}
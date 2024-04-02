package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.member.vo.MemberVO;

/*
 	매핑 
 	- 컴퓨터 과학에서 "매핑"은 서로 다른 두 도메인 또는 시스템 간에 정보를 연결하거나 대응시키는 과정을 말한다. 
 	- 예시) 객체 지향 프로그래밍에서 데이터베이스와 객체 간, 그래픽 디자인에서 2D 좌표를 3D 공간에 대응시키는 것
 	- 즉, 객체 지향 프로그래밍 언어와 관계형 데이터베이스 간의 정보를 매핑하고 대응시키는 프로세스를 의미함. 이를 통해 객체와 데이터베이스 간의 대응 및 변환 작업이 수행된다.
 	
 	매퍼 : 데이터와 애플리케이션 사이의 매핑 또는 변환을 담당하는 소프트웨어 구성 요소를 가리킴 
 	
 	결론 : MyBatis가 SQL과 Java 객체 간의 변환하는데 쓰이는 프레임워크인것!!!!
 	
 */
public class MyBatisTest {
	
	/*
	 	 비슷해보이는 SqlSessionFactory와 SqlSession의 사용 흐름:

			- 애플리케이션 시작 시에 SqlSessionFactory를 생성하고 설정 정보를 로드합니다.
			- SqlSessionFactory를 통해 필요한 시점에 SqlSession을 생성합니다.
			- SqlSession을 사용하여 SQL 문을 실행하고 데이터베이스 작업을 수행합니다.
			- SqlSession 사용이 끝나면 반드시 close 메서드로 닫아야 하며, 이렇게 하면 데이터베이스 연결 및 리소스가 해제됩니다.
			- 다음 데이터베이스 작업이 필요한 경우, 새로운 SqlSession을 다시 생성하고 사용합니다.
			- MyBatis는 SqlSessionFactory를 사용하여 SqlSession을 생성하고 관리하며, 이를 통해 데이터베이스 작업을 수행합니다.
			
			결론 : MyBatis는 데이터베이스와 Java 객체 간의 변환을 효과적으로 수행하기 위한 프레임워크입니다. 
				XML 파일은 SQL 쿼리문과 데이터베이스 연결 설정, 그리고 Java 객체와의 변환 설정 등을 담고 있는 외부 파일로, 이를 사용하여 데이터베이스 작업을 구성하고 관리합니다. 
				이렇게 설정 정보와 SQL 쿼리문을 별도의 XML 파일로 분리함으로써 코드와 데이터베이스 작업의 설정을 분리하고 관리하기 용이하게 합니다.
	 */

	public static void main(String[] args) {
		
		//myBatis를 이용하여 DB데이터를 처리하는 작업 순서
		//1. sqlSessionFactory 인터페이스를 통해 myBatis의 환경설정파일(mybatis-config.xml)을 읽어온다.
		SqlSessionFactory sessionFactory = null;	//sqlSessionFactory인터페이스는  MyBatis에서 SQL과 Java 객체 간의 매핑 설정 및 리소스 관리를 담당하는 인터페이스
													//qlSessionFactory는 MyBatis 프레임워크에서 데이터베이스와 상호 작용할 준비가 된 상태의 객체 => 그래서 아래의 과정은 결국, 이 객체를 얻기위한 전제된 선행과정임.
													//아래 과정 후, 최종적으로 얻으려는 객체는 SqlSessionFactory임.
													//SqlSessionFactory는 MyBatis 설정과 데이터베이스 연결 정보를 로드하고, 이 정보를 기반으로 실제 쿼리실행을 담당하는 SqlSession객체를 생성
		
		try {
			//1-1. xml설정파일을 읽기위해 인코딩정보 셋팅
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			//mybatis-config.xml"이라는 설정 파일을 읽는 Reader 객체를 생성함. (xml 이 파일에는 MyBatis의 전반적인 설정이 들어 있음)
			Reader rd = Resources.getResourceAsReader("config/mybatis-config.xml");
			
			//1-2. 위에서 생성된 Reader객체(rd)와 SqlSessionFactoryBuilder를 생성해야 비로소  SqlSessionFactory객체가 생성됨
			//		(SqlSessionFactoryBuilder는 Reader객체를 기반으로 SqlSessionFactory를 생성)
			sessionFactory = new SqlSessionFactoryBuilder().build(rd);	//비로소 SqlSessionFactory객체를 생성하게됨
			
			rd.close();	//스트림 닫기
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//2. 실행할 SQL문에 맞는 쿼리문을 호출하여 원하는 작업을 수행한다.
		
		//2-1. insert작업 연습
		System.out.println("insert 작업 시작!");
		MemberVO mv = new MemberVO();
		mv.setMemId("d001");
		mv.setMemName("강감찬");
		mv.setMemTel("010-1111-1111");
		mv.setMemAddr("경남 진해시");
		
		//SqlSession객체를 이용하여 해당 쿼리문을 실행한다.
		// ex) sqlSession.insert("namespace값.id값", 파라미터 객체)	//namespace는 자바의 package와 비슷(패키지가 없이 클래스만 있으면, 클래스 이름들 충돌 발생하는것처럼)
		// 반환값 : 성공한 레코드 수
		
		//세션열기 (오토커밋 여부 설정) - SQL CRUD를 위한 객체(=SqlSession)
		//SqlSession : SqlSession은 SqlSessionFactory를 통해 생성되고, 실제로 데이터베이스 작업을 실행하는 인터페이스
		SqlSession sqlSession = sessionFactory.openSession(false);	//오토커밋 : false => 트랜잭션 관리하기에 수월함(멀티스레드시 양쪽에서 실행시, 
			
		try {	
			int cnt = sqlSession.insert("memberTest.insertMember", mv);	//sqlSession.insert("namespace값.id값", 파라미터 객체),	mv에 저장된 데이터들로  쿼리를 실행함
			if (cnt > 0) {
				System.out.println("insert 작업 성공 : "+cnt);
				sqlSession.commit();	//위에서 오토커밋 여부를 false라고 했으니까, 작업 성공후 꼭 커밋 해줘야함!
			}else {
				System.out.println("insert 작업 실패!!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();	//세션 닫기
		}
		System.out.println("-------------------------------------------------------------");
		
		
		
		
		
		
		//2-2. update작업 연습
		System.out.println("update 작업 시작");
		MemberVO mv2 = new MemberVO();
		mv2.setMemId("d001");
		mv2.setMemName("강감찬");
		mv2.setMemTel("010-2222-4444");
		mv2.setMemAddr("대전시 중구 오류동");
		
		SqlSession session = sessionFactory.openSession();	//openSession()안에 false생략하면, default는 false
		try {
			//update()메서드의 반환값도 성공한 레코드 수이다.
			int cnt = session.update("memberTest.updateMember", mv2);
			if (cnt > 0) {
				System.out.println("update 작업" +cnt+ "개 성공!");
				session.commit();	//auto커밋을 false로 꺼놨으므로 반드시 명시적으로 commit()해주기!!
			}else {
				System.out.println("updqte 작업 실패!");
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		System.out.println("-------------------------------------------------------------");
		
		
		
		
		
//		//2-3 delete 작업 연습
		System.out.println("delete 작업 시작");
		SqlSession session2 = sessionFactory.openSession();
		try {
			int cnt = session2.delete("memberTest.deleteMember", "d001");
			if (cnt > 0) {
				System.out.println("delete 작업 성공!");
				session2.commit();
			}else {
				System.out.println("delete 작업 실패!");
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session2.close();
		}
		System.out.println("-------------------------------------------------------------");
		
		
		
		
		
		// 2-4 select 작업 연습
		// 2-4-1) 응답의 결과가 여러개일경우 = select쿼리 실행후, 레코드 개수가 2개 이상일때 => 레코드 단위(객체)를 List에 넣기위해
		//						=> selectList()를 사용한다.
		//						이 메서드는 여러개의 레코드를 VO에 담은 후, 이 VO데이터를 List에 추가해주는 작업을 자동으로 수행한다.
		
		System.out.println("select 연습 시작(select 결과 레코드가 여러개일 경우)");
		
		SqlSession session3 = sessionFactory.openSession(true);	//select는 데이터 변경이 일어나지 않음 => 트랜젝션에 영향을 주지 않으므로 한번 true로 오토커밋 해보기
		try {
			List<MemberVO> memList = session3.selectList("memberTest.selectAllMember");
			
			//List로 select결과를 잘 갖고 왔는지 for-each로 memList출력해보기
			for (MemberVO mv3 : memList) {
				System.out.println("ID : "+mv3.getMemId());
				System.out.println("Name : "+mv3.getMemName());
				System.out.println("Tel : "+mv3.getMemTel());
				System.out.println("Addr : "+mv3.getMemAddr());
				System.out.println("-------------------------------------------------------------");	//한사람의 정보출력(한 레코드마다)이 끝날때마다 구분자로 출력
			}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session3.close();
		}
		System.out.println("-------------------------------------------------------------");
		
		
		
		
		//2-4-2)응답 결과가 1개인 경우
		System.out.println("select 연습시작(응답결과가 1개일 경우");
		SqlSession session4 = sessionFactory.openSession(true);
		
		try {
			MemberVO mv4 = session4.selectOne("memberTest.getMember", "d001");	//mybatis가 selectOne()메서드를 통해 쿼리 실행 후, 가져오는 결과는 MemberVO타입이므로, 
																				//그 결과를 받을 변수 타입은 MemberVO
			
			System.out.println("ID : "+mv4.getMemId());
			System.out.println("Name : "+mv4.getMemName());
			System.out.println("Tel : "+mv4.getMemTel());
			System.out.println("Addr : "+mv4.getMemAddr());
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session4.close();
		}
	}
}

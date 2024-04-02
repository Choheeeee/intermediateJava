package kr.or.ddit.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {

	private static SqlSessionFactory sessionFactory;
	
	static {	//MyBatisUtil이 로딩되는 시점에, SqlSessionFactory객체도 생성하려고
		
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
		
	}
	
	/**
	 * SqlSession 객체를 제공하기 위한 메서드 (SqlSession객체가 필요할때마다 사용할 메서드)
	 * @return SqlSession객체
	 */
	public static SqlSession getInstance() {
		return sessionFactory.openSession();
	}
	
	/**
	 * SqlSession 객체를 제공하기 위한 메서드 (SqlSession객체가 필요할때마다 사용할 메서드)
	 * @param autoCommit 오토커밋 여부 설정
	 * @return SqlSession객체
	 */
	public static SqlSession getInstance(boolean autoCommit) {
		return sessionFactory.openSession(autoCommit);
	}
	
	
	
	
}

package kr.or.ddit.basic;
/*
 	annotation에 대해
 		- 프로그램 소스코드 안에 다른 프로그램을 위한 정보를 미리 약속된 형식으로 포함시킨것. (JDK1.5부터 지원)
 		- 주석처럼 프로그래밍 언어에 영향을 미치지 않으면서도 다른 프로그램에 유용한 정보를 제공함
 	
 	종류 
 		1. 표준 애너테이션
 		2. 메타애너테이션 : 애너테이션을 위한 애너테이션, 즉 에너테이션을 정의할때 사용하는 에너테이션을 말한다.)
 		
 	애너테이션 정의 방법
 		@interface 애너테이션이름 {
 			요소타입 타입요소이름();	//반환값이 있고 매개변수는 없는 추상메서드 형태
 			...
 		}
 	
 	애너테이션 요소의 규칙
 		1. 요소의 타입은 기본형, String, enum, annotation, class만 허용된다.
 		2. ()안에 매개변수를 선언할 수 없다.
 		3. 예외를 선언할 수 없다.
 		4. 요소의 타입에 타입글자를(제네릭) 사용할 수 없다.
 */

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Target(ElementType.METHOD)	//어노테이션 적용 대상을 지정함
@Retention(RetentionPolicy.RUNTIME)	//유지되는 기간을 지정함(디폴트: CLASS) 
//우리가 작성한 코드는 Source파일 ->(컴파일 후) Class파일 -> Runtime 단계 순으로 실행 되는데, Retention을 통해  어느 단계까지 @를 유지할 것인지를 지정할 수 있다.
public @interface PrintAnnotation {

	int id = 100;	//상수 선언 가능. static final int id = 100과 동일
	String value() default "-"; //기본값을 '-'로 지정
	int count() default 20;	//기본값을 20으로 지정
	
}

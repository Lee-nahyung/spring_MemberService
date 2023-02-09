package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //tomcat이라는 웹서버를 내장
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}

/**
 * 빈 등록 방법 2가지
 * 1. 컴포넌트 스캔과 의존관계 설정 -
 -어노테이션으로 @Controller, @Repository 같은 것 이러한 것은 특수화된 컴포넌트다.
 =>주로 정형화된 코드는 컴포넌트 스캔을 사용한다.

 *
 * 2. 자바 코드로 직접 스프링 빈 등록하기
 - 컨트롤러 말고 리파지토리랑 서비스랑 다 컴포넌트 지우고, Autowired지운 후 SpringConfig에 @Configuration와 @Bean 어노테이션으로 관리
 => 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경해야 할 경우는 설정을 통해 스프링 빈으로 등록

 ex)아직 데이터 저장소가 정해지지 않은 상황, 설계 후 저장소만 바꿔치기 할 경우 다른 컨트롤러나 서비스 등의 코드는
 바꾸지 않게 설계할 수 있음.
 */


/** Di 주입 방법 3가지
 * 1. 필드 주입
 * => 변경할 수 있는 방법이 없어 잘 사용하지 않는다.
 *
 * 2.setter 주입
 * => setter을 public으로 열어놔야 한다. 한 번 셋팅이 되면 바꿀 이유가 없는데, public으로 노출이 됨. 잘못바꿀 시 오류 생길 수 있음
 *
 * 3.생성자 주입
 * => 처음 셋팅이되는 시점에 한 번 들어가고 끝. 실행중에 동적으로 변하는 경우가 거의 없음.
 * */

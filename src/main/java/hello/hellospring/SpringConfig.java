package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    //private DataSource dataSource;
    //private final EntityManager em;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);//밑에서 빈에 넣은 멤버 리파지토리를 멤버 서비스에 넣음
    }

    //AOP는 정형화되어있다기 보다는 특별한 경우이기 때문에 Bean으로 등록하는 게 나음
    //그러나 이번에는 컴포넌트 스킨을 사용하기로 함.
    /**@Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    } */
}

/**
    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em) {

        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        //return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}

*/
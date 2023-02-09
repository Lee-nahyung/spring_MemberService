package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
/** 테스트는 서로 의존관계 없이 독립적으로 설계해야 함. 그렇게 하기 위해선 저장소나 공용 데이터들을
 * 지워줘야 함.
 * */
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository() ;
    /**동시에 테스트하면 findALL()이랑 findByName()중 하나 오류가 뜸
     *이미 앞에서 같은 것을 저장했기 때문임. 따라서 한 테스트가 끝나면 저장소를.
    * */


    //클래스가 실행될때마다 끝에 호출, save ->afterEach, findBy000 -> afterEach
    public
    @AfterEach  void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){ //저장 테스트
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        //System.out.println("result =" + (result == member )); //방법1
        //Assertions.assertEquals(member, null); //방법 2 주피터 어썰션, 뭘 출력하지는 않지만 같으면 녹색으로 뜨고 틀리면 알려줌
        //Assertions.assertThat(member).isEqualTo(result); //방법3 다른 어썰션
        assertThat(member).isEqualTo(result); //static import 하여 생략 가능
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get(); //리포지토리에서 네임을 찾아서 가져옴. (없으면 null 감싸서 나옴)
        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }


}

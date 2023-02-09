package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    //실무에서는 이 두 줄을 동시성 문제때문에 다른 것으로 사용함
    private static Map<Long, Member> store = new HashMap<>(); //save를 할 저장소, 키는 회원의 아이디(long) 값은 멤버
    private static long sequence = 0L; //sequence는 아이디 값 증겨시켜서 넣을 애

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //아이디 값을 생성해주고
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) { //store에서 꺼내기
        return Optional.ofNullable(store.get(id)); //아이디가 없으면 null로 반환될 가능성이 있기 떄문에 optional로 감쌈
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); //찾으면 반환되고 없으면 null, optional로 감싸서 반환

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear(); //싹 비우는 것
    }
}

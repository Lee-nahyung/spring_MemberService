package hello.hellospring.controller;

public class MemberForm {
    private String name; //crateMemberFomr에 있는 name에 매칭
/** createMemberFrom.html 중
    <label for="name">이름</label>
    <input type="text" id="name" name="name" placeholder="이름을 입력하세요">
 * */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

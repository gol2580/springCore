package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;

public class MemberApp {

    public static void main(String[] args) {
        //메인메소드에서 구현객체 직접 선택했던 경우
        //MemberService memberService = new MemberServiceImpl();

        //역할 분리 : appconfig를 이용해서 memberservice를 꺼내야 함
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        Member member = new Member(1L,"memberA", Grade.VIP);
        memberService.join(member);

       Member findMem = memberService.findMember(1L);
       System.out.println("new member = "+member);
       System.out.println("find member = "+findMem);
    }
}

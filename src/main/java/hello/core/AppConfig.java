package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    //생성자를 통해 외부에서 의존관계를 주입함
    //-> 구현 객체들은 인터페이스에만의존 + 구현객체를 생성,주입하는 것은 외부에서

    //중복되는 파라미터 생성은 메소드로 뺌
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    public DiscountPolicy DiscountPolicy() {
        //return new FixDiscountPolicy(); : 생성,반환부만 변경하면 구현체 변경가능
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService() {
        System.out.println("call  AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), DiscountPolicy());
    }



}

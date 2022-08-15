package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Locale;
import java.util.Map;

public class MemberApp {

    public static void main(String[] args) {

        //메인메소드에서 구현객체 직접 선택했던 경우
        //MemberService memberService = new MemberServiceImpl();

        //역할 분리 : appconfig를 이용해서 memberservice를 꺼내야 함
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService =
                applicationContext.getBean("memberService",MemberService.class);
        Member member = new Member(1L,"memberA", Grade.VIP);
        memberService.join(member);

       Member findMem = memberService.findMember(1L);
       System.out.println("new member = "+member);
       System.out.println("find member = "+findMem);
    }
}

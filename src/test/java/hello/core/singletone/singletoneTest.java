package hello.core.singletone;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class singletoneTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI컨테이더")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        //1. 조회 : 호출할때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        //2. 조회 : 호출할때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //서로 다른 객체 생성됨 확인
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

    }
    
    @Test
    @DisplayName("싱글톤 패턴을 사용한 객체 사용")
    void singletonServiceTest() {
        singletoneService singletoneService1 = singletoneService.getInstance();
        singletoneService singletoneService2 = singletoneService.getInstance();

        System.out.println("singletoneService1 = " + singletoneService1);
        System.out.println("singletoneService2 = " + singletoneService2);
        //서로 같은 객체!!
        Assertions.assertThat(singletoneService1).isSameAs(singletoneService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        //AppConfig appConfig = new AppConfig();
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        ac.getBean("memberService", MemberService.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //조회 할 때마다 같은 객체를 반환하는지 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //서로 다른 객체 생성됨 확인
        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}

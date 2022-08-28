package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private  final MemberRepository memberRepository;
    private  final DiscountPolicy discountPolicy; //인터페이스에만 의존하도록 변경

    //인터페이스에만 의존함, 구현객체는 생성자를 통해 외부에서 주입함
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        //일단 멤버 찾기
        Member member = memberRepository.findById(memberId); //회원 생성하고 받아서 discountPolicy에 넘김
        int discountPrice = discountPolicy.discount(member,itemPrice); //orderService 입장에선 할인내부정책은 모름! : 단일체계원칙

        return new Order(memberId, itemName,itemPrice,discountPrice); //주문결과 반환
    }

    //테스트용 : 싱글톤여부
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private  final MemberRepository memberRepository = new MemoryMemberRepository();
    private  final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        //일단 멤버 찾기
        Member member = memberRepository.findById(memberId); //회원 생성하고 받아서 discountPolicy에 넘김
        int discountPrice = discountPolicy.discount(member,itemPrice); //orderService 입장에선 할인내부정책은 모름! : 단일체계원칙

        return new Order(memberId, itemName,itemPrice,discountPrice); //주문결과 반환
    }
}

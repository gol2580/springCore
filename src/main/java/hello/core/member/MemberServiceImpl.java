package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private MemberRepository memberRepository;
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    } //AppConfig에서 생성해 할당(주입) : 본 클래스는 인터페이스에만 의존


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

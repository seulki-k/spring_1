package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

  public static void main(String[] args) {
//    AppConfig appConfig = new AppConfig();
//    MemberService memberService = appConfig.memberService();

    //모든 Bean객체를 관리하는 역할.
    // Spring ApplicationContext로 시작
    //AppConfig의 환경설정 갖고 관리한다.
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

    Member memberA = new Member(1L, "memberA", Grade.VIP);

    memberService.join(memberA);

    Member findMember = memberService.findMember(1L);
    System.out.println("memberA = " + memberA.getName());
    System.out.println("findMember = " + findMember.getName());

  }

}

package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextSameBeanTest {

  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(sameBeanConfig.class);

  @Test
  @DisplayName("타입으로 조회 시 중복 오류 발생")
  void findBeanByTypeDuplicate(){
    Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
        () -> ac.getBean(MemberRepository.class));
  }

  @Test
  @DisplayName("같은 타입 2개 시 Bean 이름 지정")
  void findBeanByName(){
    MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
    org.assertj.core.api.Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
  }

  @Test
  @DisplayName("특정 타입 모두 조회")
  void findAllBeanByType(){
    Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
    for (String s : beansOfType.keySet()) {
      System.out.println("key = " + s + "value = " + beansOfType);

    }
    System.out.println("beansOfType = " + beansOfType);
    org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);

  }

  @Configuration
  static class sameBeanConfig {

    @Bean
    public MemberRepository memberRepository1() {
      return new MemoryMemberRepository();
    }

    @Bean
    public MemberRepository memberRepository2() {
      return new MemoryMemberRepository();
    }

  }

}

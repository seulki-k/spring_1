package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextExtendsFindTest {

  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

  @Test
  @DisplayName("부모티압으로 조회 시 자식이 둘 이상있으면 에러 발생")
  void findBeanParentTypeDuplicate(){
    Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
        ()-> ac.getBean(DiscountPolicy.class));
  }

  @Test
  @DisplayName("부모티압으로 조회 시 자식이 둘 이상있으면 자식 이름 지정")
  void findBeanParentTypeBeanName(){
    DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
    org.assertj.core.api.Assertions.assertThat(rateDiscountPolicy)
        .isInstanceOf(RateDiscountPolicy.class);
  }

  @Test
  @DisplayName("특정 하위타입으로 조회")
  void findBeanBySubType() {
    RateDiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);
    org.assertj.core.api.Assertions.assertThat(rateDiscountPolicy)
        .isInstanceOf(RateDiscountPolicy.class);
  }

  @Configuration
  static class TestConfig {

    @Bean
    public DiscountPolicy rateDiscountPolicy() {
      return new RateDiscountPolicy();
    }

    @Bean
    public DiscountPolicy fixDiscountPolicy() {
      return new FixDiscountPolicy();
    }

  }
}

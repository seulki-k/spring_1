package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//ComponentScan 사용 시, @Configuration 이 붙은 설정 정보도 자동으로 스프링 Bean에 등록
// excludeFilters 사용하여 설정 정보는 컴포넌트 스캔에서 제외 시킨다. 다른 강의에서 등록한 설정파일을 사용하지 않기위해서.
@ComponentScan(
    basePackages = "hello.core.member", //
    excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {


}

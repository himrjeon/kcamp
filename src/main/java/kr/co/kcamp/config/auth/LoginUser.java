package kr.co.kcamp.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 어노테이션 생성 위치 지정, 파라미터로 지정했으니 선언된 객체에서만 사용 가능.
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { // 파일을 어노테이션 클래스로 지정. LoginUser라는 이름을 가진 어노테이션 생성되었다고 보면 됨.
}

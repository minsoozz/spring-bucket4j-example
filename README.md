# 처리율 제한기 예제

## 개요

API 요청 처리율을 제한하는 예제입니다.

## 개발 환경

- Java 17
- Gradle 8.1
- Junit 5

## 외부 라이브러리 목록

| Dependency                                         | Version | Purpose                               |
|----------------------------------------------------|---------|---------------------------------------|
| org.springframework.boot:spring-boot-starter       |         | Spring Boot 핵심 스타터 종속성                |
| org.springframework.boot:spring-boot-starter-web   |         | Spring MVC를 사용하여 웹 애플리케이션 개발을 위한 스타터  |
| org.springframework.boot:spring-boot-starter-aop   | 3.1.1   | Spring AOP를 활용한 관점 지향 프로그래밍을 위한 스타터   |
| org.springframework.boot:spring-boot-starter-cache |         | Spring Framework의 캐싱 기능을 사용하기 위한 스타터  |
| com.github.ben-manes.caffeine:caffeine             | 3.1.6   | Spring Cache 통합을 위한 Caffeine 캐싱 라이브러리 |
| com.github.vladimir-bukhtoyarov:bucket4j-core      | 7.6.0   | 요청 제한과 제어를 위한 Bucket4j 핵심 라이브러리       |
| ch.qos.logback:logback-classic                     |         | 로깅을 위한 Logback 클래식 모듈                 |
| org.springframework.boot:spring-boot-starter-test  |         | Spring Boot 애플리케이션을 테스트하기 위한 스타터      |

## API 명세

```
GET /api/v1/products
```

### Success Response

사용가능한 토큰이 존재하는 경우

![image-3.png](src%2Fmain%2Fresources%2Fimages%2Fimage-3.png)

### Fail Response

사용가능한 토큰이 존재하지 않는 경우

![image-1.png](src%2Fmain%2Fresources%2Fimages%2Fimage-1.png)

콘솔 로그

![image-2.png](src%2Fmain%2Fresources%2Fimages%2Fimage-2.png)
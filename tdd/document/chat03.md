

 # 테스트 작성 순서 연습
 ```
 - 구현하기 쉬운 것부터 먼저 테스트
 - 예외 상황을 먼저 테스트
 ```

# 시작이 안될 때는 단언부터 고민
테스트 작성이 막힐 땐, 기능의 결과를 검증하는 코드부터 시작하면 흐름을 잡기 쉽다.
- 예를 들어 만료일 계산 기능의 경우 만료일을 검증하는 코드부터 작성해 보는 것이다.
```java
@DisplayName("만원 납부하면 한달 뒤가 만료일")
@Test
void test1() {
    //처음 작성하는 코드
    assertEquals(기대하는만료일, 실제만료일);    
}
```

java 8 LocalDate 타입을 사용하여 만료일 표현
```java
assertEquals(LocalDate.of(2025, 4, 8), 실제만료일);
```
실제만료일 변수화
```java
LocalDate realExpiryDate = 계산하기
assertEquals(LocalDate.of(2025, 4, 8), realExpiryDate);
```

realExpiryDate 변수를 구하는 코드 작성
```java
LocalDate realExpiryDate = cal.calculateExpirtDate(파라미터);
assertEquals(LocalDate.of(2025, 4, 8), realExpiryDate);
```

cal의 타입과 파라미터 타입
만료일을 계산하는데 납부일과 납부액이 있어야하므로 파라미터에 이 두 값을 전달
```java
ExpiryDateCalculator cal = new ExpiryDateCalculator();
LocalDate realExpiryDate = cal.calculateExpirtDate(LocalDate.of(2025, 4, 8), 10_000);
assertEquals(LocalDate.of(2025, 4, 8), realExpiryDate);
```
이렇게 테스트 코드를 어떻게 작성할지 감을 못잡는다면 검증 코드부터 시작하자.

# 구현이 막히면
막히는 느낌이들면 과감하게 코드를 지우고 미련 없이 다시 시작한다.
다시 진행할땐 다음을 상기한다
- 쉬운 테스트, 예외적인 테스트
- 완급조절
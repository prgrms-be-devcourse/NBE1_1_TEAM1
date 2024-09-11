﻿# NBE1_1_TEAM1

</br>
    
# 역할

- 김도우 : JWT 인증방식 구현
- 류정원 : React, 문서 작업 / 이후 남는 추가 작업 진행
- 최철진 : 기존 등록된 상품 이름, 카테고리, 가격, 설명을 수정/삭제하는 기능 추가
- 한도윤 : 주문 내역 확인 페이지 작성
- 이종원 : JPA로 다중 구현 기술 제공

</br>

# 개발 환경



### 버전 관리 및 협업 도구

- Git: 소스 코드 관리
    - Main 브랜치로부터 각자 Branch를 생성해서 작업한 후 통합
- Notion: 프로젝트 관리 및 문서화
- Slack: 소통


### 개발 도구

- IntelliJ IDEA (IDE)


### 벡엔드 기술 스택

- 프레임워크: Spring Boot 3.3.3
- dependencies
    - spring-boot-starter-jdbc
    - spring-boot-starter-thymeleaf
    - spring-boot-starter-web
    - h2
    - mysql-connector-j
    - lombok
    - spring-boot-starter-test
- 빌드 도구
    - Maven
- JDK
    - Java 17


### 프론트엔드 기술 스택

- React 18.3.1
- Axios 1.7.7
- Bootstrap 5.3.3


### 테스트 환경

- JUnit

</br>

# Structure of Directory

# Naming Convention


### 패키지

- 소문자로 작성

```markdown
com.programmers.mycoffee
```

### 클래스 / 인터페이스

- 클래스, 인터페이스 : UpperCamelCase
- 테스트 클래스 : 클래스명 끝에 Test 추가

```java
public class DefaultOrderServiceTest {}
```

---

### 변수 / 메서드

- 변수명 / 메서드명 : lowerCamelCase
- 메서드명 : 동사 또는 전치사로 시작

```java
public Product createProduct() {
		var product = new Product();
}
```

---

### 상수

- CONSTANT_UPPERCASE, 단어 사이에 언더스코어(_) 사용

```java
public static final int TEST_CONSTANT = 100;
```

---

### 코드 스타일

- 들여쓰기 : 4칸
- 제어문 공백 형식 : `if`, `for`, `while` 등은 공백 후 중괄호 사용
- 조건문/반복문 : 중괄호 {} 필수 사용

```java
if (condition) {
		//logic
}
```

---

### 코드 형식

- 줄 간격 : 논리적으로 다른 기능을 하는 메서드나 변수는 적절히 묶어서 구분
- 코드 정리 단축키 : CTRL + SHIFT + ENTER
    - 코드 정리 단축키를 사용하여 일관된 형식 유지
</br>

# 사용 시나리오

1. 관리자 기능
    - 관리자만 상품 관리 페이지 및 주문관리 페이지 에 접근할 수 있도록 제한한다.
2. 상품 관리 (관리자)
    - 상품 등록
        - 관리자는 상품의 이름, 카테고리, 가격, 설명을 입력하여 새로운 상품을 등록한다.
        - 등록 시 현재 시간을 기준으로 마지막 수정 시간을 기록한다
    - 상품 수정 및 삭제
        - 관리자는 기존에 등록된 상품의 이름, 카테고리, 가격, 설명을 수정할 수 있다
        - 관리자는 상품을 삭제할 수 있다.
4. 주문 기능
    - 상품 목록 표시
        - 사용자는 등록 되어 있는 상품의 목록을 확인할 수 있다
    - 상품 추가
        - 사용자는 원하는 상품의 “추가” 버튼을 눌러 장바구니에 담을 수 있다
        - 상품이 이미 존재할 경우 해당 상품의 수량이 증가한다
    - 상품 제거
        - 사용자는 장바구니에서 상품의 “제거”버튼을 눌러 해당 상품을 삭제할 수 있다.
        - (상품 지우기 or 개수 -1 중 하나로 구현 예정)
    - 주문 정보 입력
        - 사용자는 이메일, 주소, 우편번호 입력란에 주문 정보를 입력한다
    - 총 금액 계산
        - 사용자가 장바구니에 담은 상품들의 개수와 가격을 계산하여 총 금액을 자동으로 보여준다.
    - 상품 목록에 있는 아이템의 추가 버튼을 누를 시 제품명과 개수를 등록해준다
        - 상품이 이미 존재할 경우 해당 상품의 개수를 늘려준다
5. 결제 기능
    - 결제 처리
        - 사용자가 “결제하기” 버튼을 누를 시 입력된 주문 정보를 저장한다
        - 결제 완료 안내를 한 후 결제 완료 페이지로 이동한다.
    - 결제 완료
        - 사용자는 주문한 상품 목록, 개수, 총 금액 등의 정보를 확인할 수 있다
        - “돌아가기” 버튼을 누를 시 첫 화면으로 다시 이동할 수 있다.
</br>

# 요구사항 명세서

| ID   | 구분    | 화면명            | 요구사항명           | 요구사항 내용                                                                                               |
|------|---------|-------------------|----------------------|------------------------------------------------------------------------------------------------------------|
| MC-AD-0001  | 관리자 | 관리자 로그인</br> 페이지 | 관리자 로그인 기능      | 관리자 로그인 시 상품 관리 페이지 및 주문 관리 페이지에</br> 접근할 수 있어야 하며, </br>인증되지 않은 사용자는 로그인 페이지로 리다이렉트된다. |
| MC-AD-0002  | 관리자 | 상품 관리</br> 페이지     | 상품 등록 기능         | 관리자는 상품의 이름, 카테고리, 가격, 설명을 입력하여</br> 상품을 등록할 수 있으며,</br> 설명을 제외한 필드는 필수 입력값이다.                   |
| MC-AD-0003  | 관리자 | 상품 관리 </br>페이지     | 상품 수정/삭제 기능  | 관리자는 등록된 상품 정보를 수정하거나 삭제할 수 있어야 하며,</br> 삭제 시 사용자의 화면에 표시되지 않도록 한다.                          |
| MC-AD-0004  | 관리자 | 주문 관리 </br>페이지     | 주문 목록 보기         | 사용자의 주문 정보들을 보여준다.                                                                         |
| MC-UR-0001  | 사용자 | 주문 페이지         | 상품 목록 보기         | 사용자는 상품 목록을 확인할 수 있으며,</br> 각 상품의 이름, 카테고리, 가격 정보가 표시된다.                                                                |
| MC-UR-0002  | 사용자 | 주문 페이지         | 상품 추가 기능         | 사용자가 상품 목록의 "추가" 버튼을 클릭하면</br> 해당 상품이 장바구니에 추가되며, </br>이미 장바구니에 있는 경우 수량이 증가한다.                  |
| MC-UR-0003  | 사용자 | 주문 페이지         | 상품 제거 기능         | 사용자가 "제거" 버튼을 클릭하면 </br>해당 상품이 장바구니에서 삭제된다. <br> Case 1: 현재 수량에서 -1을 하고 0이 되면 목록에서 지운다. <br> Case 2: 장바구니에서 해당 상품을 완전히 제거한다. |
| MC-UR-0004  | 사용자 | 주문 페이지         | 주문 정보 입력         | 사용자는 이메일, 주소, 우편번호를 모든 필드를</br> 필수적으로 작성해야 한다.                                                                                  |
| MC-UR-0005  | 사용자 | 주문 페이지         | 총 금액 계산           | 장바구니에 담긴 상품의 개수와 가격을 기반으로</br> 총 금액을 자동으로 계산하여 동적으로 표시한다.                                                          |
| MC-UR-0006  | 사용자 | 주문 페이지         | 결제 처리 기능         | 사용자가 "결제하기" 버튼을 클릭하면</br> 주문 정보가 저장되고 결제가 완료된다. </br>결제 완료 페이지로 이동한다.                                               |
| MC-UR-0007  | 사용자 | 결제 완료</br> 페이지     | 결제 정보 제공         | 사용자에게 해당 결제에 대한 주문 내역을 보여주며,</br> "돌아가기" 버튼을 클릭하면 첫 화면으로 돌아갈 수 있다.                                              |


</br>

# 시스템 구성도
![image](https://github.com/user-attachments/assets/d7552a7a-2db3-4adb-ab9a-730598cabd6f)

</br></br>

# ERD

![image](https://github.com/user-attachments/assets/faecad18-b04c-45b7-b213-b364a4cd0e94)
</br></br>


# 화면 설계서

## 관리자 상품 관리 페이지

### 상품 추가
![image](https://github.com/user-attachments/assets/112c6ab9-6c25-4754-b6e7-90bb483d4cf9)

### 상품 수정
![image](https://github.com/user-attachments/assets/65693e32-bc62-46b7-b4e3-090986619d75)

## 사용자 주문 페이지
![image](https://github.com/user-attachments/assets/5b4c367c-a4c2-435a-8ceb-b41457b897ec)

## 사용자 결제 완료 페이지
![image](https://github.com/user-attachments/assets/23e6db58-ebe6-48ee-829e-d8ec6ea47c3a)


</br></br>


# API 명세서

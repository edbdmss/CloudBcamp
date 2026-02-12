## MVC 구조를 적용한 Dept 테이블 기반 JDBC 코드

**MVC 패턴: Model + View + Controller** \
    - M : 핵심 로직\
    - V : 출력 로직\
    - C : View와 Model의 연결로

```
구조: 

JDBC_Company
└─ src
    └─ dept
         ├─ controller
         │   └─ DeptController.java
         ├─ service
         │   └─ DeptService.java
         ├─ dao
         │   └─ DeptDAO.java
         ├─ dto
         │   └─ DeptDto.java
         ├─ view
         │   └─ DeptView.java
         └─ util
             └─ DBUtil.java

```

<br>

전체적인 흐름은 다음과 같습니다.

```

    View

    ↓ (사용자 입력)

    Controller

    ↓ (요청)

    Service   ⭐ 핵심

    ↓ (호출)

    DAO

    ↓

    DB
```

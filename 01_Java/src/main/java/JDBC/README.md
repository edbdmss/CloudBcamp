# JDBC (Java Database Connectivity)

- DEPT 테이블을 사용하여 JAVA로 접속하기
- 핵심 인터페이스: Connection, Statement, ResultSet
- 작동 원리: JDMC driver가 데이터베이스와 자바 애플리케이션 간 중간 다리 역할을 수행함.


```
프로젝트 구조: 

JDBC
└─ src	
    ├─ dao
	│   └─ DeptDao.java
	├─ dto
	│   └─ DeptDto.java
	└─ test
    │	└─ DeptTest.java
    └─  util
        └─ DBUtil.java

```

<br>

- DeptDao: DB 접근 
    - SQL 작성 및 실행
    - ResultSet -> DTO 변환

- DeptDto: 데이터 전달 
    - dept 테이블과 1:1 매핑

- DeptTest: main 메서드를 통한 DAO 기능 단위 테스트

- Util: properties 객체 불러오기 및 보안 데이터 전달
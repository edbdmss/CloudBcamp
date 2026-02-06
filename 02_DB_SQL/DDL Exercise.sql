-- [시나리오 1] 신규 부서 및 사원 관리
-- 1. 테이블 생성: dept_test (deptno 숫자형 PK, dname 문자형 20자 NOT NULL, loc 문자형 20자)

create table dept_test (
deptno int primary key,
dname varchar(20) not null,
loc varchar(20)
);

-- 2. 데이터 입력: 50번 부서, 'IT', 'SEOUL' 추가
insert into dept_test values (50, 'IT', 'SEOUL');

-- 3. 데이터 수정: IT 부서의 위치(loc)를 'PANGYO'로 변경
SET SQL_SAFE_UPDATES = 0;
update dept_test set loc = 'PANGYO'
where dname = 'IT';

-- 4. 컬럼 추가: dept_test 테이블에 budget(예산) 컬럼(숫자형) 추가
alter table dept_test add budget int;

-- 5. 데이터 삭제: 부서 번호가 50인 행 삭제
delete from dept_test where deptno = 50;


-- 
-- [시나리오 2] 인사 시스템 데이터 정제 (응용)
-- 1. 복사 생성: emp 테이블에서 부서번호 20, 30번인 사원의 empno, ename, sal, deptno로 emp_senior 생성
create table emp_senior as (select empno, ename, sal, deptno from emp);

-- 2. 제약 조건 추가: emp_senior 테이블의 empno 컬럼을 PK로 설정
alter table emp_senior add constraint PK primary key (empno);

-- 3. 일괄 수정: 부서번호가 20번인 사원들의 급여를 15% 인상
update emp_senior set sal = sal * 1.15
where deptno = 20;


-- 4. 조건 삭제: 급여(sal)가 2000 미만인 사원 데이터 삭제
delete from emp_senior where sal < 2000;

-- 5. 구조 유지 및 데이터 전체 삭제: 테이블 구조는 남기고 모든 데이터만 삭제
delete from emp_senior;



-- 
-- [시나리오 3] 서비스 회원 관리 시스템
-- 1. 테이블 생성: users 
--    (id: 숫자형, PK, 자동증가 / username: 문자형 20자, 중복불가, 필수 / 
--     email: 문자형 50자, 중복불가 / reg_date: 날짜형, 기본값 현재시간(CURRENT_TIMESTAMP))
create table users (
	id int primary key auto_increment,
    username varchar(20) not null unique,
    email varchar(50) unique,
    reg_date datetime default CURRENT_TIMESTAMP
    );

-- 2. 데이터 입력: 'admin', 'admin@test.com' / 'guest', 'guest@test.com' 두 개 행 추가
--    (id와 reg_date는 자동 입력되도록 설정)

-- 3. 컬럼 수정: 유저의 상태를 나타내는 status 컬럼(문자형 10자, 기본값 'ACTIVE') 추가

-- 4. 제약 조건 추가: email 컬럼이 NULL이 되지 않도록(NOT NULL) 제약 조건 수정

-- 5. 데이터 수정: 'guest' 사용자의 상태(status)를 'INACTIVE'로 변경

-- 6. 데이터 삭제: 가입일(reg_date)이 오늘 이전인 데이터 중 상태가 'INACTIVE'인 사용자 삭제

-- 7. 테이블 삭제: users 테이블을 데이터베이스에서 완전히 제거

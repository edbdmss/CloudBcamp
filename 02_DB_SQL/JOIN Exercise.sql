-- JOIN

-- 1. dept table의 구조 검색
desc dept;
desc emp;
desc salgrade;



-- 2. SMITH 의 이름ename, 사번empno, 근무지역(부서위치)loc 정보를 검색
select e.ename, e.empno, d.loc, d.deptno
from emp as e
inner join dept as d
on e.deptno = d.deptno
where e.ename = 'SMITH';


-- 3. deptno가 동일한 모든 데이터 검색
-- emp & dept
select *
from emp e 
join dept d
on e.deptno = d.deptno;


-- 4. 2+3 번 항목 결합해서 SMITH에 대한 모든 정보(ename, empno, sal, comm, deptno, loc) 검색하기
select *
from emp as e
inner join dept as d
on e.deptno = d.deptno
where e.ename = 'SMITH';


-- 5.  SMITH에 대한 이름(ename)과 부서번호(deptno), 부서명(dept의 dname) 검색하기
select e.ename, e.deptno, d.dname
from emp as e
inner join dept as d
on e.deptno = d.deptno
where e.ename = 'SMITH';


-- 6. 조인을 사용해서 뉴욕에 근무하는 사원의 이름과 급여를 검색 
-- loc='NEW YORK', ename, sal
select d.loc, e.ename, e.sal
from emp as e
inner join dept as d
on e.deptno = d.deptno
where d.loc = 'NEW YORK';


-- 7. 조인 사용해서 ACCOUNTING 부서(dname)에 소속된 사원의 이름과 입사일 검색
select e.ename, e.hiredate, d.dname
from emp as e
inner join dept as d
on e.deptno = d.deptno
where d.dname = 'ACCOUNTING';



-- 8. 직급이 MANAGER인 사원의 이름, 부서명 검색
select e.ename, d.dname, job
from emp as e
inner join dept as d
on e.deptno = d.deptno
where e.job = 'MANAGER';


-- *** Not - Equi 조인 ***
-- 9. 사원의 급여가 몇등급인지 검색
-- between ~ and
select e.empno, e.sal, s.grade
from emp as e
inner join salgrade as s
on e.sal between s.losal and s.hisal;



-- ? 등급이 3등급인 사원들의 이름과 급여 검색
select e.empno, e.sal, s.grade
from emp as e
inner join salgrade as s
on e.sal between s.losal and s.hisal
where s.grade = 3;



-- *** Outer 조인 ***
/*emp를 두개의 table로 사용 
1. 별칭 e : employee 직원 table
2. 별칭 m : manager table 
*/

-- 11. SMITH 직원의 매니저 이름 검색
/* manager table의 ename 검색
   employee table에 ename='SMITH'
	- SMITH라는 이름으로 상사의 사번 검색
	-> 상사의 사번으로 manager의 사번으로 매니저 이름 검색
*/
select *
from emp as e inner join emp as m
on e.mgr = m.empno
where e.ename = 'SMITH';

	
-- ? SMITH 직원의 매니저의 부서번호 검색, 근무 부서 위치 뭉 
select m.deptno, d.loc
from emp as e 
inner join emp m 
inner join dept d
on e.mgr = m.empno and m.deptno = d.deptno
where e.ename = 'SMITH';


-- ? 12. 매니저가 KING인 사원들의 이름과 직급 검색
select e.ename, e.job, m.ename
from emp e 
inner join emp m on e.mgr = m.empno
where m.ename = 'KING';


-- ? 13. SMITH와 동일한 근무지에서 근무하는 사원의 이름 검색 + 근무지 출력
select e.ename, d.loc, m.deptno
from emp e 
inner join dept d inner join emp m
on e.deptno = d.deptno and m.deptno = d.deptno
where m.ename = 'SMITH';



-- *** 4. outer join ***
-- 14. 모든 사원명, 매니저 명 검색, 단 매니저가 없는 사원도 검색되어야 함
-- null값을 포함한 검색도 가능
select e.ename, m.ename
from emp e
left outer join emp m
on e.mgr = m.empno;



-- 15. 모든 직원명, 부서번호, 부서명 검색
-- 부서 테이블의 40번 부서와 조인할 사원 테이블의 부서 번호가 없지만, outer join이용해서 40번 부서의 부서 이름도 검색하기 
select *
from dept as d 
left outer join emp as e
on d.deptno = e.deptno;



-- ANSI Join vs Oracle Join
/*
1. ANSI JOIN 
SQL 표준(ANSI SQL) 에서 정의한 공식 조인 문법
SELECT *
FROM A
JOIN B
ON A.col = B.col;

2. Oracle JOIN
Oracle에서 사용하던 비표준 조인 문법
FROM 절에 테이블 나열
WHERE 절에서 조인 조건 작성
외부 조인은 (+) 기호 사용

SELECT *
FROM A, B
WHERE A.col = B.col;

*/

-- Natural Join (자연 조인)
-- 두 테이블에서 이름이 같은 모든 컬럼을 자동으로 조인

-- Cross Join (카테시안 곱)
-- 조인 조건 없이 모든 경우의 수

-- 중복 제거
-- ex. 사원이 배정된 부서가 총 몇 개인지 조회
select count(distinct d.deptno)
from dept d
inner join emp e
on d.deptno = e.deptno;



-- 추가 실습
-- 매니저보다 먼저 입사한 사원 목록 조회
-- 자신의 매니저보다 입사일(hiredate)이 빠른 사원의 이름, 입사일, 매니저 이름, 매니저 입사일을 조회
select distinct e.ename, e.hiredate, m.ename, m.hiredate
from emp e  
left outer join emp m
on e.mgr = m.mgr
where e.hiredate < m.hiredate;


-- 사원이 한 명도 없는 부서 정보 조회
-- 사원이 배정되지 않은 부서의 부서번호, 부서명, 위치를 조회
select d.deptno, d.dname, d.loc
from dept d
left outer join emp e
on d.deptno = e.deptno
where e.empno is null;


-- 같은 부서에 근무하는 동료 사원 쌍 찾기(단, 본인 제외 및 중복 쌍 제거)
select e.ename, p.ename, e.deptno
from emp e 
join emp p
on e.deptno = p.deptno and e.ename != p.ename;

/*
예시 출력

'SMITH', 'JONES', '20'
'SMITH', 'SCOTT', '20'
'SMITH', 'ADAMS', '20'
'SMITH', 'FORD', '20'
'ALLEN', 'WARD', '30'
'ALLEN', 'MARTIN', '30'
'ALLEN', 'BLAKE', '30'
'ALLEN', 'TURNER', '30'
'ALLEN', 'JAMES', '30'
...
*/

-- 사원별 관리자 및 관리자의 관리자(차상위자) 조회
-- 사원명, 직속 상사명, 차상위 상사명을 함께 조회. 상사가 없으면 'None'으로 표시
select e.ename, ifnull(e2.ename,'None'), ifnull(e3.ename, 'None')
from emp e 
left outer join emp e2 
on e.mgr = e2.empno
left outer join emp e3 
on e2.mgr = e3.empno;



-- 'CHICAGO'에서 근무하는 사원들의 평균 급여 등급
-- 시카고에서 근무하는 사원들의 평균 급여가 아닌, 급여 등급의 평균
select avg(s.grade)
from emp e 
join dept d on e.deptno = d.deptno 
join salgrade s on e.sal between s.losal and s.hisal
where d.loc = 'CHICAGO' ;


-- 자신의 급여 등급보다 높은 등급의 상사를 둔 사원
-- 사원의 급여 등급이 상사의 급여 등급보다 낮은 사원들의 이름과 등급, 상사의 이름과 등급을 조회
select e.ename, s.grade, e2.ename, s2.grade
from emp e
join salgrade s on e.sal between s.losal and s.hisal
join emp e2 on e.mgr = e2.empno 
join salgrade s2 on e2.sal between s2.losal and s2.hisal
where s.grade < s2.grade;



-- 부서별 급여 합계와 전체 합계를 동시에 조회 (UNION)
-- 각 부서별 이름과 급여 합계를 구하고, 마지막 행에 모든 부서의 총합계를 추가
select dname, sum(e.sal)
from emp e 
inner join dept d 
on e.deptno = d.deptno
group by d.dname
union all
select 'total', sum(sal)
from emp;



-- 'SCOTT'보다 많은 급여를 받는 사원의 이름, 급여, 부서명을 조회
select e.ename, e.sal, d.dname
from emp e
inner join dept d 
on e.deptno = d.deptno
inner join emp employee 
on employee.ename = 'SCOTT'
where e.sal > employee.sal;


-- 참고: 'SCOTT' 테이블이 모든 직원 옆에 붙음.
select *
from emp e
inner join dept d 
on e.deptno = d.deptno
inner join emp employee 
on employee.ename = 'SCOTT';


-- 사원이 한 명도 없는 부서 조회 (LEFT JOIN)
select *
from dept d
left outer join emp e
on d.deptno = e.deptno
where e.empno is null;



-- 급여 등급(SALGRADE)이 매칭되지 않는 사원 조회(LEFT JOIN)
select e.ename, e.sal, s.grade
from emp e
left join salgrade s 
on e.sal between s.losal and s.hisal
where s.grade is null;
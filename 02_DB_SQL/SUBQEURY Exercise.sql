-- Subquery 
-- 1. SMITH라는 직원 부서명 검색
SELECT e.ename, d.dname
FROM emp e, dept d
WHERE e.deptno = d.deptno 
  AND e.ename = 'SMITH';
  
  
-- 서브쿼리
select e.deptno
from emp e
where e.ename = 'smith';

select d.dname
from dept d
where d.deptno = 20;


-- 2. SMITH와 동일한 직급(job)을 가진 사원들 검색(SMITH 포함)
select *
from emp c
where c.job = (select s.job 
				from emp s
                where s.ename = 'SMITH');
                
                
-- 3. SMITH와 급여가 동일하거나 더 많은(>=) 사원명과 급여 검색
select e.ename, e.sal
from emp e
where e.sal >= (select e2.sal
				from emp e2
                where e2.ename = 'SMITH');


-- 4. DALLAS에 근무하는 사원의 이름, 부서 번호 검색
select e.ename, e.deptno
from emp e 
where e.deptno = (select d.deptno
				from dept d
                where d.loc = 'DALLAS');
			

-- 5. 평균 급여보다 더 많이 받는(>) 사원만 검색
select e.empno, e.ename, e.sal, (SELECT AVG(sal) 
								 FROM emp) AS 평균급여
from emp e
where e.sal > (select avg(sal)
				from emp e2);


-- 다중행 서브 쿼리(sub query의 결과값이 하나 이상)
-- 6.급여가 3000이상 사원이 소속된 부서에 속한 사원이름, 급여 검색
-- 급여가 3000이상 사원의 부서 번호
-- in 연산자 안에 있는 서브쿼리

select e.ename, e.sal
from emp e
where e.deptno in (select e2.deptno
				   from emp e2
                   where e2.sal >= 3000);

-- 7. in 연산자를 이용하여 부서별로 가장 급여를 많이 받는 사원의 정보(사번, 사원명, 급여, 부서번호) 검색
-- 사번, 사원명, 급여, 부서번호 
select e.empno, e.ename, e.sal, e.deptno
from emp e
where (e.deptno, e.sal) in (select e2.deptno, max(e2.sal)
					from emp e2
                    group by e2.deptno)
order by e.deptno desc;
                    
                    
-- ? 부서별로 가장 급여를 많이 받는 사원
	
    

-- ? 결과를 부서번호 내림차순 정렬


-- 8. 직급(job)이 MANAGER인 사람이 속한 부서의 부서 번호와 부서명(dname)과 지역검색(loc)
select d.dname, d.loc
from dept d
where d.deptno in (select e.deptno
					from emp e
                    where e.job = 'MANAGER');


-- 실습
-- 모든 사원의 이름, 급여, 그리고 회사 전체 평균 급여를 나란히 출력
-- 일반 컬럼과 집계함수를 같이 쓸 수 없음 --
select ename, sal, (select avg(sal) from emp)
from emp;


-- 사원 이름과 그 사원이 속한 부서의 이름을 출력
select e.ename, e.deptno, d.dname
from emp e 
inner join dept d on e.deptno = d.deptno;


-- 사원명과 해당 사원 부서의 '최대 급여'를 함께 출력   -- 밖의 e를 쓸 수 있?
select e.ename, e.deptno, (select max(sal)
						   from emp e2 
                           where e2.deptno = e.deptno)
from emp e;


-- '급여 등급(SALGRADE)' 테이블을 참고하여 사원의 이름과 등급(GRADE) 출력
select e.ename, s.grade
from emp e 
inner join salgrade s on e.sal between s.losal and s.hisal;


-- 부서별 평균 급여 테이블을 먼저 만들고 (인라인 뷰), 이를 부서 테이블과 조인하여 부서명과 평균 급여를 출력
select d.dname, a.avg `평균 급여`
from (select deptno, avg(sal) as avg      -- avg 별칭 써야함.
		from emp e
        group by e.deptno) a
        inner join dept d on d.deptno = a.deptno;



-- 81년도에 입사한 사원들 중에서만 급여가 2000 이상인 사람 출력
select e.ename 
from emp e
WHERE e.hiredate >= '1981-01-01' AND e.hiredate <  '1982-01-01'
and e.sal >= 2000;
		

-- 각 부서에서 가장 먼저 입사한 사원의 정보를 출력

        

-- 사원 정보에 ename과 더불어 부서 위치(LOC) 출력

-- 부서별 인원수와 평균 급여를 가진 가상 테이블을 만들고, 인원수가 5명 이상인 부서만 조회

-- 직급이 'MANAGER'인 사원들이 속한 부서의 모든 사원을 출력

-- 30번 부서의 그 누구보다도 급여를 많이 받는 사원 출력

-- 부하 직원이 한 명이라도 있는 '관리자' 사원들의 이름을 출력   - 실무에서 유용



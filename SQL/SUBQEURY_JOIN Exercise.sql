-- 01. [단일행 서브쿼리] 'Bull'이라는 성(last_name)을 가진 직원보다 급여가 많은 직원의 이름과 급여를 구하세요.
-- 사용 테이블: employees
-- 사용 컬럼: first_name, last_name, salary
select e.first_name, e.last_name, e.salary
from employees e
where e.salary > (select salary
			   from employees 
               where last_name = 'Bull') ;



-- 02. [다중행 서브쿼리] 'IT' 부서에서 근무하는 모든 직원의 이름(first_name, last_name)을 구하세요.
-- 사용 테이블: employees, departments
-- 사용 컬럼: first_name, last_name, department_id, department_name
select concat(e.first_name, e.last_name)
from employees e
where e.department_id in (select d.department_id
						  from departments d
						  where d.department_name = 'IT');


-- 03. [다중 열 서브쿼리] 각 직무(job_id)별 최저 급여(min_salary)를 받는 직원의 이름과 급여를 구하세요.
-- 사용 테이블: employees, jobs
-- 사용 컬럼: job_id, salary, min_salary
SELECT e.first_name, e.last_name, e.salary, e.job_id
FROM employees e
WHERE (e.job_id, e.salary) IN (
    SELECT job_id, min_salary
    FROM jobs
);   



-- 04. [중첩 서브쿼리] 미국(US) 내 위치한 부서에서 근무하며, 매니저가 있는 직원의 이름을 구하세요.
-- 사용 테이블: employees, departments, locations
-- 사용 컬럼: first_name, country_id, location_id, manager_id
select distinct e.first_name
from departments d, employees e
where d.location_id in (select l.location_id
					   from locations l
                       where l.country_id = 'US')
and e.manager_id is not null
and e.department_id = d.department_id;


-- 05. [상관 서브쿼리] 본인이 속한 부서의 평균 급여보다 많이 받는 직원의 ID, 이름, 급여를 구하세요.
-- 사용 테이블: employees (m: 메인, s: 서브)
-- 사용 컬럼: employee_id, first_name, salary, department_id
-- + 평균 급여도 함께 출력
select m.employee_id, concat(m.first_name, m.last_name) as 직원명, m.salary as 급여,  
					  (select avg(salary) 
                       from employees
                       where department_id = m.department_id) `평균 급여`, m.department_id
from employees m
where m.salary >= (select avg(s.salary)
				   from employees s
                   WHERE s.department_id = m.department_id);
                   

-- 06. [NOT IN & NULL 처리] 매니저(관리자)가 아닌 모든 직원의 이름을 구하세요.
-- 사용 테이블: employees
-- 사용 컬럼: employee_id, manager_id
-- *주의: 서브쿼리 결과에 NULL이 있으면 결과가 나오지 않으므로 IS NOT NULL 조건이 필수입니다.
select concat(e.first_name, e.last_name) as name
from employees e
where e.employee_id not in (select manager_id
							from employees 
                            where manager_id is not null);

-- 실무에서는 NOT EXISTS를 더 선호
SELECT CONCAT(e.first_name, ' ', e.last_name) as name
FROM employees e
WHERE NOT EXISTS (
    SELECT 1
    FROM employees m
    WHERE m.manager_id = e.employee_id
);


-- 07. [스칼라 서브쿼리] 모든 직원의 ID, 이름, 그리고 해당 직원이 속한 부서명을 출력하세요.
-- 사용 테이블: employees, departments
-- 사용 컬럼: employee_id, first_name, department_name, department_id
select e.employee_id, e.first_name, e.department_id, (select d.department_name
													  from departments d
                                                      where e.department_id = d.department_id) `부서명`
from employees e;



-- 08. [EXISTS 활용] 직원이 단 한 명도 근무하지 않는 부서의 ID와 이름을 구하세요.
-- 사용 테이블: departments, employees
-- 사용 컬럼: department_id, department_name
select d.department_id, d.department_name
from departments d
where not exists (select 1
				  from employees e
                  where e.department_id = d.department_id);



-- 09. [INNER JOIN] 런던(London) 시에서 근무하는 직원의 이름, 직무(job_id), 부서명을 구하세요.
-- 사용 테이블: employees, departments, locations
-- 사용 컬럼: first_name, job_id, department_name, city
select e.first_name, e.job_id `직무` , l.city, d.department_name `부서명`
from employees e
join departments d on e.department_id = d.department_id
join locations l on d.location_id = l.location_id
where l.city = 'London';



-- 10. [SELF JOIN] 직원의 이름과 그 직원을 관리하는 매니저의 이름을 함께 출력하세요.
-- 사용 테이블: employees (e: 사원, m: 매니저)
-- 사용 컬럼: first_name, employee_id, manager_id
select e.first_name `직원명` , m.first_name `매니저명`
from employees e
join employees m 
on e.manager_id = m.employee_id;



-- 11. [SELF JOIN & 날짜 비교] 'Jones'라는 성을 가진 직원보다 나중에 입사한 직원의 이름과 입사일을 구하세요.
-- 사용 테이블: employees (e: 대상자, j: Jones)
-- 사용 컬럼: first_name, last_name, hire_date
select concat(e.first_name, e.last_name), e.hire_date, j.hire_date
from employees e 
join employees j
on j.last_name = 'Jones'
where j.hire_date < e.hire_date;


-- 12. [JOIN & GROUP BY] 부서명과 해당 부서에 근무하는 직원 수를 출력하세요. (직원이 없는 부서는 제외)
-- 사용 테이블: employees, departments
-- 사용 컬럼: department_name, department_id
select d.department_name `부서명`, count(e.employee_id) `직원 수`
from employees e
join departments d 
on e.department_id = d.department_id
group by d.department_id;


-- 13. [다중 JOIN & 계산] 부서명, 해당 부서 매니저의 이름, 그리고 부서가 위치한 도시를 출력하세요.
-- 사용 테이블: departments, employees, locations
-- 사용 컬럼: department_name, first_name, city
select d.department_name `부서명`, e.first_name `매니저명`, l.city `도시명`
from departments d
join locations l on l.location_id = d.location_id
join employees e on e.department_id = d.department_id
where d.manager_id = e.employee_id;



-- 14. [JOIN & 집계] 각 직무 타이틀(job_title)별 평균 급여를 구하고, 평균 급여가 높은 순으로 정렬하세요.
-- 사용 테이블: jobs, employees
-- 사용 컬럼: job_title, salary
select job_title, avg(e.salary) `평균 급여`
from jobs j
join employees e on j.job_id = e.job_id
group by j.job_title
order by `평균 급여` desc;

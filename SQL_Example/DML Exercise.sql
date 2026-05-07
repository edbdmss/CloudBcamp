use hr;

-- 01. 별칭 "First Name", "Last Name"을 사용하여 이름(first_name, last_name)을 표시하는 쿼리를 작성하세요.
select first_name as 'First Name', last_name as 'Last Name'
from employees;


-- 02. 직원 테이블에서 고유한 부서 ID를 가져오는 쿼리를 작성하세요.
select employee_id
from employees;


-- 03. 이름을 기준으로 모든 직원의 세부 정보를 내림차순(first_name, last_name)으로 가져오는 쿼리를 작성하세요.
select *
from employees
order by first_name desc, last_name desc;


-- 04. 모든 직원의 이름(first_name, last_name), 급여, PF(PF는 급여의 15%로 계산)를 가져오는 쿼리를 작성하세요.
select first_name, last_name, salary, (salary * 0.85) as 'PF' 
from employees;


-- 05. 사원 ID, 이름(first_name, last_name), 급여를 급여 오름차순으로 가져오는 쿼리를 작성하세요.
select employee_id, first_name, last_name, salary
from employees
order by salary;


-- 06. 직원에게 지급될 총 급여를 구하는 쿼리를 작성하세요.
select sum(salary) as '총급여'
from employees;


-- 07. 직원 테이블에서 최대 급여와 최소 급여를 구하는 쿼리를 작성하세요.
select max(salary), min(salary)
from employees;


-- 08. 직원 테이블에서 평균 급여와 직원 수를 가져오는 쿼리를 작성하세요.
select avg(salary) as '평균 급여', count(*) as '직원 수'
from employees;


-- 09. 회사에 근무하는 직원 수를 구하는 쿼리를 작성하세요.
select count(distinct employee_id) as '직원 수'
from employees;

-- 10. 직원 테이블에서 사용 가능한 직위 개수를 가져오는 쿼리를 작성하세요.
select count(distinct job_id) as '직위 개수'
from employees;


-- 11. 모든 직원의 이름 중 처음 세 글자를 구하는 쿼리를 작성하세요.
select substr(first_name,1,3)
from employees;


-- 12. 직원 테이블에서 모든 직원의 이름을 "firstname lastname" 형태로 가져오는 쿼리를 작성하세요.
select first_name as 'firstname', last_name as 'lastname'
from employees;


-- 13. 테이블 양쪽의 공백을 제거한 후 모든 직원의 이름을 가져오는 쿼리를 작성하세요.
select trim(concat(first_name, last_name)) as '직원 이름'
from employees;


-- 14. 직원 테이블에서 직원 이름(first_name, last_name)의 길이를 가져오는 쿼리를 작성하세요.
select length(trim(concat(first_name, last_name))) 
from employees;


-- 15. 모든 직원의 월급을 소수점 이하 2자리로 구하는 쿼리를 작성하세요.
select round(salary,2)
from employees;


-- 16. 급여가 $10,000 - $15,000 범위에 속하지 않는 모든 직원의 이름과 급여를 표시하는 쿼리를 작성하세요.
select concat(first_name, last_name), salary
from employees
where salary < 10000 or salary > 15000;


-- 17. 30번, 100번 부서에 소속된 모든 사원의 이름과 부서 ID를 오름차순으로 출력하는 쿼리를 작성하세요.
select concat(first_name, last_name), department_id
from employees
where department_id in (30, 100);


-- 18. 급여가 $10,000 - $15,000 범위에 있지 않고 부서 30 또는 100에 있는 모든 직원의 이름과 급여를 표시하는 쿼리를 작성하세요.
select concat(first_name, last_name), salary
from employees
where (salary < 10000 or salary > 15000) and department_id in (30, 100);


-- 19. 1987년에 입사한 모든 직원의 이름과 입사 날짜를 표시하는 쿼리를 작성하세요.
select concat(first_name, last_name), hire_date
from employees
where date_format(hire_date, '%Y') = '1987';


-- 20. 이름에 "b"와 "c"가 모두 포함된 모든 직원의 이름을 표시하는 쿼리를 작성하세요.
select concat(first_name, last_name) as '직원 이름'
from employees
WHERE CONCAT(first_name, last_name) LIKE '%b%' AND CONCAT(first_name, last_name) LIKE '%c%';


-- 21. 성이 'JONES', 'BLAKE', 'SCOTT', 'KING', 'FORD'인 직원의 모든 레코드를 선택하는 쿼리를 작성하세요.
select *
from employees
where last_name in ('JONES', 'BLAKE', 'SCOTT', 'KING', 'FORD');


-- 22. 이름이 정확히 6자인 직원의 성을 표시하는 쿼리를 작성하세요.
select first_name
from employees
where length(concat(first_name, last_name)) = 6;


-- 23. 세 번째 문자가 'e'인 직원의 성을 표시하는 쿼리를 작성하세요.
select first_name
from employees
-- where substr(first_name, 3, 1) = 'e';
where first_name like '__e%';


-- 24. 직원 테이블에서 사용 가능한 직무/직위를 표시하는 쿼리를 작성하세요.
select distinct job_id
from employees;


-- 25. 모든 사원의 이름, 성, 급여, PF(급여의 15%)를 출력하는 쿼리를 작성하세요.
select last_name, first_name, salary, (salary * 0.85) as 'PF' 
from employees;


-- 26. 최대 급여가 $4000 이상인 직원의 직업 ID와 최대 급여를 가져오는 쿼리를 작성하세요.
select job_id, max(salary)
from employees
group by job_id
having max(salary) >= 4000;


-- 27. 직원이 10명 이상인 모든 부서의 평균 급여를 구하는 쿼리를 작성하세요
select department_id, avg(salary)
from employees
group by department_id
having count(employee_id) >= 10;

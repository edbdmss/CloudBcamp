package JDBC.test;

import java.util.List;

import JDBC.dao.DeptDao;
import JDBC.dto.DeptDto;


// main 메서드 위치
public class DeptTest {
    public static void main(String[] args) {
        try {
            // 1
            List<DeptDto> deptList = DeptDao.getAllDepts();
			System.out.println(deptList);

            // 2
            int searchDeptno = 20;
            DeptDto dept20 = DeptDao.getDeptByDeptno(searchDeptno);
            System.out.println(dept20);


            // 3
            /*
            DeptDto cloud = DeptDto.builder()
                                    .deptno(50)
                                    .dname("Cloud")
                                    .loc("Seoul")
                                    .build();
                                
            DeptDao.insertDept(cloud);
            */


            // 4
            /*
            int updatenum = 50;
            String loc = "Gangnam";
            DeptDao.updateDept(updatenum, loc);
            */


            // 5
            /*
            int deletenum = 50;
            DeptDao.deleteDept(deletenum);
            */
                

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

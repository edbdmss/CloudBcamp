package JDBC.test;

import java.util.List;

import JDBC.dao.DeptDao;
import JDBC.dto.DeptDto;


// main 메서드 위치
public class DeptTest {
    public static void main(String[] args) {
        try {
            List<DeptDto> deptList = DeptDao.getAllDepts();
			System.out.println(deptList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

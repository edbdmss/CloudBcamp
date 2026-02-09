package JDBC.test;

import JDBC.dao.DeptDao;
import JDBC.dto.DeptDto;


// main 메서드 위치
public class DeptTest {
    public static void main(String[] args) {
        try {
            DeptDao.getAllDepts();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

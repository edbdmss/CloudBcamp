package JDBC_mvc.dept.service;

import java.sql.SQLException;
import java.util.List;

import JDBC_mvc.dept.dao.DeptDao;
import JDBC_mvc.dept.dto.DeptDto;

public class DeptService {

    // 1
    public static List<DeptDto> getAllDepts() throws SQLException {
        return DeptDao.getAllDepts();
    }

    // 2
    public static DeptDto getDeptByDeptno(int deptno) throws SQLException {
        return DeptDao.getDeptByDeptno(deptno);
    }

    // 3
    public static boolean getInsertDept(DeptDto dept) throws SQLException {

        if (DeptDao.getDeptByDeptno(dept.getDeptno()) != null) {            // 이미 존재하는 부서면 오류
            throw new SQLException("이미 존재하는 부서 번호");
        }

        // 오류를 어디로 보내지? - View? catch 블록에서 CompanyView를 직접 부르는 것은 Service의 역할을 벗어나는 행동.
        // CompanyView.printErrorMessage(e.getMessage());

        return DeptDao.insertDept(dept);
    }

    // 4
    public static boolean getUpdateDept(int deptno, String loc) throws SQLException {
        return DeptDao.updateDeptLocByDeptno(deptno, loc);
    }

    // 5
    public static boolean getDeleteDept(int deptno) throws SQLException {
        return DeptDao.deleteDeptByDeptno(deptno);
    }

}
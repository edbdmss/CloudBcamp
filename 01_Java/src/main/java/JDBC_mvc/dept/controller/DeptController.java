package JDBC_mvc.dept.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBC_mvc.dept.dto.DeptDto;
import JDBC_mvc.dept.service.DeptService;
import JDBC_mvc.view.CompanyView;


// View에서 호출해서-> service로부터 빋아오는 역할. 
// 가져오는건 Service고, 결과값 or 오류는 View에 전달.
public class DeptController {
    
    public static List<DeptDto> selectDepts() throws SQLException{
        try {  
            List<DeptDto> deptlist = null;
            deptlist =  DeptService.getAllDepts();

            if (deptlist == null) {
                // View에게 "데이터가 없습니다"라고 알리는 로직 필요
            }
            return deptlist;
        } catch (Exception e) {
            // 예외 발생 시 View의 에러 출력 전용 메서드 호출
            CompanyView.printErrorMessage(e.getMessage());
        }
        return null;
    }



    public static DeptDto selectDept(int deptno) throws SQLException {
        try {
            DeptDto dept = null;
            dept = DeptService.getDeptByDeptno(deptno);

            if (dept == null) {
                // View에게 "데이터가 없습니다"라고 알리는 로직 필요
            }
            return dept;

        } catch (Exception e) { 
            CompanyView.printErrorMessage(e.getMessage());
        }
        return null;
    }
    


    public static boolean insertDept(DeptDto dept) throws SQLException{
        
        try {
            boolean insertdept = false;
            insertdept = DeptService.getInsertDept(dept);
            return insertdept;

        } catch (Exception e) {
            CompanyView.printErrorMessage(e.getMessage());
        }
        return false;
    }


    public static boolean updateDept(int deptno, String loc) throws SQLException{
    
        try {
            boolean updatedept = false;
            updatedept = DeptService.getUpdateDept(deptno, loc);

            if (updatedept == false) {
                // View 로드 실패
            }
            return updatedept;
        } catch (Exception e) {
            CompanyView.printErrorMessage(e.getMessage());
        }
        return false;
    }


    public static boolean deleteDept(int deptno) throws SQLException {
        
        try {
            boolean deletedept = false;
            deletedept = DeptService.getDeleteDept(deptno);

            if (deletedept == false) {
                // View 로드 실패
            }
            return deletedept;
        } catch (Exception e) {
            CompanyView.printErrorMessage(e.getMessage());
        }
        return false;
    }
}
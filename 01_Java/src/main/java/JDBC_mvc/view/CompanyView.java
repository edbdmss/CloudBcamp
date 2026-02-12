package JDBC_mvc.view;

import JDBC_mvc.dept.controller.DeptController;
import JDBC_mvc.dept.dto.DeptDto;

public class CompanyView {


    // 우리가 Controller에서 넘겨주는 예외 메시지는 일반적인 String 타입
    public static void printErrorMessage(String m) {
        System.out.println(m);
    }
    
    public static void main(String[] args) {
        
        try {

            // 1
            System.out.println(DeptController.selectDepts());


            // 2
            int deptno = 20;
            System.out.println(DeptController.selectDept(deptno));


            // 3
            DeptDto cloud = DeptDto.builder()
                                    .deptno(50)
                                    .dname("Cloud")
                                    .loc("Seoul")
                                    .build();
            System.out.println(DeptController.insertDept(cloud));


            // 4
            int updatenum = 50;
            String loc = "Gangnam";
            System.out.println(DeptController.updateDept(updatenum, loc));


            // 5
            int deletenum = 50;
            System.out.println(DeptController.deleteDept(deletenum));
                

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

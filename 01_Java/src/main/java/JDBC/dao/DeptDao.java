package JDBC.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBC.dto.DeptDto;
import JDBC.util.DBUtil;

public class DeptDao {


    // 1. 모든 부서 정보 검색 메소드
    public static List<DeptDto> getAllDepts() throws SQLException {
        List<DeptDto> deptList = new ArrayList<>();

        // step02 & step03
        String sql = "SELECT * FROM dept";

        try (Connection con = DBUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            // step04
            try (ResultSet rset = pstmt.executeQuery(sql)) {

                // step05 - 행이 몇개인지 모르기때문에 while문으로 추가함.
                while (rset.next()) {
                    deptList.add(DeptDto.builder() // builder() 추가
                            .deptno(rset.getInt("deptno"))
                            .dname(rset.getString("dname"))
                            .loc(rset.getString("loc"))
                            .build());
                }
            }

            System.out.println(con);

        } // finally 블록에서 close()를 수동으로 할 필요가 없음

        return deptList;
    }



    // 2. 부서번호로 특정 부서 정보 검색 메소드
    public static DeptDto getDeptByDeptno(int deptno) throws SQLException {

        String sql = "SELECT * FROM dept WHERE deptno = ?";
        DeptDto dept = null;

        try (Connection con = DBUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
            
            pstmt.setInt(1, deptno);
            
            ResultSet rset = pstmt.executeQuery();


            // 반환값이 하나거나 없으므로 while문 필요 x
            if (rset.next()) {
                dept = DeptDto.builder()
                                .deptno(rset.getInt("deptno"))
                                .dname(rset.getString("dname"))
                                .loc(rset.getString("loc"))
                                .build();
            }

            return dept;
        }
    }




    // 3. 부서 생성 INSERT INTO dept VALUES(50, "Cloud", "Seoul")
    public static void insertDept(DeptDto cloud) throws SQLException {

        String sql = "INSERT INTO DEPT VALUES(?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            // 파라미터 세팅
            pstmt.setInt(1, cloud.getDeptno());
            pstmt.setString(2, cloud.getDname());
            pstmt.setString(3, cloud.getLoc());

            // SQL 실행 -- 젤 중요함.
            // executeUpdate()는 영향받은 행의 개수를 반환한다.
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("부서 등록 성공");
            }

        }
    }


        // 4. 부서 번호 50으로 검색한 부서의 위치를 강남으로 수정 (GANGNAM)
        public static void updateDept(int deptno, String loc) throws SQLException {
            
            String sql = "UPDATE DEPT SET loc = ? WHERE deptno = ?";

            try (Connection con = DBUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {

                // 파라미터 세팅
                pstmt.setString(1, loc);
                pstmt.setInt(2, deptno);


                int result = pstmt.executeUpdate();
                if (result > 0) {
                    System.out.println("수정 성공");
                }

            }
        }



        // 5. 부서 번호 50으로 검색한 부서의 정보를 삭제
        public static void deleteDept(int deptno) throws SQLException {
            
            String sql = "DELETE FROM DEPT WHERE deptno = ?";

            try (Connection con = DBUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {

                // 파라미터 세팅
                pstmt.setInt(1, deptno);


                int result = pstmt.executeUpdate();
                if (result > 0) {
                    System.out.println("삭제 성공");
                }
            }
        }



    }

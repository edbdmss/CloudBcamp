package JDBC.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import JDBC.dto.DeptDto;

public class DeptDao {
    
    // step01
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");      // mysql이 갖고있는 고유한 driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    // 1. 모든 부서 정보 검색 메소드
    // SELECT * FROM dept
    public static List<DeptDto> getAllDepts() throws SQLException {
        List<DeptDto> deptList = new ArrayList<>();
        
        // step02 & step03
        String url = "jdbc:mysql://localhost:3306/scott?serverTimezone=UTC";
        String sql = "SELECT * FROM dept";
        
        try (Connection con = DriverManager.getConnection(url, "root", "edbdms1012");
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            // step04
            try (ResultSet rset = pstmt.executeQuery(sql)) {
                
                // step05 - 행이 몇개인지 모르기때문에 while문으로 추가함.
                while (rset.next()) {
                    deptList.add(DeptDto.builder()          // builder() 추가
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




    // - 반환값이 하나거나 없으므로 while문 필요 x


}
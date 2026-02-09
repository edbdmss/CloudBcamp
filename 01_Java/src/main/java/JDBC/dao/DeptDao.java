package JDBC.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    
    // 모든 부서 정보 검색 메소드
    // SELECT * FROM dept
    public static List<DeptDto> getAllDepts() throws SQLException {
        List<DeptDto> deptList = new ArrayList<>();
        
        // step02 & step03: try (...) 안에 선언하면 자동으로 close() 됨
        String url = "jdbc:mysql://localhost:3306/scott?serverTimezone=UTC";
        
        try (Connection con = DriverManager.getConnection(url, "root", "edbdms1012");
             Statement stmt = con.createStatement()) {
            
            // step04
            String sql = "SELECT * FROM dept";
            try (ResultSet rset = stmt.executeQuery(sql)) {
                
                // step05
                while (rset.next()) {
                    deptList.add(new DeptDto(rset.getInt("deptno"),
                                            rset.getString("dname"), 
                                            rset.getString("loc")));
                }
            }

            System.out.println(con);
            
        } // finally 블록에서 close()를 수동으로 할 필요가 없음
        
        return deptList;
    }
    
    // 부서번호로 특정 부서 정보 검색 메소드
}
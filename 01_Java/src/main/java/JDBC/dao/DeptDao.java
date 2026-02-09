package JDBC.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
		List<DeptDto> deptList = null;
		Connection con = null;
		Statement stmt = null;
		
		try {
			// step02
			String url = "jdbc:mysql://localhost:3306/scott?serverTimezone=UTC";
			con = DriverManager.getConnection(url, "root", "edbdms1012");
			// step03
			stmt = con.createStatement();
			// step04
			stmt.executeQuery("SELECT * FROM dept");
			
			System.out.println(con);
			
		} finally {
		}
		
		
		return deptList;
	}
	
	
	
	// 부서번호로 특정 부서 정보 검색 메소드
	
	
}
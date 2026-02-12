package JDBC_mvc.dept.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBC_mvc.dept.dto.DeptDto;
import JDBC_mvc.util.DBUtil;

public class DeptDao {

	// 모든 부서 정보 검색 메소드
	// SELECT * FROM dept
	public static List<DeptDto> getAllDepts() throws SQLException {
		List<DeptDto> deptList = new ArrayList<>();

		String sql = "SELECT * FROM dept";

		try (Connection con = DBUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			try (ResultSet rset = pstmt.executeQuery()) {
				while (rset.next()) {
					deptList.add(DeptDto.builder()
							.deptno(rset.getInt("deptno"))
							.dname(rset.getString("dname"))
							.loc(rset.getString("loc"))
							.build());
				}
			}
		}

		return deptList;
	}

	// 부서번호로 특정 부서 정보 검색 메소드
	// SELECT * FROM dept WHERE deptno = ?
	public static DeptDto getDeptByDeptno(int deptno) throws SQLException {
		
		String sql = "SELECT * FROM dept WHERE deptno = ?";
        DeptDto dept = null;

		try (Connection con = DBUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
				
				pstmt.setInt(1, deptno);
				try (ResultSet rset = pstmt.executeQuery()) {
                if (rset.next()) {
                    dept = DeptDto.builder()
                            .deptno(rset.getInt("deptno"))
                            .dname(rset.getString("dname"))
                            .loc(rset.getString("loc"))
                            .build();
                }
            } 

			return dept;
		}
		
	}

	// 부서 생성
	// INSERT INTO dept VALUES(50, CLOUD, SEOUL)
	public static boolean insertDept(DeptDto cloud) throws SQLException, Exception {

		String sql = "INSERT INTO dept VALUES(?, ?, ?)";
		try (Connection con = DBUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, cloud.getDeptno());
			pstmt.setString(2, cloud.getDname());
			pstmt.setString(3, cloud.getLoc());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				return true;
			}
		}
		return false;

	}


	// 부서번호로 검색한 부서의 위치를 GANGNAM으로 수정 : 50 -> 위치변경
	// UPDATE dept SET loc = ? WHERE deptno = ?
	public static boolean updateDeptLocByDeptno(int deptno, String newLoc) throws SQLException {
		// Connection con = null;
		// PreparedStatement pstmt = null;

		String sql = "UPDATE dept SET loc = ? WHERE deptno = ?";
		try (Connection con = DBUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, newLoc);
			pstmt.setInt(2, deptno);

			int result = pstmt.executeUpdate();
			if (result > 0) {
				return true;
			}
		}

		return false;
	}

	// 부서번호로 검색한 부서의 정보를 삭제 : 50
	// DELETE FROM dept WHERE deptno = ?
	public static boolean deleteDeptByDeptno(int deptno) throws SQLException {
		// Connection con = null;
		// PreparedStatement pstmt = null;

		String sql = "DELETE FROM dept WHERE deptno = ?";
		try (Connection con = DBUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, deptno);

			int result = pstmt.executeUpdate();
			if (result > 0) {
				return true;
			}
		}

		return false;
	}
}
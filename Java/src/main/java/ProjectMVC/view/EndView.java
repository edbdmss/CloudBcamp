package ProjectMVC.view;

import ProjectMVC.model_domain.Student;
import java.util.List;

public class EndView {
	// 모든 데이터 출력
	public static void printAllStudents(List<Student> allData) {
		for (Student student : allData) {
			System.out.println(student);
		}
	}
	
	// 한명 출력
	public static void printStudent(Student student) {
		System.out.println(student);
	}
	
	public static void printResult(String msg) {
		System.out.println(msg);
	}

}

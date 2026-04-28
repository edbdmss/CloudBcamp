package ProjectMVC.controller;

import ProjectMVC.model.StudentGrade;
import ProjectMVC.model.StudentModel;
import ProjectMVC.model_domain.Student;
import ProjectMVC.view.EndView;
import java.util.List;

public class StudentController {
	public static void feature(int num) {

		try {
			List<Student> studentList = StudentModel.getAllStudents();

			if (num == 1) {

				EndView.printAllStudents(studentList);

			} else if (num == 2) {

				Student student = StudentModel.getStudentByStudentNo(1001);
				EndView.printStudent(student);

			} else if (num == 3) {
				boolean result = StudentModel.insertStudent(new Student("CE", 1000, StudentGrade.JUNIOR));

				if (result) {
					EndView.printResult("저장 성공");
					EndView.printAllStudents(StudentModel.getAllStudents());

				}

			} else if (num == 4) {
				boolean result = StudentModel.deleteStudentByStudentNo(1001);

				if (result) {
					EndView.printResult("삭제 성공"); 
					EndView.printAllStudents(StudentModel.getAllStudents());

				}

			} else if (num == 5) {
				boolean result = StudentModel.updateGrade(1001, StudentGrade.SENIOR);

				if (result) {
					EndView.printResult("수정 성공");
					EndView.printAllStudents(StudentModel.getAllStudents());
				}
			}
		} catch (Exception e) {
			EndView.printResult(e.getMessage());
		}
	}
}

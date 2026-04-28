package ProjectMVC.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ProjectMVC.model_domain.Student;

public class StudentModel {

	// Student 학생 객체 관리
	private static List<Student> studentList = new ArrayList<Student>();

	static {

		// 1
		studentList.add(Student.builder()
				.name("Java")
				.studentNo(1004)
				.gradecode(StudentGrade.JUNIOR)
				.build());

		// 2
		studentList.add(Student.builder().build());

		// 3
		studentList.add(Student.builder()
				.name("DB")
				.studentNo(1001)
				.gradecode(StudentGrade.JUNIOR)
				.build());

		// 4
		studentList.add(Student.builder()
				.name("FrontEnd")
				.studentNo(1002)
				.gradecode(StudentGrade.JUNIOR)
				.build());

	}

	// 핵심 기능
	// 검색 - 모든 학생 검색
	public static List<Student> getAllStudents() throws Exception {

		List<Student> notNullStudentList = studentList.stream()
				.filter(Objects::nonNull)
				.toList();

		if (notNullStudentList.isEmpty()) {
			throw new Exception("데이터 존재x");
		}

		return notNullStudentList;
	}

	// 검색 - 학생 번호로 검색
	public static Student getStudentByStudentNo(int studentNo) throws Exception {
		// Optional 생성
		Optional<Student> optStudent = studentList.stream()
				.filter(s -> s != null && s.getStudentNo() == studentNo)
				.findFirst();

		// 반환
		return studentList.stream()
				.filter(s -> s != null && s.getStudentNo() == studentNo)
				.findFirst()
				.orElseThrow(() -> new Exception("데이터 존재x"));
	}

	// 저장 - 학생번호가 중복되지 않는경우
	public static boolean insertStudent(Student student) throws Exception {

		// 학생 객체의 null -> 예외발생
		if (student == null)
			throw new Exception("입력 데이터 null");

		// 학생 번호를 통해 존재 유무 파악하기 -> 존재한다면, 예외 발생
		boolean isExist = studentList.stream()
				.filter(Objects::nonNull)
				.anyMatch(s -> s.getStudentNo() == student.getStudentNo());

		if (isExist)
			throw new Exception("학생 번호 중복");

		// 학생 추가
		studentList.add(student);
		return true;
	}

	// 삭제 - 학생번호로 학생 정보 삭제
	public static boolean deleteStudentByStudentNo(int studentNo) throws Exception {

		// list에서 해당 학생의 index 찾기
		// removeIf : 컬렉션내부 요소에서 조건 만족하는 모든 요소 삭제
		boolean removed = studentList.removeIf(s -> s != null && s.getStudentNo() == studentNo);
		if (!removed) {
			throw new Exception("삭제 실패");
		}
		return true;
	}

	// 수정 - 학생번호로 학생 검색 후, Junior값을 Senior로 변경
	public static boolean updateGrade(int studentNo, StudentGrade grade) throws Exception {

		// studentNo 학생 존재 유무
		Student student = getStudentByStudentNo(studentNo);

		// grade 수정
		student.setGradecode(grade);
		return true;
	}

}

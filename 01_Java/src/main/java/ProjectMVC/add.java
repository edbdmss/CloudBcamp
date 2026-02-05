/*
 * - **고려사항1**
 * - 이전에 사용했던 Student를 고유하게 구분할 수 있는 값이 없음 → 고유하게 구분할 수 있는 값을 활용
 * - **domain.Student**
 * 
 * ```java
 * package model.domain;
 * 
 * public class Student {
 * private String name;
 * private int studentNo;
 * private String grade;
 * 
 * public Student() {}
 * public Student(String name, int studentNo, String grade) {
 * this.name = name;
 * this.studentNo = studentNo;
 * this.grade = grade;
 * }
 * 
 * public String getName() {
 * return name;
 * }
 * public void setName(String name) {
 * this.name = name;
 * }
 * public int getStudentNo() {
 * return studentNo;
 * }
 * public void setStudentNo(int studentNo) {
 * this.studentNo = studentNo;
 * }
 * public String getGrade() {
 * return grade;
 * }
 * public void setGrade(String grade) {
 * this.grade = grade;
 * }
 * 
 * @Override
 * public String toString() {
 * return "Student [name=" + name + ", studentNo=" + studentNo + ", grade=" +
 * grade + "]";
 * }
 * 
 * }
 * 
 * ```
 * 
 * - 고유하게 구분할 수 있는 값을 활용한 StudentModel에서의 메소드 활용
 * - **domain.model.StudentModel**
 * 
 * ```java
 * package model;
 * 
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 * import model.domain.Student;
 * 
 * public class StudentModel {
 * 
 * // Student 학생 객체 관리
 * private static List<Student> studentList = new ArrayList<Student>();
 * 
 * static {
 * studentList.add(new Student("Java", 1004, "Junior"));
 * studentList.add(null);
 * studentList.add(new Student("DB", 1001, "Junior"));
 * studentList.add(new Student("FrontEnd", 1002, "Junior"));
 * }
 * 
 * 
 * // 핵심 기능
 * // 검색 - 모든 학생 검색
 * public static List<Student> getAllStudents() {
 * return null;
 * }
 * 
 * // 검색 - 학생 번호로 검색
 * public static Student getStudentByStudentNo(int studentNo) {
 * return null;
 * }
 * 
 * // 저장 - 학생번호가 중복되지 않는경우
 * public static boolean insertStudent(Student student) {
 * return false;
 * }
 * 
 * // 삭제 - 학생번호로 학생 정보 삭제
 * public static boolean deleteStudentByStudentNo(int studentNo) {
 * return false;
 * }
 * 
 * // 수정 - 학생번호로 학생 검색 후, Junior값을 Senior로 변경
 * public static boolean updateGrade(int studentNo, String grade) {
 * return false;
 * }
 * 
 * }
 * 
 * ```
 * 
 * - Controller의 변경 사항을 토대로 각 기능별 결과 출력
 * - **controller.StudentController**
 * 
 * ```java
 * package controller;
 * 
 * import java.util.List;
 * 
 * import model.StudentModel;
 * import model.domain.Student;
 * import view.EndView;
 * 
 * public class StudentController {
 * public static void feature(int num) {
 * if(num == 1) {
 * 
 * List<Student> studentList = StudentModel.getAllStudents();
 * EndView.printAllStudents(studentList);
 * 
 * }else if(num == 2) {
 * 
 * Student student = StudentModel.getStudentByStudentNo(1001);
 * EndView.printStudent(student);
 * 
 * }else if(num == 3) {
 * boolean result = StudentModel.insertStudent(new Student("CE", 1000,
 * "Junior"));
 * 
 * if(result) {
 * EndView.printResult("저장 성공");
 * }else {
 * EndView.printResult("저장 실패");
 * }
 * 
 * }else if(num == 4) {
 * boolean result = StudentModel.deleteStudentByStudentNo(1000);
 * 
 * if(result) {
 * EndView.printResult("삭제 성공");
 * }else {
 * EndView.printResult("삭제 실패");
 * }
 * 
 * }else if(num == 5) {
 * boolean result = StudentModel.updateGrade(1001, "Senior");
 * 
 * if(result) {
 * EndView.printResult("수정 성공");
 * }else {
 * EndView.printResult("수정 실패");
 * }
 * }
 * }
 * }
 * 
 * ```
 * 
 * - **고려사항2**
 * - 고려사항1의 내용 기반으로 exception과 stream, optional 문법 적용해보기
 * - **hint**
 * - 핵심계층(Model)에서 **stream, optional을 활용하여 기능 개선**
 * - 핵심계층(Model) 기능 내에서 핵심기능 수행에 실패한다면 **Exception 발생**
 * - 단, 편의상 사용자 정의예외 사용하지 않고 일반 Exception클래스로 활용
 * - Exception 클래스에 “검색 실패”, “삭제 실패” 등 기능별 실패 메세지 전달
 * - Model에서 발생한 Exception은 모두 Controller에서 처리 → 실패 메세지는 EndView의 실패결과 출력 메소드를
 * 활용
 * 
 */
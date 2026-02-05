package ProjectMVC.view;

import java.util.Scanner;

import ProjectMVC.controller.StudentController;

// main 위치
public class StartView {
	public static void main(String[] args) {
		System.out.println("*** 기능 ***");
		System.out.println("*** 모든 검색 : 1 입력***");
		System.out.println("*** 학생 검색 : 2 입력***");
		System.out.println("*** 학생 등록 : 3 입력***");
		System.out.println("*** 학생 삭제 : 4 입력***");
		System.out.println("*** 학생 수정 : 5 입력***");
		
		System.out.println("번호 입력: ");
		Scanner sc = new Scanner(System.in);
		
		try {
			String num = sc.nextLine();
			StudentController.feature(Integer.parseInt(num));
		} catch (Exception e) {
		} finally {
			sc.close();

		}
	}
}

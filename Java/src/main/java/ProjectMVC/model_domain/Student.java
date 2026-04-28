package ProjectMVC.model_domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ProjectMVC.model.StudentGrade;

@Builder
@ToString
@Getter
@Setter
public class Student {

	private String name;
	private int studentNo;
	private StudentGrade gradecode; 


	public Student() {}
	public Student(String name, int studentNo, StudentGrade gradecode) {
		this.name = name;
		this.studentNo = studentNo;
		this.gradecode = gradecode;
	}
	
}	
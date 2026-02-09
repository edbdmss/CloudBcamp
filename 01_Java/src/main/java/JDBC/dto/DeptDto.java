package JDBC.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptDto {

    private Integer deptno;         // db에서의 null은 자바에서의 객체 ?  왜 int가 아닌 Integer인가 ? null로 넘겨질 수 있는 것들은 객체화?
    private String dname;
    private String loc;
    
    @Builder   // 이 위치에 선언하면 롬복이 내부적으로 빌더 클래스를 만듬. 웬만하면 이거 써주는게 좋을듯.
    public DeptDto(int deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }
}

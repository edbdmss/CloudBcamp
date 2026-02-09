package JDBC.dto;

import com.google.auto.value.AutoValue.Builder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeptDto {

    private Integer deptno;         // db에서의 null은 자바에서의 객체 ?  왜 int가 아닌 Integer인가 ? null로 넘겨질 수 있는 것들은 객체화?
    private String dname;
    private String loc;
    
}

package JDBC_mvc.dept.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeptDto {
    
    private int deptno;
    private String dname;
    private String loc;

}

package JDBC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DeptDto {

    private Integer deptno;       
    private String dname;
    private String loc;

}

package exp.company.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeDTO {
    public EmployeeDTO(Long id, String name, String last_name,String mission){
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.mission = mission;
    }
    private Long id;
    private String name;
    private String last_name;
    private String mission;

    public String getMission() {
        return mission;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLast_name() {
        return last_name;
    }
}

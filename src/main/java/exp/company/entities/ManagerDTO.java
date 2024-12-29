package exp.company.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ManagerDTO {
    private Long id;
    private String name;
    private String last_name;
    public ManagerDTO(Long id, String name , String last_name){
        this.id = id;
        this.name = name;
        this.last_name = last_name;
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

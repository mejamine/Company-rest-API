package exp.company.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamDTO {
    public TeamDTO(Long id, String name){
        this.id = id;
        this.name = name;
    }
    private Long id;

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    private String name;
}

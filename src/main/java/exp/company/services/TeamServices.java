package exp.company.services;
import exp.company.entities.Manager;
import exp.company.entities.Team;
import exp.company.entities.TeamDTO;

import java.util.List;

public interface TeamServices {
    public void addTeam(Team team);
    public void removeTeam(Long id);
    public Team getTeam(Long id);
    public List<Team> getAllTeams();
    public void updateTeam(Team team);
    public List<Manager> getManagers(Long id);

    //DTO
    public List<TeamDTO> getTeamDTOs();
    public TeamDTO getTeamDTO(Long id);
}

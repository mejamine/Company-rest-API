package exp.company.services;
import exp.company.entities.Manager;
import exp.company.entities.Team;
import exp.company.entities.TeamDTO;
import exp.company.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeamServicesImplementations implements TeamServices {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public void addTeam(Team team) {
        teamRepository.save(team);
    }

    @Override
    public void removeTeam(Long id) {
        teamRepository.deleteById(id);
    }

    @Override
    public Team getTeam(Long id) {
        return teamRepository.findById(id).get();
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public void updateTeam(Team team) {
        teamRepository.save(team);
    }

    @Override
    public List<Manager> getManagers(Long id) {
        return teamRepository.findManagersByTeamId(id);
    }

    //DTO

    @Override
    public List<TeamDTO> getTeamDTOs() {
        return teamRepository.findAll().stream()
                .map(team -> new TeamDTO(team.getTeam_id(), team.getName()))
                .toList();
    }

    @Override
    public TeamDTO getTeamDTO(Long id) {
        Team team = teamRepository.findById(id).get();
        return new TeamDTO(team.getTeam_id(), team.getName());
    }
}

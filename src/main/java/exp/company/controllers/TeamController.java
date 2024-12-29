package exp.company.controllers;
import exp.company.entities.*;
import exp.company.repositories.EmployeeRepository;
import exp.company.services.EmployeeServices;
import exp.company.services.TeamServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamServices teamServices;

    @Autowired
    private EmployeeServices employeeServices;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/all")
    public List<TeamDTO> getAllTeams( ) {
       return teamServices.getTeamDTOs();

    }
    @GetMapping("/{id}")
    public TeamDTO getTeam(@PathVariable Long id) {
        return teamServices.getTeamDTO(id);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteTeam(@PathVariable Long id) {
        List<Employee> employees = employeeServices.getEmployeeByTeam(teamServices.getTeam(id));
        employeeRepository.deleteAll(employees);
        teamServices.removeTeam(id);
        return "Team deleted";

    }
    @PostMapping("/save")
    public String saveTeam(@RequestParam String Name) {
        Team team = new Team();
        team.setName(Name);
        teamServices.addTeam(team);
        return "Team saved";
    }
    @PutMapping("/update/{id}")
    public String updateTeam(@PathVariable Long id, @RequestParam String Name) {
        Team team = teamServices.getTeam(id);
        team.setName(Name);
        teamServices.updateTeam(team);
        return "update success";
    }
    @GetMapping("/viewEmployee/{id}")
    public List<EmployeeDTO> viewEmployee(@PathVariable Long id) {
        return employeeServices.getEmployeeByTeam(teamServices.getTeam(id))
                .stream().map(employee
                        ->new EmployeeDTO(employee.getEmployee_id(),employee.getName(),employee.getLast_name(),employee.getTask().getDescription()))
                .toList();

    }
    @GetMapping("/viewManagers/{id}")
    public List<ManagerDTO> viewManagers(@PathVariable Long id) {
        return teamServices.getManagers(id)
                .stream().map(manager
                        ->new ManagerDTO(manager.getManager_id(),manager.getName(),manager.getLast_name()))
                .toList();
    }

}

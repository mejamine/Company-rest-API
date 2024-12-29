package exp.company.controllers;
import exp.company.entities.Employee;
import exp.company.entities.Manager;
import exp.company.entities.ManagerDTO;
import exp.company.entities.Team;
import exp.company.repositories.ManagerRepository;
import exp.company.services.ManagerServices;
import exp.company.services.TeamServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerServices managerServices;

    @Autowired
    private TeamServices teamServices;
    @Autowired
    private ManagerRepository managerRepository;


    @PostMapping("/save/{id}")
    public String save(@RequestParam String name,@RequestParam String last_name, @PathVariable Long id) {
        try {
            Team team = teamServices.getTeam(id);
            if (team == null) {
                return "team does not exist";
            }

            Manager manager = new Manager();
            manager.setName(name);
            manager.setLast_name(last_name);
            Manager existingManager = managerRepository.findManagersByName(name);
            if (existingManager != null) {
                return "manager already exists";
            } else {
                List<Employee> employees = team.getEmployees();
                manager.getTeams().add(team);
                team.getManagers().add(manager);
                for (Employee employee : employees) {
                    manager.getEmployees().add(employee);
                    employee.getManagers().add(manager);
                }
                managerServices.addManager(manager);
            }
            return "success";
        } catch (DataIntegrityViolationException e) {
            return "error";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteManager(@PathVariable Long id) {
        Manager manager = managerServices.getManager(id);
        if (manager == null) {
            return "manager does not exist";
        }
        managerServices.removeManager(id);
        return "Manager deleted successfully";
    }
    @PutMapping("/update/{id}")
    public String updateManager(@PathVariable Long id ,@RequestParam String name, @RequestParam String last_name) {
        Manager manager = managerServices.getManager(id);
        if (manager == null) {
            return "manager does not exist";
        }
        manager.setLast_name(last_name);
        manager.setName(name);
        managerServices.addManager(manager);
        return "Manager updated successfully";
    }
    @GetMapping("/all")
    public List<ManagerDTO> getAllManagers() {
        return managerRepository.findAll().stream()
                .map(manager-> new ManagerDTO(manager.getManager_id(), manager.getName(),manager.getLast_name()))
                .toList();
    }
    @GetMapping("/{id}")
    public ManagerDTO getManager(@PathVariable Long id) {
        return managerServices.getManagerDTO(id);
    }
}

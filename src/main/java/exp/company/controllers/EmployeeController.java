package exp.company.controllers;
import exp.company.entities.*;
import exp.company.services.EmployeeServices;
import exp.company.services.TeamServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeService;

    @Autowired
    private TeamServices teamService;


    @PostMapping("/save/{id}")
    public String save(@RequestParam String name,@RequestParam String last_name,@RequestParam String mission, @PathVariable Long id, Model model) {
        try{
            Team team = teamService.getTeam(id);
            Employee employee = new Employee();
            Task task = new Task();
            task.setDescription(mission);
            employee.setName(name);
            employee.setLast_name(last_name);
            employee.setTask(task);
            employee.setTeam(team);
            employeeService.addEmployee(employee);
            return "employee saved successfully";
        }catch(DataIntegrityViolationException e) {
            return "error";
        }

    }
    @PutMapping("/update/{id}")
    public String updateEmployee(@PathVariable Long id,@RequestParam String name,@RequestParam String last_name,@RequestParam String mission) {
        try{
            Employee employee = employeeService.getEmployee(id);
            if(name!=null){
                employee.setName(name);
            }
            if(last_name!=null){
                employee.setLast_name(last_name);
            }
            if(mission!=null){
                Task task = employee.getTask();
                task.setDescription(mission);
            }
            employeeService.updateEmployee(employee);
            return "updated employee successfully";
        }catch(DataIntegrityViolationException e) {
            return "error";
        }

    }
    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        try{
            employeeService.removeEmployee(id);
            return "deleted successfully";
        }catch(DataIntegrityViolationException e) {
            return "error";
        }

    }
    @GetMapping("/all")
    public List<EmployeeDTO> allEmployees(){
            return employeeService.getAllEmployees().stream()
                    .map(employee -> new EmployeeDTO(employee.getEmployee_id(), employee.getName(), employee.getLast_name(), employee.getTask().getDescription()))
                    .toList();
    }
    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@PathVariable Long id) {
        return employeeService.getEmployeeDTO(id);
    }

}

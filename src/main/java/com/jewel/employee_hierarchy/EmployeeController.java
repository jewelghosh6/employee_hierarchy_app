package com.jewel.employee_hierarchy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EmployeeController
{
    @Autowired
    private EmpRepository empRepository;
    @GetMapping("/get-supervisor/{empName}")
    public String getHierarchy(@PathVariable String empName)
    {
        Employee employee = empRepository.findEmployeeByEmpName(empName);
        String supervisorName;
        if(employee.getSupervisor()!=null)
            supervisorName = employee.getSupervisor().getEmpName();
        else supervisorName="N/A";

        String supervisorOfSupervisor;// = supervisor.getSupervisor();
        if(employee.getSupervisor()!=null && empRepository.findEmployeeByEmpName(supervisorName).getSupervisor()!=null)
            supervisorOfSupervisor = empRepository.findEmployeeByEmpName(supervisorName).getSupervisor().getEmpName();
        else supervisorOfSupervisor="N/A";


        return "Emp Name: "+empName+ " Supervisor: "+ supervisorName+ " Supervisor Of Supervisor: "+supervisorOfSupervisor;
    }
    @PostMapping("/save-employee")
    @Transactional
    public String saveEmployee(@RequestBody HashMap<String, String> empSupMap)
    {
        for(Map.Entry<String,String> entry:empSupMap.entrySet()){
            String empName=entry.getKey();
            String supName=entry.getValue();

            Employee supEmp=new Employee();
           if(empRepository.findEmployeeByEmpName(supName)==null)
           {
               supEmp.setEmpName(supName);
              // supEmp.setSupervisor(empRepository.findEmployeeByEmpName(supName).getSupervisor());
               empRepository.save(supEmp);
           }
            Employee emp=new Employee();
            emp.setEmpName(empName);
            emp.setSupervisor(empRepository.findEmployeeByEmpName(supName));
            empRepository.save(emp);
        }
           return "Saved Successfully";
    }
}

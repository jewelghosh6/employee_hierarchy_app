package com.jewel.employee_hierarchy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepository extends JpaRepository<Employee,String> {
    Employee getEmployeeByEmpName(String key);

    Employee findEmployeeByEmpName(String supName);
}

package com.jewel.employee_hierarchy;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee
{
//    @Id
//    @GeneratedValue
//    private long id;


      @Id
      private String empName;

    @ManyToOne
    @JoinColumn
    private Employee supervisor;


}

package com.jewel.employee_hierarchy;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

package com.paypal.bfs.test.employeeserv.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class EmployeeEntity {

    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String country;
    private String zipCode;

}

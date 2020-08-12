package com.paypal.bfs.test.employeeserv.imp;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.exception.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeResourceImplTest {


    @Mock
    EmployeeRepository employeeRepository;

    @Spy
    @Autowired
    ModelMapper modelMapper;

    @Spy
    @InjectMocks
    EmployeeResourceImpl em;

    @Test
    public void convertStringToIntegerException(){
        assertThrows(RuntimeException.class,()->{
            em.employeeGetById("abc");
        });
    }

    @Test
    public void userNotFundException(){
        Mockito.when(employeeRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class,()->{
            em.employeeGetById("1");
        });
    }

    @Test
    public void goodCaseFind(){
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName("Neal");
        Mockito.when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employeeEntity));
        assertEquals("Neal",em.employeeGetById("1").getBody().getFirstName());
    }

    @Test
    public void goodCaseCreate(){
        Employee employee = new Employee();
        employee.setId(1);
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1);
        Mockito.when(employeeRepository.save(any())).thenReturn(employeeEntity);
        assertEquals(new Integer(1),em.createEmployee(employee).getBody().getId());
    }
}
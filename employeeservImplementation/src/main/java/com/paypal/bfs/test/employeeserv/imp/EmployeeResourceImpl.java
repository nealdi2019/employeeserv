package com.paypal.bfs.test.employeeserv.imp;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.exception.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {


    final EmployeeRepository employeeRepository;
    final ModelMapper modelMapper;

    @Autowired
    public EmployeeResourceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {
        Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findById(Integer.valueOf(id));
        if(!employeeEntityOptional.isPresent()){
            throw new EmployeeNotFoundException();
        }
        Employee found = modelMapper.map(employeeEntityOptional.get(), Employee.class);
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employee> createEmployee(Employee employee) {
        System.out.println(employee);
        EmployeeEntity entity = modelMapper.map(employee, EmployeeEntity.class);
        System.out.println("------");
        System.out.println(entity);
        System.out.println("------");
        EmployeeEntity saved = employeeRepository.save(entity);
        return new ResponseEntity<>(modelMapper.map(saved, Employee.class),HttpStatus.CREATED);
    }
}

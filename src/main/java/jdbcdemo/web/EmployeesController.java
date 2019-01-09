package jdbcdemo.web;


import jdbcdemo.data.base.EmployeeRepository;
import jdbcdemo.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("api/employees")
public class EmployeesController {
    private EmployeeRepository repository;

    @Autowired
    public EmployeesController(EmployeeRepository repository){
        this.repository = repository;
    }

    @RequestMapping("/")
    private List<Employee> getAll() {
        return repository.getAll();
    }

}

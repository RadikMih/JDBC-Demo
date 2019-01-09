package jdbcdemo.web;


import jdbcdemo.data.base.EmployeeRepository;
import jdbcdemo.models.Employee;
import jdbcdemo.services.base.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    private EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/")
    private List<Employee> getAll() {
        return service.getAll();
    }

    @GetMapping("/find/{name}")
    List<Employee> findByName(@PathVariable("name") String name){
        return service.findByName(name);
    }
}

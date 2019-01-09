package jdbcdemo.services.base;

import jdbcdemo.models.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();
    List<Employee> findByName(String name);
}

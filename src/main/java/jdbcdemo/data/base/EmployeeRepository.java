package jdbcdemo.data.base;

import jdbcdemo.models.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAll();
    List<Employee> findByName(String name);
}

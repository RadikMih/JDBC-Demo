package jdbcdemo.data;

import jdbcdemo.data.base.EmployeeRepository;
import jdbcdemo.models.Employee;

import java.util.List;

public class EmployeeFileRepository implements EmployeeRepository {
    @Override
    public List<Employee> getAll() {
        return null;
    }

    @Override
    public List<Employee> findByName(String name) {
        return null;
    }
}

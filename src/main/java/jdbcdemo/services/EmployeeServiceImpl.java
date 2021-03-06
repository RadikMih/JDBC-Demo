package jdbcdemo.services;

import jdbcdemo.data.base.EmployeeRepository;
import jdbcdemo.models.Employee;
import jdbcdemo.services.base.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Employee> findByName(String name) {
        return repository.findByName(name);
    }
}

package jdbcdemo.web;


import jdbcdemo.models.Employee;
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
public class EmployeeService {

    @RequestMapping("/")
    private List<Employee> getAll() {
        String configFile = "src\\main\\resources\\application.properties";
        Properties dbConfig = new Properties();
        try (FileInputStream fis = new FileInputStream(configFile)) {
            dbConfig.load(fis);
        } catch (FileNotFoundException e) {
            System.out.println("Properties file not found");
        } catch (IOException e) {
            System.out.println("Unable to read properties file");
        }


        String dbUrl = dbConfig.getProperty("dbUrl");
        String username = dbConfig.getProperty("username");
        String password = dbConfig.getProperty("password");


        List<Employee> employees = new ArrayList<>();
        try (
                Connection connection = DriverManager.getConnection(dbUrl, username, password);
                Statement statement = connection.createStatement();
        ) {
            System.out.println("Connection created!");
            String query = "select FirstName, LastName, JobTitle from employees";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Employee e = new Employee(
                        result.getString("FirstName"),
                        result.getString("LastName"),
                        result.getString("JobTitle"));
                employees.add(e);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employees;
    }

}

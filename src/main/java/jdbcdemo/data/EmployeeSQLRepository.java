package jdbcdemo.data;

import jdbcdemo.data.base.EmployeeRepository;
import jdbcdemo.models.Employee;
import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Repository
public class EmployeeSQLRepository implements EmployeeRepository {
    private final String CONFIG_FILE = "src\\main\\resources\\application.properties";

    private String dbUrl;
    private String username;
    private String password;

    EmployeeSQLRepository() {
        dbUrl = username = password = "";
    }

    private void loadDbConfig() {
        if (dbUrl.equals("")){
        Properties dbConfig = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            dbConfig.load(fis);
        } catch (FileNotFoundException e) {
            System.out.println("Properties file not found");
        } catch (IOException e) {
            System.out.println("Unable to read properties file");
        }

        dbUrl = dbConfig.getProperty("dbUrl");
        username = dbConfig.getProperty("username");
        password = dbConfig.getProperty("password");
        }
    }

    @Override
    public List<Employee> getAll() {
        loadDbConfig();
        List<Employee> employees = new ArrayList<>();

        try (
                Connection connection = DriverManager.getConnection(dbUrl, username, password);
                Statement statement = connection.createStatement();
        ) {
            System.out.println("Connection created!");
            String query = "select FirstName, LastName, JobTitle from employees";
            ResultSet result = statement.executeQuery(query);
            readEmployeesData(employees, result);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employees;
    }

    @Override
    public List<Employee> findByName(String name) {
        loadDbConfig();

        String query = "select FirstName, LastName, JobTitle from employees " +
                "where FirstName = ?";

        List<Employee> employees = new ArrayList<>();

        try (
                Connection connection = DriverManager.getConnection(dbUrl, username, password);
               PreparedStatement statement = connection.prepareStatement(query);
        ) {
            System.out.println("Connection created!");

            statement.setString(1, name);

            ResultSet result = statement.executeQuery();
            readEmployeesData(employees, result);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employees;
    }

    private void readEmployeesData(List<Employee> employees, ResultSet result) throws SQLException {
        while (result.next()) {
            Employee e = new Employee(
                    result.getString("FirstName"),
                    result.getString("LastName"),
                    result.getString("JobTitle"));
            employees.add(e);
        }
    }
}

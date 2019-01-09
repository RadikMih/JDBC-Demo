package jdbcdemo.web;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@RestController
@RequestMapping("api/employees")
public class EmployeeService {

    @RequestMapping("/")
    void getAll(){
        String configFile = "src\\main\\resources\\application.properties";
        Properties dbConfig = new Properties();
        try (FileInputStream fis = new FileInputStream(configFile)){
            
        }



        String dbUrl = "jdbc:mysql://localhost:3306/telerikacademy";
        String username = "dev_user";
        String password = "dev_user";

        try(Connection connection = DriverManager.getConnection(dbUrl, username, password)){
            System.out.println("Connection created!");
        } catch (SQLException e) {
            System.out.println("Connection failed!");
        }
    }

}

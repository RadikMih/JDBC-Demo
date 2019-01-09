package jdbcdemo.web;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
            dbConfig.load(fis);
        } catch (FileNotFoundException e) {
            System.out.println("Properties file not found");
        } catch (IOException e){
            System.out.println("Unable to read properties file");
        }



        String dbUrl = dbConfig.getProperty("dbUrl");
        String username = dbConfig.getProperty("username");
        String password = dbConfig.getProperty("password");

        try(Connection connection = DriverManager.getConnection(dbUrl, username, password)){
            System.out.println("Connection created!");
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            System.out.println(e.getMessage());
        }
    }

}

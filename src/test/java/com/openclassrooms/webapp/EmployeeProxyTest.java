package com.openclassrooms.webapp;

import com.openclassrooms.webapp.model.Employee;
import com.openclassrooms.webapp.repository.EmployeeProxy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class EmployeeProxyTest {

    @Autowired
    private EmployeeProxy employeeProxy;

    @Test
    public void getEmployees() throws Exception {
        Iterable<Employee> employee = employeeProxy.getEmployees();
        for (Employee e : employee) {
            System.out.println(e.getFirstname());
        }
    }

}
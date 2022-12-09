package com.openclassrooms.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.openclassrooms.webapp.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeProxy {

    @Autowired
    private CustomProperties props;

    /**
     * Get all employees
     * @return An iterable of all employees
     */

    public Iterable<Employee> getEmployees() {
        String baseApiUrl = props.getApiUrl();
        String getEmployeesUrl = baseApiUrl + "/employees";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(
                getEmployeesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Employee>>() {}
        );

        log.debug("Get Employees call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Employee getEmployee(final int id) {
        String baseApiUrl = props.getApiUrl();
        String getEmployeeUrl = baseApiUrl + "/employee/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> response = restTemplate.exchange(
                getEmployeeUrl,
                HttpMethod.GET,
                null,
                Employee.class);

        log.debug("Get Employees call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Employee updateEmployee(Employee employee) {
        String baseApiUrl = props.getApiUrl();
        String updateEmployeesUrl = baseApiUrl + "/employee/" + employee.getId();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> response = restTemplate.exchange(
                updateEmployeesUrl,
                HttpMethod.PUT,
                new HttpEntity<Employee>(employee),
                Employee.class);

        log.debug("Get Employees call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Employee createEmployee(Employee employee){
        String baseApiUrl = props.getApiUrl();
        String createEmployeeUrl = baseApiUrl + "/employee";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> request = new HttpEntity<Employee>(employee);
        ResponseEntity<Employee> response = restTemplate.exchange(
                createEmployeeUrl,
                HttpMethod.POST,
                request,
                Employee.class);

        log.debug("Create Employee call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public void deleteEmployee(final int id){
        String baseApiUrl = props.getApiUrl();
        String deleteEmployeeUrl = baseApiUrl + "/employee" + id;

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(deleteEmployeeUrl);
    }

}
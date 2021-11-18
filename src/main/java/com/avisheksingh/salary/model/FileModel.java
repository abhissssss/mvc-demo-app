package com.avisheksingh.salary.model;

import java.io.IOException;
import java.util.List;

public interface FileModel {
    void readData() throws IOException;
    EmployeeEntity getEmployeeById(Long employeeId);
    EmployeeEntity getEmployeeByName(String employeeName);
    void incrementSalary(EmployeeEntity employee, Integer amount);
    List<EmployeeEntity> getAllEmployees();
    List<EmployeeEntity> getEmployeeByNameIfSame (String employeeName);
    List<EmployeeEntity> getEmployeesWithSalaryInRange(final long salaryMin , final long salaryMax);
    Long getMaxId();


}

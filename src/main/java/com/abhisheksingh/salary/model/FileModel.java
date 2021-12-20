package com.abhisheksingh.salary.model;

import java.io.IOException;
import java.util.Set;

public interface FileModel {
    void readData() throws IOException;
    EmployeeEntity getEmployeeById(Long employeeId);
    EmployeeEntity getEmployeeByName(String employeeName);
    void incrementSalary(EmployeeEntity employee, Integer amount);
    Set<EmployeeEntity> getAllEmployees();
    Set<EmployeeEntity> getEmployeeByNameIfSame (String employeeName);
    Set<EmployeeEntity> getEmployeesWithSalaryInRange(final long salaryMin , final long salaryMax);
    Long getMaxId();
    void addNewEmployees (EmployeeEntity employee) throws IOException;
    void incrementSalaryById (final long employeeId , final int salary) throws IOException;
    void incrementSalaryByName (final String employeeName , final int incrementSalary) throws IOException;
    void removeEmployeeById (final long employeeId) throws InterruptedException, IOException;


}

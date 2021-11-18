package com.avisheksingh.salary.view;

public interface FileView {
    void showEmployees();
    void getEmployee(String employeeName);
    void increaseSalary(String employeeName, Integer salary);
    void getAllEmployeeWithSameName(String employeeName);
    void getAllEmployeeWithSalaryInRange (long salaryMin , long salaryMax);
}

package com.abhisheksingh.salary.view;

public interface FileView {
    void showEmployees();
    void getEmployee(String employeeName);
    void increaseSalary(String employeeName, Integer salary);
    void getAllEmployeeWithSameName(String employeeName);
    void getAllEmployeeWithSalaryInRange (long salaryMin , long salaryMax);
    void getIncrementedSalaryEmployee ( long employeeId , int incrementedSalary) ;
    void getIncrementedSalaryByGivenName (String employeeName , int incrementSalary);
    void removeEmployeeById (long employeeId);
}

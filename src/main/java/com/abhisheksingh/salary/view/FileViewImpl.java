package com.abhisheksingh.salary.view;

import com.abhisheksingh.salary.controller.FileController;
import com.abhisheksingh.salary.model.EmployeeEntity;

import java.io.PrintStream;
import java.util.List;

public record FileViewImpl(FileController fileController, PrintStream ps) implements FileView {

    @Override
    public void showEmployees() {
        printEmployeeEntityList(fileController.populateEmployees());
    }

    @Override
    public void removeEmployeeById(long employeeId) {
        fileController.removeEmployeeById(employeeId) ;
    }

    @Override
    public void getIncrementedSalaryByGivenName(String employeeName, int incrementSalary) {
        fileController.increaseSalaryByName(employeeName,incrementSalary);
    }

    @Override
    public void getIncrementedSalaryEmployee(long employeeId, int incrementedSalary) {
           fileController.increaseSalaryById(employeeId,incrementedSalary);
    }

    public void getAllEmployeeWithSalaryInRange(long salaryMin, long salaryMax) {
        printEmployeeEntityList(fileController.getEmployeesWithSalaryInRange(salaryMin, salaryMax));
    }

    @Override
    public void getAllEmployeeWithSameName(String employeeName) {
        printEmployeeEntityList(fileController.getSameEmployees(employeeName));
    }

    @Override
    public void getEmployee(String employeeName) {
        ps.println(fileController.getEmployee(employeeName));
    }

    @Override
    public void increaseSalary(String employeeName, Integer salary) {
        ps.println(fileController.increaseSalary(employeeName, salary));
    }


    private void printEmployeeEntityList(List<EmployeeEntity> employeeEntityList) {
        for (EmployeeEntity employee : employeeEntityList) {
            ps.println(employee);
            ps.println("------------------------");
        }
    }


}

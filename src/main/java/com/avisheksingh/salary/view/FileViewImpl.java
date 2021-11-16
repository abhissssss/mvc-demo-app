package com.avisheksingh.salary.view;

import com.avisheksingh.salary.controller.FileController;
import com.avisheksingh.salary.model.EmployeeEntity;

import java.io.PrintStream;
import java.util.List;

public record FileViewImpl(FileController fileController, PrintStream ps) implements FileView {

    @Override
    public void showEmployees() {
        printEmployeeEntityList(fileController.populateEmployees());
    }
public void getAllEmployeeWithSalaryInRange (long salaryMin , long salaryMax){
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

package com.avisheksingh.salary.view;

import com.avisheksingh.salary.controller.FileController;
import com.avisheksingh.salary.model.EmployeeEntity;

import java.io.PrintStream;
import java.util.List;

public class FileViewImpl implements FileView {
    private final FileController fileController;
    private final PrintStream ps;

    public FileViewImpl(FileController fileController, PrintStream ps) {
        this.fileController = fileController;
        this.ps = ps;
    }

    @Override
    public void showEmployees() {
        printEmployeeEntityList(fileController.populateEmployees());
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
            ps.println(employeeEntityList);
            ps.println("------------------------");
        }
    }
}

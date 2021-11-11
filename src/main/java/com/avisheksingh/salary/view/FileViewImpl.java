package com.avisheksingh.salary.view;

import com.avisheksingh.salary.controller.FileController;
import com.avisheksingh.salary.model.EmployeeEntity;

import java.io.PrintStream;

public class FileViewImpl implements FileView {
    private final FileController fileController;
    private final PrintStream ps;

    public FileViewImpl(FileController fileController, PrintStream ps) {
        this.fileController = fileController;
        this.ps = ps;
    }

    @Override
    public void showEmployees() {
        final var employees = fileController.populateEmployees();
        for (EmployeeEntity employee : employees) {
            ps.println(employee);
            ps.println("------------------------");
        }
    }

    @Override
    public void getEmployee(String employeeName) {
        ps.println(fileController.getEmployee(employeeName));
    }

    @Override
    public void increaseSalary(String employeeName, Integer salary) {
        ps.println(fileController.increaseSalary(employeeName, salary));
    }
}

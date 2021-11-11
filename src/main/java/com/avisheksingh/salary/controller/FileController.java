package com.avisheksingh.salary.controller;


import com.avisheksingh.salary.model.EmployeeEntity;
import com.avisheksingh.salary.model.FileModel;

import java.io.IOException;
import java.util.List;

public class FileController {
    private final FileModel fileModel; // data source is file, there is business logic

    public FileController(FileModel fileModel) {
        this.fileModel = fileModel;
        try {
            fileModel.readData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<EmployeeEntity> populateEmployees() {
        return fileModel.getAllEmployees();
    }

    public EmployeeEntity getEmployee(String employeeName) {
        return fileModel.getEmployeeByName(employeeName);
    }

    public EmployeeEntity increaseSalary(String employeeName, Integer salary) {
        final var employee = fileModel.getEmployeeByName(employeeName);
        fileModel.incrementSalary(employee, salary);
        return fileModel.getEmployeeById(employee.getId());
    }
}

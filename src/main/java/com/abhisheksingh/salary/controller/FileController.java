package com.abhisheksingh.salary.controller;


import com.abhisheksingh.salary.model.EmployeeEntity;
import com.abhisheksingh.salary.model.FileModel;

import java.io.IOException;
import java.util.List;

public record FileController(FileModel fileModel) {
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

    public List<EmployeeEntity> getSameEmployees(String employeeName) {
        return fileModel.getEmployeeByNameIfSame(employeeName);
    }

    public List <EmployeeEntity> getEmployeesWithSalaryInRange (final long salaryMin , final long salaryMax){
        return  fileModel.getEmployeesWithSalaryInRange(salaryMin,salaryMax);
    }
   public void increaseSalaryById (final long employeeId , final int incrementedValue ) {
       try {
            fileModel.incrementSalaryById(employeeId,incrementedValue);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   public void removeEmployeeById (long employeeId){

          try {
              fileModel.removeEmployeeById(employeeId);
          }
       catch (IOException | InterruptedException e){
          e.printStackTrace();
      }
   }
   public void increaseSalaryByName (final String employeeName , final int incrementSalary){
        try {
            fileModel.incrementSalaryByName(employeeName,incrementSalary);
        }
        catch (IOException e){
            e.printStackTrace();
        }
   }
}

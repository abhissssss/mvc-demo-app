package com.avisheksingh.salary.model;

import com.avisheksingh.salary.exceptions.EmployeeNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FileModelImpl implements FileModel {
    private final String location;
    // read file
    // parse data
    // perform some business logic
    private final List<EmployeeEntity> employees;


    public FileModelImpl(final String location) {
        this.location = location;
        this.employees = new ArrayList<>();
    }

    @Override
    public void readData() throws IOException {
        final var file = new File(location);
        final var fileInputStream = new FileInputStream(file);
        final var inputStreamReader = new InputStreamReader(fileInputStream);
        final var bufferedReader = new BufferedReader(inputStreamReader);

        fillUpEmployeesList(bufferedReader);
    }

    @Override
    public EmployeeEntity getEmployeeById(final Long employeeId) {
        if (null == employeeId) {
            throw new IllegalArgumentException("WTF?!");
        }
        for (EmployeeEntity employee : employees) {
            if (employeeId == employee.getId().longValue()) {
                return employee;
            }
        }

        throw new EmployeeNotFoundException(String.format("Employee with id: %d not found", employeeId));
    }

    @Override
    public List<EmployeeEntity> getEmployeesWithSalaryMoreThan1000(final long salaryMin, final long salaryMax) {
        if (salaryMin > salaryMax) {
            throw new IllegalArgumentException("WTF");
        }
        final var employeeWithMoreSalary = new ArrayList<EmployeeEntity>();
        for (final EmployeeEntity employee : employees) {
            final var employeeSalary = employee.getSalary().longValue();
            if ((salaryMax >= employeeSalary) && (employeeSalary >= salaryMin)) {
                employeeWithMoreSalary.add(employee);
            }
        }
        return employeeWithMoreSalary;
    }

    @Override
    public List<EmployeeEntity> getEmployeeByNameIfSame(String employeeName) {

        final var employeeDetails = new ArrayList<EmployeeEntity>();
        // Employees for only those whose name matches
        for (final EmployeeEntity employee : employees) {
            final var trimmedName = employeeName.replaceAll("\\s+", "")
                    .toLowerCase();
            final var trimmedNameFromPersistence = employee.getName()
                    .replaceAll("\\s+", "")
                    .toLowerCase();
            if (trimmedName.equals(trimmedNameFromPersistence)) {
                employeeDetails.add(employee);
            }
        }
        return employeeDetails;
    }

    @Override
    public EmployeeEntity getEmployeeByName(String employeeName) {
        if (null == employeeName || employeeName.isEmpty() || employeeName.isBlank()) {
            throw new IllegalArgumentException("WTF?!");
        }

        for (EmployeeEntity employee : employees) {
            final var trimmedName = employeeName.replaceAll("\\s+", "");
            final var trimmedNameFromPersistence = employee.getName().replaceAll("\\s+", "");
            if (trimmedName.equals(trimmedNameFromPersistence)) {
                return employee;
            }
        }

        throw new EmployeeNotFoundException(String.format("Employee with name: %s not found", employeeName));
    }

    @Override
    public Long getMaxId() {
        long max = employees.get(0).getId();
        for (EmployeeEntity employee : employees) {
            max = Math.max(max, employee.getId());
        }

        return max;
//        return Collections.max(employees.stream().map(EmployeeEntity::getId).collect(Collectors.toList()));
/*
        employees.stream()
                .max(Comparator.comparing(EmployeeEntity::getId))
                .map(EmployeeEntity::getId)
                .orElse(-1L);
*/
    }

    @Override
    public void incrementSalary(final EmployeeEntity employee, final Integer newSalary) {
        employee.setSalary(newSalary);
        // getEmployeeByName -> increaseSalary -> save to storage -> return updated employee
    }

    @Override
    public List<EmployeeEntity> getAllEmployees() {
        return this.employees;
    }

    private void fillUpEmployeesList(BufferedReader bufferedReader) throws IOException {
        String tempString;
        while ((tempString = bufferedReader.readLine()) != null) {
            // file structure
            // Sl.No. Name Salary
            // 1 Subba Rao 78  1478645
            final var splittingData = tempString.split("\\s+");
            final var employee = new EmployeeEntity(Long.parseLong(splittingData[0]));
            employee.setSalary(Integer.parseInt(splittingData[splittingData.length - 1]));

            final var names = new ArrayList<>(Arrays.asList(splittingData).subList(1, splittingData.length - 1));
            final var strArr = new String[names.size() - 1];
            employee.setName(String.join(" ", names.toArray(strArr)));

            employees.add(employee);
        }
    }
}

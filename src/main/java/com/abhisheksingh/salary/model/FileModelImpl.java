package com.abhisheksingh.salary.model;

import com.abhisheksingh.salary.exceptions.EmployeeNotFoundException;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class FileModelImpl implements FileModel {
    private final String location;
    // read file
    // parse data
    // perform some business logic
    private final Set<EmployeeEntity> employees;
    //Points to think over
    // Explore Set in java ?
    // When to use Binary Search Tree or HashTable ?
    // Time Complexity and Space complexity ?
    public FileModelImpl(final String location) {
        this.location = location;
        this.employees = new TreeSet<>();
    }

    @Override
    public void addNewEmployees(EmployeeEntity employee) throws IOException {
         // br.append(employee.toString());
         // Make sure that you have append flag set to true for FileWriter
        employees.add(employee);
        // saves employee in ram
        flushToFile();
    }

    private void flushToFile() throws IOException {
        final var bufferedWriter = new BufferedWriter(new FileWriter(location, false));
        bufferedWriter.write("");
        bufferedWriter.close();
        final var br = new BufferedWriter(new FileWriter(location, true));
        for (EmployeeEntity emp : employees) {
            br.write(emp.toString());
            //you can use append to which means saving the file to disk here
            // append meaning differs from class to class
            // write is a better option
            br.write(Character.LINE_SEPARATOR);
            // Here write and append can do the same tasks , but we prefer write
            // a b v m // 1 3 5 7 9
            // this is a character sequence which is interchangeable to an int and vice versa any int can be changed to a char and a char to an int by the primary principal of CS
            br.flush();
            //EmployeeEntity a1 = new EmployeeEntity(emp);
            // deep copy
            // EmployeeEntity a2 = emp;
            // EmployeeEntity a3 = a1;
        }
    }

    @Override
    public void removeEmployeeById(long employeeId) throws IOException {
        employees.remove(getEmployeeById(employeeId));
        flushToFile();

    }

    @Override
    public void readData() throws IOException {
        final var file = new File(location);
        final var fileInputStream = new FileInputStream(file);
        final var inputStreamReader = new InputStreamReader(fileInputStream);
        final var bufferedReader = new BufferedReader(inputStreamReader);

        initialize(bufferedReader);
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
    public Set<EmployeeEntity> getEmployeesWithSalaryInRange(final long salaryMin, final long salaryMax) {
        if (salaryMin > salaryMax) {
            throw new IllegalArgumentException("WTF");
        }
        final var employeeWithMoreSalary = new TreeSet<EmployeeEntity>();
        for (final EmployeeEntity employee : employees) {
            final var employeeSalary = employee.getSalary().longValue();
            if ((salaryMax >= employeeSalary) && (employeeSalary >= salaryMin)) {
                employeeWithMoreSalary.add(employee);
            }
        }
        return employeeWithMoreSalary;
    }

    @Override
    public Set<EmployeeEntity> getEmployeeByNameIfSame(String employeeName) {

        final var employeeDetails = new TreeSet<EmployeeEntity>();
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
        return Collections.max(employees.stream().map(EmployeeEntity::getId).collect(Collectors.toList()));
    }

    @Override
    public void incrementSalary(final EmployeeEntity employee, final Integer newSalary) {
        employee.setSalary(newSalary);
    }

    @Override
    public void incrementSalaryById(final long employeeId, final int incrementValue) throws IOException {
        EmployeeEntity employeeById = getEmployeeById(employeeId);
        employeeById.setSalary(employeeById.getSalary() + incrementValue);
        flushToFile();
    }

    @Override
    public void incrementSalaryByName(final String employeeName, final int incrementSalary) throws IOException {
        EmployeeEntity employeeByName = getEmployeeByName(employeeName);
        employeeByName.setSalary(employeeByName.getSalary() + incrementSalary);
        flushToFile();
    }

    @Override
    public Set<EmployeeEntity> getAllEmployees() {

        return this.employees;
    }

    private void initialize(BufferedReader bufferedReader) throws IOException {
        String tempString;
        while ((tempString = bufferedReader.readLine()) != null) {
            // file structure
            // Sl.No. Name Salary
            // 1 Subba Rao  1478645
            final var splittingData = tempString.split("\\s+"); // n = 4


            final var names = new ArrayList<>(Arrays.asList(splittingData).subList(1, splittingData.length - 1));
            final var strArr = new String[names.size()];
            final var employee = EmployeeEntity.builderFactory()
                    .setId(Long.parseLong(splittingData[0]))
                    .setName(String.join(" ", names.toArray(strArr)))
                    .setSalary(Integer.parseInt(splittingData[splittingData.length - 1]))
                    .build();

            employees.add(employee);
        }
    }
}

package com.abhisheksingh.salary;

import com.abhisheksingh.salary.controller.FileController;
import com.abhisheksingh.salary.view.FileViewImpl;
import com.abhisheksingh.salary.model.FileModelImpl;

public class Main {
    // MVC architecture
    // MVC - Model View Controller
    public static void main(String[] args)  {

        final var fileLocation = "D:\\Java_Projects\\Salary\\src\\main\\resources\\data_file.txt";
        final var fileModel = new FileModelImpl(fileLocation);
        final var fileController = new FileController(fileModel);
        final var printStream = System.out;
        final var fileView = new FileViewImpl(fileController, printStream);

       fileView.getAllEmployeeWithSameName("Bhaiya  Jee");
//        fileView.getAllEmployeeWithSameName("Bhaiya  jee");

//        fileView.getAllEmployeeWithSalaryInRange(20, Long.MAX_VALUE);
    }
}

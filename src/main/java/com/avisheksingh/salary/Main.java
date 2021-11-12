package com.avisheksingh.salary;

import com.avisheksingh.salary.controller.FileController;
import com.avisheksingh.salary.model.FileModelImpl;
import com.avisheksingh.salary.view.FileViewImpl;

public class Main {
    // MVC architecture
    // MVC - Model View Controller
    public static void main(String[] args) {
        final var fileLocation = "D:\\Java_Projects\\Salary\\src\\main\\resources\\data_file.txt";
        final var fileModel = new FileModelImpl(fileLocation);
        final var fileController = new FileController(fileModel);
        final var printStream = System.out;
        final var fileView = new FileViewImpl(fileController, printStream);

        fileView.getEmployee("Bhaiya  Jee");
        fileView.getAllEmployeeWithSameName("Bhaiya  jee");
    }
}

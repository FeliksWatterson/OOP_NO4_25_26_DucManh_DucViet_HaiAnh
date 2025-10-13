package oop_no4_25_26.ducmanh_ducviet_haianh.code.controller;

import oop_no4_25_26.ducmanh_ducviet_haianh.code.model.Customer;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/write")
public class WriteToFileController {

    private static final String FILE_PATH = "project/complete/File/customer.txt";

    @PostMapping("/customer")
    public String writeCustomerToFile(@RequestBody Customer customer) {
        try {
            Path dir = Paths.get("src/main/resources/data");
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
                writer.write(String.format("ID: %s | Name: %s | Email: %s | Phone: %s%n",
                        customer.getId(),
                        customer.getName(),
                        customer.getEmail(),
                        customer.getPhone()));
            }

            return "Customer saved to file successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error writing to file: " + e.getMessage();
        }
    }
}

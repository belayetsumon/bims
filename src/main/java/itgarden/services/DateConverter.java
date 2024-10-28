/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class DateConverter {
    // Method to convert date format
    public  String convertDateFormat(LocalDate originalDate) {
        // Parse the original date string
  
        
        // Define the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        // Format the date to the desired format
        return originalDate.format(formatter);
    }
    
    
    // Method to check if a string is null or empty
    private static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    // Method to convert and format date
    public  String convertDateFormat(String originalDate) {
        // Check for null or empty input
        if (isNullOrEmpty(originalDate)) {
            return "Invalid date"; // Handle as needed
        }
        
        try {
            // Parse the original date string
            LocalDate date = LocalDate.parse(originalDate);
        
            // Define the desired format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
            // Format the date to the desired format
            return date.format(formatter);
        } catch (Exception e) {
            return "Invalid date format"; // Handle parsing errors
        }
    }
}

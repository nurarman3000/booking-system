
package com.arman.booking.system;
import java.util.Scanner;

/**
 *
 * @author NurArman
 */


public class ClinicUtil {

    private static Scanner scanner = new Scanner(System.in);

    public static void addPatient(Clinic clinic) {
        System.out.print("\nEnter Patient ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Patient Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Patient Phone Number: ");
        String phone = scanner.nextLine();

        Patient patient = new Patient(id, name, address, phone);
        clinic.addPatient(patient);
        System.out.println("Patient " + name + " added successfully.");
    }

}


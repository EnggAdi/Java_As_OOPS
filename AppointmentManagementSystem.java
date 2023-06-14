package Assignments;

import java.util.Scanner;

class Visitor {
    String name;
    String contactNumber;
    String email;

    public Visitor(String name, String contactNumber, String email) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
    }
}

class Appointment {
    String date;
    String timeSlot;
    Visitor visitor;

    public Appointment(String date, String timeSlot, Visitor visitor) {
        this.date = date;
        this.timeSlot = timeSlot;
        this.visitor = visitor;
    }
}

public class AppointmentManagementSystem {
    private Visitor[] visitors;
    private Appointment[] appointments;
    private int visitorCount;
    private int appointmentCount;

    public AppointmentManagementSystem() {
        visitors = new Visitor[100];
        appointments = new Appointment[100];
        visitorCount = 0;
        appointmentCount = 0;
    }

    public void addVisitor(Visitor visitor) {
        visitors[visitorCount++] = visitor;
    }

    public void editVisitorDetails(int index, Visitor updatedVisitor) {
        if (index >= 0 && index < visitorCount) {
            visitors[index] = updatedVisitor;
        } else {
            System.out.println("Invalid visitor index.");
        }
    }

    public void viewVisitorsList() {
        if (visitorCount == 0) {
            System.out.println("No visitors found.");
        } else {
            System.out.println("Visitors List:");
            for (int i = 0; i < visitorCount; i++) {
                Visitor visitor = visitors[i];
                System.out.println("Visitor " + (i + 1) + ":");
                System.out.println("Name: " + visitor.name);
                System.out.println("Contact Number: " + visitor.contactNumber);
                System.out.println("Email: " + visitor.email);
                System.out.println("--------------------");
            }
        }
    }

    public void viewAppointmentSchedule(String date) {
        boolean hasAppointments = false;
        for (int i = 0; i < appointmentCount; i++) {
            Appointment appointment = appointments[i];
            if (appointment.date.equals(date)) {
                hasAppointments = true;
                System.out.println("Appointment:");
                System.out.println("Date: " + appointment.date);
                System.out.println("Time Slot: " + appointment.timeSlot);
                System.out.println("Visitor Name: " + appointment.visitor.name);
                System.out.println("Contact Number: " + appointment.visitor.contactNumber);
                System.out.println("Email: " + appointment.visitor.email);
                System.out.println("--------------------");
            }
        }
        if (!hasAppointments) {
            System.out.println("No appointments scheduled for the given date.");
        }
    }

    public static void main(String[] args) {
        AppointmentManagementSystem clinic = new AppointmentManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. View Visitors List");
            System.out.println("2. Add new Visitor");
            System.out.println("3. Edit Visitor Details");
            System.out.println("4. View Appointment Schedule for a Day");
            System.out.println("5. Book an Appointment");
            System.out.println("6. Edit/Cancel Appointment");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    clinic.viewVisitorsList();
                    break;
                case 2:
                    System.out.print("Enter Visitor Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Contact Number: ");
                    String contactNumber = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    Visitor visitor = new Visitor(name, contactNumber, email);
                    clinic.addVisitor(visitor);
                    System.out.println("Visitor added successfully.");
                    break;
                case 3:
                    System.out.print("Enter the index of the visitor to edit: ");
                    int index = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    if (index >= 1 && index <= clinic.visitorCount) {
                        System.out.print("Enter Visitor Name: ");
                        name = scanner.nextLine();
                        System.out.print("Enter Contact Number: ");
                        contactNumber = scanner.nextLine();
                        System.out.print("Enter Email: ");
                        email = scanner.nextLine();
                        visitor = new Visitor(name, contactNumber, email);
                        clinic.editVisitorDetails(index - 1, visitor);
                        System.out.println("Visitor details updated successfully.");
                    } else {
                        System.out.println("Invalid visitor index.");
                    }
                    break;
                case 4:
                    System.out.print("Enter the date to view the appointment schedule: ");
                    String date = scanner.nextLine();
                    clinic.viewAppointmentSchedule(date);
                    break;
                case 5:
                    // Implementation for booking an appointment
                    break;
                case 6:
                    // Implementation for editing/canceling an appointment
                    break;
                case 7:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

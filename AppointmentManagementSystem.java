import java.util.Scanner;

class Visitor {
    private String name;
    private String contactNumber;
    private String email;

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

class Appointment {
    private String date;
    private String timeSlot;
    private Visitor visitor;

    // Getter and Setter methods
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }
}

class Clinic {
    private Appointment[] appointments;
    private int appointmentCount;

    public Clinic(int maxAppointments) {
        appointments = new Appointment[maxAppointments];
        appointmentCount = 0;
    }

    public void addVisitor(Appointment appointment) {
        if (appointmentCount >= appointments.length) {
            System.out.println("Appointment slots are full. Cannot add a new visitor.");
            return;
        }

        appointments[appointmentCount] = appointment;
        appointmentCount++;

        System.out.println("Visitor and appointment added successfully.");
    }

    public void editVisitorDetails() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter visitor name to edit details: ");
        String visitorName = scanner.nextLine();

        for (int i = 0; i < appointmentCount; i++) {
            Appointment appointment = appointments[i];
            Visitor visitor = appointment.getVisitor();

            if (visitor.getName().equalsIgnoreCase(visitorName)) {
                System.out.print("Enter new contact number: ");
                visitor.setContactNumber(scanner.nextLine());
                System.out.print("Enter new email: ");
                visitor.setEmail(scanner.nextLine());
                System.out.println("Visitor details updated successfully.");
                return;
            }
        }

        System.out.println("Visitor not found with the given name.");
    }

    public void viewAppointmentSchedule(String date) {
        System.out.println("Appointment schedule for " + date + ":");

        boolean appointmentsFound = false;

        for (int i = 0; i < appointmentCount; i++) {
            Appointment appointment = appointments[i];

            if (appointment.getDate().equalsIgnoreCase(date)) {
                Visitor visitor = appointment.getVisitor();
                System.out.println("Time Slot: " + appointment.getTimeSlot());
                System.out.println("Visitor Name: " + visitor.getName());
                System.out.println("Contact Number: " + visitor.getContactNumber());
                System.out.println("Email: " + visitor.getEmail());
                System.out.println();
                appointmentsFound = true;
            }
        }

        if (!appointmentsFound) {
            System.out.println("No appointments found for the given date.");
        }
    }

    public void displayVisitors() {
        System.out.println("=== Visitors List ===");

        if (appointmentCount == 0) {
            System.out.println("No visitors found.");
        } else {
            for (int i = 0; i < appointmentCount; i++) {
                Appointment appointment = appointments[i];
                Visitor visitor = appointment.getVisitor();

                System.out.println("Visitor Name: " + visitor.getName());
                System.out.println("Contact Number: " + visitor.getContactNumber());
                System.out.println("Email: " + visitor.getEmail());
                System.out.println("Appointment Date: " + appointment.getDate());
                System.out.println("Appointment Time Slot: " + appointment.getTimeSlot());
                System.out.println();
            }
        }
    }
}

public class AppointmentManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Clinic clinic = new Clinic(100);

        // Sample records
        Visitor visitor1 = new Visitor();
        visitor1.setName("John Doe");
        visitor1.setContactNumber("9876543210");
        visitor1.setEmail("john@example.com");

        Appointment appointment1 = new Appointment();
        appointment1.setDate("24/06/2023");
        appointment1.setTimeSlot("10:00 AM");
        appointment1.setVisitor(visitor1);
        clinic.addVisitor(appointment1);

        Visitor visitor2 = new Visitor();
        visitor2.setName("Jane Smith");
        visitor2.setContactNumber("1234567890");
        visitor2.setEmail("jane@example.com");

        Appointment appointment2 = new Appointment();
        appointment2.setDate("24/06/2023");
        appointment2.setTimeSlot("11:00 AM");
        appointment2.setVisitor(visitor2);
        clinic.addVisitor(appointment2);

        while (true) {
            System.out.println("=== Appointment Management System ===");
            System.out.println("1. Visitors List");
            System.out.println("2. Add new Visitor");
            System.out.println("3. Edit Visitor Details");
            System.out.println("4. View Appointment Schedule for a Day");
            System.out.println("5. Book an Appointment");
            System.out.println("6. Edit/Cancel Appointment");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("=== Visitors List ===");
                    clinic.displayVisitors();
                    break;

                case 2:
                    System.out.println("=== Add new Visitor ===");
                    System.out.print("Enter visitor name: ");
                    String visitorName = scanner.nextLine();
                    System.out.print("Enter contact number: ");
                    String contactNumber = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();

                    Visitor visitor = new Visitor();
                    visitor.setName(visitorName);
                    visitor.setContactNumber(contactNumber);
                    visitor.setEmail(email);

                    Appointment newAppointment = new Appointment();
                    System.out.print("Enter appointment date (DD/MM/YYYY): ");
                    String appointmentDate = scanner.nextLine();
                    System.out.print("Enter appointment time slot: ");
                    String appointmentTimeSlot = scanner.nextLine();

                    newAppointment.setDate(appointmentDate);
                    newAppointment.setTimeSlot(appointmentTimeSlot);
                    newAppointment.setVisitor(visitor);

                    clinic.addVisitor(newAppointment);
                    break;

                case 3:
                    System.out.println("=== Edit Visitor Details ===");
                    clinic.editVisitorDetails();
                    break;

                case 4:
                    System.out.println("=== View Appointment Schedule for a Day ===");
                    System.out.print("Enter date (DD/MM/YYYY): ");
                    String date = scanner.nextLine();
                    clinic.viewAppointmentSchedule(date);
                    break;

                case 5:
                    System.out.println("=== Book an Appointment ===");
                    // Implementation for booking a new appointment
                    break;

                case 6:
                    System.out.println("=== Edit/Cancel Appointment ===");
                    // Implementation for editing/canceling an appointment
                    break;

                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }
}

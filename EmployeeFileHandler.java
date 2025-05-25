import java.io.*;
import java.util.Scanner;

public class EmployeeFileHandler {
    private static final String FILE_NAME = "employees.txt";

    // Ensure file is auto-created if it doesn't exist
    static {
        try {
            File file = new File(FILE_NAME);
            if (file.createNewFile()) {
                System.out.println("Employee data file created.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }
    }

    // Create
    public static void addEmployee(String id, String name, String department) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(id + "," + name + "," + department);
            writer.newLine();
            System.out.println("Employee added.");
        } catch (IOException e) {
            System.out.println("Error adding employee.");
            e.printStackTrace();
        }
    }

    // Read
    public static void viewEmployees() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n--- Employee List ---");
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                System.out.println("ID: " + data[0] + ", Name: " + data[1] + ", Dept: " + data[2]);
            }
            System.out.println("----------------------\n");
        } catch (IOException e) {
            System.out.println("Error reading employee file.");
            e.printStackTrace();
        }
    }

    // Update
    public static void updateEmployee(String id, String newName, String newDepartment) {
        File inputFile = new File(FILE_NAME);
        File tempFile = new File("temp.txt");

        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String line;
            boolean updated = false;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(id)) {
                    writer.write(id + "," + newName + "," + newDepartment);
                    updated = true;
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }

            if (updated) {
                if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                    System.out.println("Employee updated.");
                } else {
                    System.out.println("Error updating employee.");
                }
            } else {
                System.out.println("Employee ID not found.");
            }
        } catch (IOException e) {
            System.out.println("Error updating employee.");
            e.printStackTrace();
        }
    }

    // Delete
    public static void deleteEmployee(String id) {
        File inputFile = new File(FILE_NAME);
        File tempFile = new File("temp.txt");

        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String line;
            boolean deleted = false;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (!data[0].equals(id)) {
                    writer.write(line);
                    writer.newLine();
                } else {
                    deleted = true;
                }
            }

            if (deleted) {
                if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                    System.out.println("Employee deleted.");
                } else {
                    System.out.println("Error deleting employee.");
                }
            } else {
                System.out.println("Employee ID not found.");
            }
        } catch (IOException e) {
            System.out.println("Error deleting employee.");
            e.printStackTrace();
        }
    }

    // Main method for testing CRUD from console
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Department: ");
                    String dept = scanner.nextLine();
                    addEmployee(id, name, dept);
                    break;
                case 2:
                    viewEmployees();
                    break;
                case 3:
                    System.out.print("Enter ID to update: ");
                    String updateId = scanner.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter New Department: ");
                    String newDept = scanner.nextLine();
                    updateEmployee(updateId, newName, newDept);
                    break;
                case 4:
                    System.out.print("Enter ID to delete: ");
                    String deleteId = scanner.nextLine();
                    deleteEmployee(deleteId);
                    break;
                case 5:
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        scanner.close();
    }
}
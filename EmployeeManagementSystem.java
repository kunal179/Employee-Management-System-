
import java.util.*;
import java.util.regex.*;

class Employee {
    private int id;
    private String name;
    private String email;
    private int age;

    public Employee(int id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return String.format("ID: %d | Name: %s | Email: %s | Age: %d", id, name, email, age);
    }
}

public class EmployeeManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static List<Employee> employeeList = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Employee Management Menu =====");
            System.out.println("1. Add Employee");
            System.out.println("2. List Employees");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    addEmployee();
                    break;
                case "2":
                    listEmployees();
                    break;
                case "3":
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("❌ Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    public static void addEmployee() {
        try {
            System.out.print("Enter Employee ID (number): ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Employee Name: ");
            String name = sc.nextLine();
            if (name.trim().isEmpty() || name.matches(".*\d.*")) {
                System.out.println("❌ Invalid name. Please avoid numbers or empty names.");
                return;
            }

            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            if (!Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email)) {
                System.out.println("❌ Invalid email format.");
                return;
            }

            System.out.print("Enter Age: ");
            int age = Integer.parseInt(sc.nextLine());
            if (age < 18 || age > 65) {
                System.out.println("❌ Age must be between 18 and 65.");
                return;
            }

            Employee emp = new Employee(id, name, email, age);
            employeeList.add(emp);

            System.out.println("✅ Employee added successfully!");
            System.out.println(emp.toString());

        } catch (NumberFormatException e) {
            System.out.println("❌ Please enter valid numeric values for ID and Age.");
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

    public static void listEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees to display.");
            return;
        }

        System.out.println("\n---- Employee List ----");
        for (Employee emp : employeeList) {
            System.out.println(emp.toString());
        }
    }
}


import java.io.*;
import java.util.*;

public class EmployeeManager {

    private static final String FILE_NAME = "employees.csv";

    // Save a list of employees to a CSV file
    public static void saveEmployees(List<Employee> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Employee emp : employees) {
                writer.write(emp.toCSV());
                writer.newLine();
            }
            System.out.println("Employees saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving employees: " + e.getMessage());
        }
    }

    // Load employees from a CSV file
    public static List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Employee emp = Employee.fromCSV(line);
                if (emp != null) {
                    employees.add(emp);
                }
            }
            System.out.println("Employees loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading employees: " + e.getMessage());
        }
        return employees;
    }

    // Example usage
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Alice", "HR", 50000));
        employees.add(new Employee(2, "Bob", "IT", 60000));

        // Save employees to file
        saveEmployees(employees);

        // Load employees from file
        List<Employee> loadedEmployees = loadEmployees();
        for (Employee emp : loadedEmployees) {
            System.out.println(emp.getId() + ": " + emp.getName() + ", " + emp.getDepartment() + ", " + emp.getSalary());
        }
    }
}





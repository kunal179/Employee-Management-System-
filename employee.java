public class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    // Constructor
    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    // Converts the Employee object to a CSV-formatted string
    public String toCSV() {
        return String.format("%d,%s,%s,%.2f", id, name, department, salary);
    }

    // Creates an Employee object from a CSV-formatted string
    public static Employee fromCSV(String line) {
        if (line == null || line.trim().isEmpty()) return null;
        String[] parts = line.split(",");
        if (parts.length != 4) return null;
        try {
            int id = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            String department = parts[2].trim();
            double salary = Double.parseDouble(parts[3].trim());
            return new Employee(id, name, department, salary);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

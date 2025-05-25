public class Main {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        EmployeeService employeeService = new EmployeeServiceImpl(employeeDAO);
        EmployeeController controller = new EmployeeController(employeeService);

        // Example usage
        Employee emp = new Employee(1, "John Doe", "Engineering", 75000);
        controller.addEmployee(emp);
    }
}

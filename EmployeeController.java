public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void addEmployee(Employee employee) {
        employeeService.createEmployee(employee);
    }

    // Other methods to handle user interactions
}

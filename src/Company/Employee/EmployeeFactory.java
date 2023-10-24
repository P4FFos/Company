package Company.Employee;

public class EmployeeFactory {
    public static Employee createEmployee(String employeeID, String employeeName, double grossSalary) {
        return new Employee(employeeID, employeeName, grossSalary);
    }

    public static Employee createIntern(String employeeID, String employeeName, double grossSalary, int GPA) {
        return new Intern(employeeID, employeeName, grossSalary, GPA);
    }

    public static Employee createManager(String employeeID, String employeeName, double grossSalary, String degree) {
        return new Manager(employeeID, employeeName, grossSalary, degree);
    }

    public static Employee createDirector(String employeeID, String employeeName, double grossSalary, String degree, String department) {
        return new Director(employeeID, employeeName, grossSalary, degree, department);
    }
}

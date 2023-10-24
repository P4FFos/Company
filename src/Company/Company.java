package Company;

import Company.Employee.*;
import Company.Exceptions.EmployeeAlreadyExistsException;
import Company.Exceptions.InvalidEmployeeDataException;
import Company.Exceptions.NoEmployeeFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Company {
    private ArrayList<Employee> employees;
    private static final String EOL = System.lineSeparator();

    // constructor for the company class
    public Company() {
        this.employees = new ArrayList<>();
    }

    // method to create an employee
    public String createEmployee(String employeeID, String employeeName, double grossSalary) {
        checkIfEmployeeAlreadyExists(employeeID);
        Employee employee = EmployeeFactory.createEmployee(employeeID, employeeName, grossSalary);
        return addEmployee(employee, employeeID);
    }

    // method to create an intern
    public String createEmployee(String employeeID, String employeeName, double grossSalary, int GPA) {
        checkIfEmployeeAlreadyExists(employeeID);
        Employee employee = EmployeeFactory.createIntern(employeeID, employeeName, grossSalary, GPA);
        return addEmployee(employee, employeeID);
    }

    // method to create a manager
    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree) {
        checkIfEmployeeAlreadyExists(employeeID);
        Employee employee = EmployeeFactory.createManager(employeeID, employeeName, grossSalary, degree);
        return addEmployee(employee, employeeID);
    }

    // method to create a director
    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree, String department) {
        checkIfEmployeeAlreadyExists(employeeID);
        Employee employee = EmployeeFactory.createDirector(employeeID, employeeName, grossSalary, degree, department);
        return addEmployee(employee, employeeID);
    }

    // this method adds employee to the array list of employees
    // and returns a message that employee was registered successfully
    public String addEmployee(Employee employee, String employeeID) {
        employees.add(employee);
        return String.format("Company.Employee.Employee %s was registered successfully.", employeeID);
    }

    // this method return Net Salary of the employee
    public double getNetSalary(String employeeID) {
        Employee employee = findEmployee(employeeID);
        if (employee != null) {
            return employee.getNetSalary();
        }
        return 0;
    }

    // this method return total Net Salary of each employee in the company
    public double getTotalNetSalary() {
        checkIfThereAreEmployees();
        double totalNetSalary = 0.00;
        for (Employee employee : employees) {
            totalNetSalary += employee.getNetSalary();
        }
        return totalNetSalary;
    }

    // method update employee name
    // firstly find employee by his/her ID,
    // then set a new name
    public String updateEmployeeName(String employeeID, String newName) {
        checkIfNameIsBlank(newName);
        Employee foundEmployee = findEmployee(employeeID);
        foundEmployee.setEmployeeName(newName);
        return String.format("Company.Employee.Employee %s was updated successfully", employeeID);
    }

    // method update employee gross salary
    // firstly find employee by his/her ID,
    // then set a new salary
    public String updateGrossSalary(String employeeID, double newSalary) {
        checkIfSalaryGreaterZero(newSalary);
        Employee foundEmployee = findEmployee(employeeID);
        foundEmployee.setGrossSalary(newSalary);
        return String.format("Company.Employee.Employee %s was updated successfully", employeeID);
    }

    // method update intern GPA
    // firstly find intern by his/her ID,
    // then set a new GPA
    public String updateInternGPA(String employeeID, int newGPA) {
        checkIfGPAisValid(newGPA);
        Employee foundEmployee = findEmployee(employeeID);
        Intern employee = (Intern) foundEmployee;
        employee.setGPA(newGPA);
        return String.format("Company.Employee.Employee %s was updated successfully", employeeID);
    }

    // method update manager degree
    // firstly find manager by his/her ID,
    // then set a new degree
    public String updateManagerDegree(String employeeID, String newDegree) {
        checkIfDegreeIsValid(newDegree);
        Employee foundEmployee = findEmployee(employeeID);
        Manager employee = (Manager) foundEmployee;
        employee.setDegree(newDegree);
        return String.format("Company.Employee.Employee %s was updated successfully", employeeID);
    }

    // method update director department
    // firstly find director by his/her ID
    // then set a new department
    public String updateDirectorDept(String employeeID, String newDepartment) {
        checkIfDeptIsValid(newDepartment);
        Employee foundEmployee = findEmployee(employeeID);
        Director employee = (Director) foundEmployee;
        employee.setDepartment(newDepartment);
        return String.format("Company.Employee.Employee %s was updated successfully", employeeID);
    }

    // method remove employee from the arrayList of the company
    public String removeEmployee(String employeeID) {
        Employee foundEmployee = findEmployee(employeeID);
        employees.remove(foundEmployee);
        return String.format("Company.Employee.Employee %s was successfully removed.", employeeID);
    }

    // method print employee by his/her ID
    public String printEmployee(String employeeID) {
        Employee foundEmployee = findEmployee(employeeID);
        return foundEmployee.toString();
    }

    // method store all employees information in a String format
    private String allEmployeesData(String message) {
        checkIfThereAreEmployees();
        for (Employee employee : employees) {
            message += employee + EOL;
        }
        return message;
    }

    // method print all registered employees in the company
    public String printAllEmployees() {
        String message = ("All registered employees:" + EOL);
        return allEmployeesData(message);
    }

    // method print all registered employees in the company sorted by gross salary
    public String printSortedEmployees() {
        Collections.sort(employees);
        String message = "Employees sorted by gross salary (ascending order):" + EOL;
        return allEmployeesData(message);
    }

    // method to get amount of each employee with degree in the company
    public HashMap<String, Integer> mapEachDegree() {
        checkIfThereAreEmployees();
        HashMap<String, Integer> eachDegreeNumber = new HashMap<>();
        for (Employee employee : employees) {
            if (employee instanceof Manager) {
                String degree = ((Manager) employee).getDegree();
                eachDegreeNumber.put(degree, eachDegreeNumber.getOrDefault(degree, 0) + 1);
            }
        }
        return eachDegreeNumber;
    }

    public String promoteToIntern(String employeeID, int GPA) {
        Employee foundEmployee = findEmployee(employeeID);
        String name = foundEmployee.getEmployeeName();
        double grossSalary = foundEmployee.getRawGrossSalary();
        Employee intern = EmployeeFactory.createIntern(employeeID, name, grossSalary, GPA);
        employees.remove(foundEmployee);
        employees.add(intern);
        return String.format("%s promoted successfully to Intern.", employeeID);
    }

    public String promoteToManager(String employeeID, String degree) {
        Employee foundEmployee = findEmployee(employeeID);
        String name = foundEmployee.getEmployeeName();
        double grossSalary = foundEmployee.getRawGrossSalary();
        Employee manager = EmployeeFactory.createManager(employeeID, name, grossSalary, degree);
        employees.remove(foundEmployee);
        employees.add(manager);
        return String.format("%s promoted successfully to Manager.", employeeID);
    }

    public String promoteToDirector(String employeeID, String degree, String department) {
        Employee foundEmployee = findEmployee(employeeID);
        String name = foundEmployee.getEmployeeName();
        double grossSalary = foundEmployee.getRawGrossSalary();
        Employee director = EmployeeFactory.createDirector(employeeID, name, grossSalary, degree, department);
        employees.remove(foundEmployee);
        employees.add(director);
        return String.format("%s promoted successfully to Director.", employeeID);
    }

    // Exceptions
    // method to find employee in the company by his/her ID
    private Employee findEmployee(String employeeID) throws NoEmployeeFoundException {
        for (Employee employee : employees) {
            if (employee.getEmployeeID().equals(employeeID)) {
                return employee;
            }
        }
        throw new NoEmployeeFoundException("Employee " + employeeID + " was not registered yet.");
    }

    public void checkIfEmployeeAlreadyExists(String employeeID) throws EmployeeAlreadyExistsException {
        for (Employee employee : employees) {
            if (employee.getEmployeeID().equals(employeeID)) {
                throw new EmployeeAlreadyExistsException("Cannot register. ID " + employeeID + " is already registered.");
            }
        }
    }

    public void checkIfThereAreEmployees() throws NoEmployeeFoundException {
        if (employees.isEmpty()) {
            throw new NoEmployeeFoundException("No employees registered yet.");
        }
    }

    public void checkIfNameIsBlank(String newName) throws InvalidEmployeeDataException {
        if (newName.isBlank()) {
            throw new InvalidEmployeeDataException("Name cannot be blank.");
        }
    }

    public void checkIfSalaryGreaterZero(double newSalary) throws InvalidEmployeeDataException {
        if (newSalary <= 0) {
            throw new InvalidEmployeeDataException("Salary must be greater than zero.");
        }
    }

    public void checkIfGPAisValid(int newGPA) throws InvalidEmployeeDataException {
        if (newGPA < 0 || newGPA > 10) {
            throw new InvalidEmployeeDataException(newGPA + " outside range. Must be between 0-10.");
        }
    }

    public boolean isValidDegree(String newDegree) {
        boolean isValidDegree = false;
        isValidDegree = "BSc".equals(newDegree) || "MSc".equals(newDegree) || "PhD".equals(newDegree);
        return isValidDegree;
    }

    public void checkIfDegreeIsValid(String newDegree) throws InvalidEmployeeDataException {
        if (!isValidDegree(newDegree)) {
            throw new InvalidEmployeeDataException("Degree must be one of the options: BSc, MSc or PhD.");
        }
    }

    public boolean isValidDept(String newDept) {
        boolean isValidDept = false;
        isValidDept = newDept.equals("Business") || newDept.equals("Human Resources") || newDept.equals("Technical");
        return isValidDept;
    }

    public void checkIfDeptIsValid(String newDept) throws InvalidEmployeeDataException {
        if (!isValidDept(newDept)) {
            throw new InvalidEmployeeDataException("Department must be one of the options: Business, Human Resources or Technical.");
        }
    }
}
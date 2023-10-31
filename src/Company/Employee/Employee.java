package Company.Employee;

import Company.Exceptions.InvalidEmployeeDataException;
import Company.Util.Util;

public class Employee implements Comparable<Employee>{
    private final static double LOW_TAX = 0.1;
    private final String employeeID;
    private String employeeName;
    private double grossSalary;


    public Employee(String employeeID, String employeeName, double grossSalary) throws InvalidEmployeeDataException {
        if (employeeID.isBlank()) {
            throw new InvalidEmployeeDataException("ID cannot be blank.");
        }
        if (employeeName.isBlank()) {
            throw new InvalidEmployeeDataException("Name cannot be blank.");
        }
        if (grossSalary <= 0) {
            throw new InvalidEmployeeDataException("Salary must be greater than zero.");
        }
        this.employeeName = employeeName;
        this.employeeID = employeeID;
        this.grossSalary = Util.truncateSalary(grossSalary);
        }

    public boolean equals(Employee anotherObject) {
        boolean isEqual = false;
        if (anotherObject == this) {
            isEqual = true;
        } else if (anotherObject == null) {
            isEqual = false;
        } else {
            Employee anotherEmployee = (Employee) anotherObject;
            boolean sameID = this.employeeID.equals(anotherEmployee.getEmployeeID());
            isEqual = sameID;
        }
        return isEqual;
    }

    public String printEmployee() {
        return toString();
    }

    @Override
    public String toString() {
        String message = String.format("%s's gross salary is %.2f SEK per month.", employeeName, this.getGrossSalary());
        return message;
    }

    @Override
    public int compareTo(Employee anotherEmployee) {
        if (this.getGrossSalary() > anotherEmployee.getGrossSalary()) {
            return 1;
        } else if (this.getGrossSalary() == anotherEmployee.getGrossSalary()) {
            return 0;
        } else {
            return -1;
        }
    }

    //Getters and setters
    public String getEmployeeID() {
        return employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String newName) {
        this.employeeName = newName;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public double getRawGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double newSalary) {
        this.grossSalary = Util.truncateSalary(newSalary);
    }

    public double getNetSalary() {
        return Util.truncateSalary(grossSalary * (1 - LOW_TAX));
    }
}
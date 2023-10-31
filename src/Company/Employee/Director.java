package Company.Employee;

import Company.Exceptions.InvalidEmployeeDataException;
import Company.Util.Util;


public class Director extends Manager {

    private String department;
    private final static double LOW_TAX = 0.1;
    private final static double HIGH_TAX = 0.2;

    public Director(String employeeID, String employeeName, double grossSalary, String degree, String department) throws InvalidEmployeeDataException {
        super(employeeID, employeeName, grossSalary, degree);
        if (!department.equals("Business") && !department.equals("Human Resources") && !department.equals("Technical")) {
            throw new InvalidEmployeeDataException("Department must be one of the options: Business, Human Resources or Technical.");
        }
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public String setDepartment(String newDepartment) {
        return this.department = newDepartment;
    }

    @Override
    public double getGrossSalary() {
        return super.getGrossSalary() + 5000.00;
    }

    @Override
    public double getNetSalary() {
        double currentGrossSalary = this.getGrossSalary();
        if (currentGrossSalary < 30000) {
            return Util.truncateSalary(currentGrossSalary * (1 - LOW_TAX));
        } else if (currentGrossSalary < 50000) {
            return Util.truncateSalary(currentGrossSalary * (1 - HIGH_TAX));
        } else {
            double taxForSalary = (HIGH_TAX * 30000) + ((2 * HIGH_TAX) * (currentGrossSalary - 30000));
            return Util.truncateSalary(currentGrossSalary - taxForSalary);
        }
    }

    @Override
    public String toString() {
        String message = super.toString() + " Dept: " + department;;
        return message;
    }

    @Override
    public String printEmployee() {
        return toString();
    }
}
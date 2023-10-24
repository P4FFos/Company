package Company.Employee;

import assignment3.Exceptions.InvalidEmployeeDataException;
import assignment3.Util.Util;

public class Manager extends Employee {
    // private String degree;
    private Degree degree;
    private final static double LOW_TAX = 0.1;

    public Manager(String employeeID, String employeeName, double grossSalary, String degree) throws InvalidEmployeeDataException {
        super(employeeID, employeeName, grossSalary);
        if (!degree.equals("BSc") && !degree.equals("MSc") && !degree.equals("PhD")) {
            throw new InvalidEmployeeDataException("Degree must be one of the options: BSc, MSc or PhD.");
        }
        this.degree = convertStringToEnum(degree);
        }

    // Converting from String to enum
    public Degree convertStringToEnum(String degree) {
        switch (degree) {
            case "BSc":
                return Degree.BSC;
            case "MSc":
                return Degree.MSC;
            case "PhD":
                return Degree.PHD;
        }
        return null;
    }

    public String getDegree() {
        return degree.getLabel();
    }

    public void setDegree(String newDegree) {
        this.degree = convertStringToEnum(newDegree);
    }

    @Override
    public double getGrossSalary() {
        double salaryBonus = this.degree.getSalaryBonus();;
        double salaryWithBonus = super.getGrossSalary();
        salaryWithBonus = super.getGrossSalary() * salaryBonus;
        return Util.truncateSalary(salaryWithBonus); 
        }

    @Override
    public double getNetSalary() {
        return Util.truncateSalary(getGrossSalary() * (1 - LOW_TAX));
    }

    @Override
    public String toString() {
        String message = String.format("%s %s's gross salary is %.2f SEK per month.", degree.getLabel(), super.getEmployeeName(), this.getGrossSalary());
        return message;
    }

    @Override
    public String printEmployee() {
        return toString();
    }  
}
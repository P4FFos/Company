package Company.Employee;

import Company.Exceptions.InvalidEmployeeDataException;

public class Intern extends Employee {
    private int GPA;

    public Intern(String employeeID, String employeeName, double grossSalary, int GPA) throws InvalidEmployeeDataException {
        super(employeeID, employeeName, grossSalary);
        if (GPA < 0 || GPA > 10) {
            throw new InvalidEmployeeDataException(GPA + " outside range. Must be between 0-10.");
        }
        this.GPA = GPA;
        }
    

    public int getGPA() {
        return getGPA();
    }

    public int setGPA(int newGPA) {
        return this.GPA = newGPA;
    }

    @Override
    public double getGrossSalary() {
        double salary = 0;
        if (GPA <= 5) {
            salary = 0;
        } else if (GPA > 5 && GPA < 8) {
            salary = super.getGrossSalary();
        } else if (GPA >= 8) {
            double bonusSalary = 1000;
            salary = super.getGrossSalary() + bonusSalary;
        }
        return salary;
    }

    @Override
    public double getNetSalary() {
        return getGrossSalary();
    }

    @Override
    public String toString() {
        String message = super.toString() + " GPA: " + GPA;
        return message;
    }

    @Override
    public String printEmployee() {
        return toString();
    }
}
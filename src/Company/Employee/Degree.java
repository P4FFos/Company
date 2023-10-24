package Company.Employee;

public enum Degree {
    BSC("BSc", 1.10),
    MSC("MSc", 1.20),
    PHD("PhD", 1.35);

    private String degree;
    private final double salaryBonus;

    Degree(String degree, double salaryBonus) {
        this.degree = degree;
        this.salaryBonus = salaryBonus;
    }

    public String getLabel() {
        return degree;
    }

    public double getSalaryBonus() {
        return salaryBonus;
    }
}

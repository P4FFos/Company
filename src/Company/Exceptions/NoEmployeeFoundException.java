package Company.Exceptions;

public class NoEmployeeFoundException extends InvalidEmployeeDataException {
    public NoEmployeeFoundException(String message) {
        super(message);
    }
}
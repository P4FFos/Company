package Company.Exceptions;

public class EmployeeAlreadyExistsException extends InvalidEmployeeDataException {
    public EmployeeAlreadyExistsException(String message) {
        super(message);
    }
}
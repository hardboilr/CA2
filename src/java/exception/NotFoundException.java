package exception;

public class NotFoundException extends Exception {

    public NotFoundException(String string) {
        super(string);
    }

    public NotFoundException() {
        super("Company with requested cvr not found");
    }
}

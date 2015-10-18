package exception;

public class ExistException extends Exception {

    String phone;
    public ExistException(String string) {
        super(string);
    }
}

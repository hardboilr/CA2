package exception;

public class PhoneExistException extends Exception {

    public PhoneExistException(String string) {
        super(string);
    }

    public PhoneExistException() {
        super("Phonenumber already exist's. Please choose another");
    }
}

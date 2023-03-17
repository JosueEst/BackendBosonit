public class InvalidLineFormatException  extends java.lang.Exception {

    String message;

    public InvalidLineFormatException (String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

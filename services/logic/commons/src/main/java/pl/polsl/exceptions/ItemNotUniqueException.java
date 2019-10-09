package pl.polsl.exceptions;

public class ItemNotUniqueException extends RuntimeException {
    public enum ExceptionType {
        NAME_NOT_UNIQUE("Username is already taken!"),
        EMAIL_NOT_UNIQUE("Email is already taken!");
        private String message;

        public String getMessage() {
            return message;
        }

        ExceptionType(String s) {
            message=s;
        }
    }

    public ItemNotUniqueException(String message) {
        super(message);
    }

    public ItemNotUniqueException(ExceptionType exception) {
        super(exception.getMessage());
    }
}

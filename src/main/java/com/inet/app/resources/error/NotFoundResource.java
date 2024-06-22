package  com.inet.app.resources.error;

public class NotFoundResource {
    class Error {
        public String message;
    }

    public Boolean success = false;

    public Error error = new Error();

    public NotFoundResource(String message) {
        error.message = message;
    }
}

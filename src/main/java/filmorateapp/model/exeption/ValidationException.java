package filmorateapp.model.exeption;

public class ValidationException extends RuntimeException {
    public ValidationException(String massage) {
        super(massage);
    }
}
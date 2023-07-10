package filmorateapp.model.controller;

import filmorateapp.model.ExceptionMessage;
import filmorateapp.model.exeption.DataNotFoundException;
import filmorateapp.model.exeption.InstanceAlreadyExistException;
import filmorateapp.model.exeption.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessage handleValidationException(final ValidationException ex) {
        log.info(ex.getMessage());
        return new ExceptionMessage(ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionMessage handleInstanceAlreadyExistException(final InstanceAlreadyExistException ex) {
        log.info(ex.getMessage());
        return new ExceptionMessage(ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionMessage handleInstanceNotFoundException(final DataNotFoundException ex) {
        log.info(ex.getMessage());
        return new ExceptionMessage(ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionMessage errorMessage(final Throwable ex) {
        log.info(ex.getMessage());
        return new ExceptionMessage(ex.getMessage());
    }
}
package br.com.marcondesmacaneiro.domain.exception;

import java.util.function.Supplier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author marcondes
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidEntityException extends RuntimeException {

    public InvalidEntityException(String message) {
        super(message);
    }

    public InvalidEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEntityException(Throwable cause) {
        super(cause);
    }

    public static Supplier<InvalidEntityException> invalidEntityException(String format, Object... args) {
        return () -> new InvalidEntityException(String.format(format, args));
    }
}

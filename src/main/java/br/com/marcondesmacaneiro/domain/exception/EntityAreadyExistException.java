package br.com.marcondesmacaneiro.domain.exception;

import java.util.function.Supplier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author marcondes
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class EntityAreadyExistException extends RuntimeException {

    public EntityAreadyExistException(String message) {
        super(message);
    }

    public EntityAreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityAreadyExistException(Throwable cause) {
        super(cause);
    }
    
    public static Supplier<EntityAreadyExistException> entityAreadyExist (String message, Object... args) {
        return () -> new EntityAreadyExistException(String.format(message, args));
    }
    
}

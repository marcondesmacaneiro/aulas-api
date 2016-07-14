package br.com.example.util;

import br.com.example.domain.exception.EntityNotFoundException;
import br.com.example.domain.exception.InvalidArgumentException;
import br.com.example.domain.exception.NullArgumentException;

import java.util.Objects;

/**
 * Created by marcondesmacaneiro on 12/07/16.
 */
public interface Preconditions {

    static <T> T checkNotNull(T value, String message) {
        if (Objects.isNull(value)) {
            throw new NullArgumentException(message);
        }
        return value;
    }

    static <T> T checkEntityNotFound(T value, String message) {
        if (Objects.isNull(value)) {
            throw new EntityNotFoundException(message);
        }
        return value;
    }

    static void checkArgument(boolean valid, String message) {
        if (!valid) {
            throw new InvalidArgumentException(message);
        }
    }
}

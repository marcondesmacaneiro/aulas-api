package br.com.marcondesmacaneiro.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class NullArgumentException extends NullPointerException {

    public NullArgumentException(String message) {
        super(message);
    }
}

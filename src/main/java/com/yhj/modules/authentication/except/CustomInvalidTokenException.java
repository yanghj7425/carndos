package com.yhj.modules.authentication.except;

import org.springframework.security.core.AuthenticationException;

public class CustomInvalidTokenException extends AuthenticationException {

    public CustomInvalidTokenException(String msg, Throwable t) {
        super(msg, t);
    }

    public CustomInvalidTokenException(String msg) {
        super(msg);
    }
}

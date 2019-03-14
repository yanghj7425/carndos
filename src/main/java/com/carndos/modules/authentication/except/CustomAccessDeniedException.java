package com.carndos.modules.authentication.except;

import org.springframework.security.core.AuthenticationException;

public class CustomAccessDeniedException extends AuthenticationException {
    public CustomAccessDeniedException(String msg, Throwable t) {
        super(msg, t);
    }

    public CustomAccessDeniedException(String msg) {
        super(msg);
    }
}

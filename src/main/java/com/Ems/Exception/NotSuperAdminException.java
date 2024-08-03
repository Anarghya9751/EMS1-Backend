package com.Ems.Exception;

public class NotSuperAdminException extends RuntimeException {
    public NotSuperAdminException(String message) {
        super(message);
    }
}
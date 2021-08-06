package com.company.validator;

public interface Validator<T> {
    boolean validate(T data);

    String getMessage();
}

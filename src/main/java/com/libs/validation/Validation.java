package com.libs.validation;

import org.modelmapper.ModelMapper;
import com.libs.validation.exceptions.ValidationException;

public abstract class Validation<T> {
    protected final ModelMapper mapper = new ModelMapper();
    protected T data = null;

    public Validation(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public abstract <M extends Object> M validate(Class<M> returnModel) throws ValidationException;
}

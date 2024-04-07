package com.libs;

import org.modelmapper.ModelMapper;

public abstract class Controller {
    protected final ModelMapper mapper = new ModelMapper();
}

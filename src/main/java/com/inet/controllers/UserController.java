package com.inet.controllers;

import com.inet.dto.UserStoreDTO;
import com.inet.models.UserModel;
import com.inet.repositories.UserRepository;
import com.inet.resources.UserStoreResource;
import com.inet.validations.user.UserStoreValidation;
import com.libs.Controller;
import com.libs.validation.exceptions.ValidationException;

public class UserController extends Controller {

    public UserStoreResource store(UserStoreDTO data) throws ValidationException {
        UserStoreValidation validation = new UserStoreValidation(data);

        UserModel user = validation.validate(UserModel.class);

        UserRepository.save(user);

        return mapper.map(user, UserStoreResource.class);
    }
}

package com.inet.validations.user;

import java.util.regex.Pattern;

import com.inet.AppConfig;
import com.inet.AppEnv;
import com.inet.dto.UserStoreDTO;
import com.inet.enums.UserTypeEnum;
import com.inet.exceptions.EnumNotFouldException;
import com.libs.validation.Validation;
import com.libs.validation.exceptions.ValidationException;

public class UserStoreValidation extends Validation<UserStoreDTO> {
    public UserStoreValidation(UserStoreDTO data) {
        super(data);
    }

    @Override
    public <M extends Object> M validate(Class<M> returnModel) throws ValidationException {
        AppEnv env = AppConfig.env;

        if (data.getName() == null || data.getName().isEmpty()) {
            throw new ValidationException("The field name is required", "required");
        }

        if (data.getEmail() == null || data.getEmail().isEmpty()) {
            throw new ValidationException("The field email is required", "required");
        }

        if (!env.REGEX_EMAIL.matcher(data.getEmail()).matches()) {
            throw new ValidationException("The field email has invalid format", "format");
        }

        if (data.getPassword() == null || data.getPassword().isEmpty()) {
            throw new ValidationException("The field password is required", "required");
        }

        if (data.getConfirmPassword() == null || data.getConfirmPassword().isEmpty()) {
            throw new ValidationException("The field confirm password is required", "required");
        }

        if (!Pattern.compile(data.getPassword()).matcher(data.getConfirmPassword()).matches()) {
            throw new ValidationException("The field confirm password not match with password", "match");
        }

        if (data.getType() == null || data.getType().isEmpty()) {
            throw new ValidationException("The field user type is required", "required");
        }

        try {
            UserTypeEnum.toEnum(data.getType());
        } catch (EnumNotFouldException e) {
            throw new ValidationException("The field user type value is not valid", "invalid");
        }

        return mapper.map(data, returnModel);
    }
}

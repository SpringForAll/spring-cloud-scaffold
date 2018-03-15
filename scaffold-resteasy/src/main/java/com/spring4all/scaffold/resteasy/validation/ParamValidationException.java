package com.spring4all.scaffold.resteasy.validation;

import javax.validation.ValidationException;
import java.util.Map;

public class ParamValidationException extends ValidationException {

    private Map<String, String> errorMap;

    public ParamValidationException(Map<String, String> errorMap) {
        super();
        this.errorMap = errorMap;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }
}

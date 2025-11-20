package com.anas.blogapi.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Var;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String resourceFieldName;
    Object fieldValue;

    public ResourceNotFoundException(String resourceName, String resourceFieldName, Object fieldValue) {
        super(String.format("%s Not Found with %s : %s",resourceName, resourceFieldName, fieldValue));
        this.resourceName = resourceName;
        this.resourceFieldName = resourceFieldName;
        this.fieldValue = fieldValue;
    }
}

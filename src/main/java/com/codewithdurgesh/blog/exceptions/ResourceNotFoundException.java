package com.codewithdurgesh.blog.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    String resourceName;
    String fieldName;
    long filedValue;

    public ResourceNotFoundException(String resourceName, String fieldName, long filedValue) {
        super(String.format("%s not found with %s : %s",resourceName,fieldName,filedValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.filedValue = filedValue;
    }

//    public ResourceNotFoundException(String message, String resourceName, String fieldName, long filedValue) {
//        super(String.format("%s not found with %s : %s",resourceName,fieldName,filedValue));
//        this.resourceName = resourceName;
//        this.fieldName = fieldName;
//        this.filedValue = filedValue;
//    }


}

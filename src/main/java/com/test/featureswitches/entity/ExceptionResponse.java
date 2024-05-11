package com.test.featureswitches.entity;

/**
 * Class for response of failed REST request
 */
public class ExceptionResponse {

    private String error;

    public ExceptionResponse() {

    }

    public ExceptionResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
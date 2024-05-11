package com.test.featureswitches.entity;

import java.util.Map;

import org.springframework.http.HttpStatus;

/**
 * Class for response of failed REST request
 */
public class RestResponse {
    private HttpStatus statusCode;
    private boolean hasError;
    private String error;
    private Map<String, Object> data;

    public RestResponse() {

    }

    public RestResponse(HttpStatus statusCode, boolean hasError, Map<String, Object> data, String error) {
        this.statusCode = statusCode;
        this.hasError = hasError;
        this.data = data;
        this.error = error;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public boolean getHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
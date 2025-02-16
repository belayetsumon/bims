/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.exception;

/**
 *
 * @author User
 */
public class ExceptionResponse {

    private String errorMessage;
    private String requestedURI;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getRequestedURI() {
        return requestedURI;
    }

    public void callerURL(final String requestedURI) {
        this.requestedURI = requestedURI;
    }
}

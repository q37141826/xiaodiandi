package com.qixiu.qixiu.request.bean;

/**
 * Created by my on 2018/11/22.
 */

public class ErrorBeanOne extends C_CodeBean{




    private ErrorBean error;



    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }



    public static class ErrorBean {
        private int code;
        private String message;
        private Object details;
        private Object validationErrors;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getDetails() {
            return details;
        }

        public void setDetails(Object details) {
            this.details = details;
        }

        public Object getValidationErrors() {
            return validationErrors;
        }

        public void setValidationErrors(Object validationErrors) {
            this.validationErrors = validationErrors;
        }
    }
}

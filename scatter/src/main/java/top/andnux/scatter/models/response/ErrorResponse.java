package top.andnux.scatter.models.response;

import java.io.Serializable;

public class ErrorResponse implements Serializable {

    private int code;
    private String message;
    private boolean isError;
    private String type;

    public ErrorResponse(int code, String message, String type) {
        this.code = code;
        this.message = message;
        this.isError = true;
        this.type = type;
    }

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

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

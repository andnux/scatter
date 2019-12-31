package top.andnux.scatter.js.models;

import top.andnux.scatter.models.Type;

public class ScatterRequest {
    private String params;
    private @Type.ScatterWalletTypes
    String methodName;
    private String callback;

    public ScatterRequest() {
    }

    public ScatterRequest(String params, String methodName, String callback) {
        this.params = params;
        this.methodName = methodName;
        this.callback = callback;
    }

    public String getParams() {
        return params;
    }

    public @Type.ScatterWalletTypes
    String getMethodName() {
        return methodName;
    }

    public String getCallback() {
        return callback;
    }

    @Override
    public String toString() {
        return "ScatterRequest{" +
                "params='" + params + '\'' +
                ", methodName='" + methodName + '\'' +
                '}';
    }
}

package top.andnux.scatter.models.response;

import java.io.Serializable;

/**
 * created on 2020/1/3
 */
public class AppInfoResponse implements Serializable {

    private String appName;
    private String appVersion;

    public AppInfoResponse() {
    }

    public AppInfoResponse(String appName, String appVersion) {
        this.appName = appName;
        this.appVersion = appVersion;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}

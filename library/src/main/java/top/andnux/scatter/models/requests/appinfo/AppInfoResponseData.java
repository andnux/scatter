package top.andnux.scatter.models.requests.appinfo;

import com.google.gson.annotations.SerializedName;

public class AppInfoResponseData {

    @SerializedName("app")
    private String appName;
    @SerializedName("app_version")
    private String appVersion;
    @SerializedName("protocol_name")
    private String protocolName;
    @SerializedName("protocol_version")
    private String protocolVersion;

    public AppInfoResponseData(String appName, String appVersion, String protocolName, String protocolVersion) {
        this.appName = appName;
        this.appVersion = appVersion;
        this.protocolName = protocolName;
        this.protocolVersion = protocolVersion;
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

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }
}

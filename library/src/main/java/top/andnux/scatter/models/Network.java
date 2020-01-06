package top.andnux.scatter.models;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Network implements Serializable {

    private String name;
    private String protocol;
    private String host;
    private String port;
    private String blockchain;
    private String chainId;
    private Object token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getBlockchain() {
        return blockchain;
    }

    public void setBlockchain(String blockchain) {
        this.blockchain = blockchain;
    }

    public String getChainId() {
        return chainId;
    }

    public void setChainId(String chainId) {
        this.chainId = chainId;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    @NonNull
    @Override
    public String toString() {
        if (TextUtils.isEmpty(port)) {
            return protocol + "://" + host + "/";
        } else {
            return protocol + "://" + host + ":" + port + "/";
        }
    }
}
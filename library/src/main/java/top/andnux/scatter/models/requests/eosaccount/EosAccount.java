package top.andnux.scatter.models.requests.eosaccount;

import java.io.Serializable;
import java.util.List;

/**
 * created on 2020/1/2
 */
public class EosAccount implements Serializable {

    private List<AccountsBean> accounts;
    private String origin;
    private String blockchain;
    private String host;
    private String port;
    private String protocol;
    private String chainId;
    private String httpEndpoint;

    public String getBlockchain() {
        return blockchain;
    }

    public void setBlockchain(String blockchain) {
        this.blockchain = blockchain;
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

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getChainId() {
        return chainId;
    }

    public void setChainId(String chainId) {
        this.chainId = chainId;
    }

    public String getHttpEndpoint() {
        return httpEndpoint;
    }

    public void setHttpEndpoint(String httpEndpoint) {
        this.httpEndpoint = httpEndpoint;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public List<AccountsBean> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountsBean> accounts) {
        this.accounts = accounts;
    }

    public static class AccountsBean implements Serializable {
        /**
         * blockchain : eos
         * host : 192.168.1.13
         * port : 8888
         * protocol : http
         * chainId : cf057bbfb72640471fd910bcb67639c22df9f92470936cddc1ade0e2f2e7dc4f
         * httpEndpoint : http://192.168.1.13:8888
         */

        private String blockchain;
        private String host;
        private String port;
        private String protocol;
        private String chainId;
        private String httpEndpoint;

        public String getBlockchain() {
            return blockchain;
        }

        public void setBlockchain(String blockchain) {
            this.blockchain = blockchain;
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

        public String getProtocol() {
            return protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        public String getChainId() {
            return chainId;
        }

        public void setChainId(String chainId) {
            this.chainId = chainId;
        }

        public String getHttpEndpoint() {
            return httpEndpoint;
        }

        public void setHttpEndpoint(String httpEndpoint) {
            this.httpEndpoint = httpEndpoint;
        }
    }
}

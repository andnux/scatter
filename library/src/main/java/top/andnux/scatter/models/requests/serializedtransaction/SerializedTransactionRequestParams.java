package top.andnux.scatter.models.requests.serializedtransaction;

import java.util.List;

public class SerializedTransactionRequestParams {

    private SerializedTransaction transaction;
    private Object requiredFields;
    private String blockchain;
    private Network network;

    public void setTransaction(SerializedTransaction transaction) {
        this.transaction = transaction;
    }

    public Object getRequiredFields() {
        return requiredFields;
    }

    public void setRequiredFields(Object requiredFields) {
        this.requiredFields = requiredFields;
    }

    public String getBlockchain() {
        return blockchain;
    }

    public void setBlockchain(String blockchain) {
        this.blockchain = blockchain;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public SerializedTransaction getTransaction() {
        return transaction;
    }


    public static class Network {

        private String name;
        private String protocol;
        private String host;
        private int port;
        private String blockchain;
        private String chainId;
        private Object token;

        public String toUrl() {
            if (port <= 0) {
                return protocol + "://" + host + "/";
            } else {
                return protocol + "://" + host + ":" + port + "/";
            }
        }

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

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
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
    }
}

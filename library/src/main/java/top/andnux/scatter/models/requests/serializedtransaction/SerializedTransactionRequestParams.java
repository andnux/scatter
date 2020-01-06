package top.andnux.scatter.models.requests.serializedtransaction;

import top.andnux.scatter.models.Network;

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
}




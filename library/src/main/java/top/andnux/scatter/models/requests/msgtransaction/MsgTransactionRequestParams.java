package top.andnux.scatter.models.requests.msgtransaction;

public class MsgTransactionRequestParams {
    private String data;
    private String publicKey;
    private boolean isHash;
    private String whatfor;
    private String nonce;
    private String row;

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public MsgTransactionRequestParams() {

    }

    public MsgTransactionRequestParams(String data, String publicKey, boolean isHash, String whatfor) {
        this.data = data;
        this.publicKey = publicKey;
        this.isHash = isHash;
        this.whatfor = whatfor;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public boolean isHash() {
        return isHash;
    }

    public void setHash(boolean hash) {
        isHash = hash;
    }

    public void setWhatfor(String whatfor) {
        this.whatfor = whatfor;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getData() {
        return data;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public boolean getIsHash() {
        return isHash;
    }

    public String getWhatfor() {
        return whatfor;
    }

    @Override
    public String toString() {
        return "MsgTransactionRequestParams{" +
                "data='" + data + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", isHash='" + isHash + '\'' +
                ", whatfor='" + whatfor + '\'' +
                '}';
    }
}



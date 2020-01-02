package top.andnux.scatter.models.requests.getaccount;

public class Account {

    private String name;
    private String authority;
    private String publicKey;
    private String blockchain;
    private String chainId;
    private Boolean isHardware;

    public Account(String name, String authority, String publicKey, String blockchain, String chainId, Boolean isHardware) {
        this.name = name;
        this.authority = authority;
        this.publicKey = publicKey;
        this.blockchain = blockchain;
        this.chainId = chainId;
        this.isHardware = isHardware;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
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

    public Boolean getHardware() {
        return isHardware;
    }

    public void setHardware(Boolean hardware) {
        isHardware = hardware;
    }
}

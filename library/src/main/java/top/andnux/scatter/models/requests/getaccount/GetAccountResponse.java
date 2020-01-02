package top.andnux.scatter.models.requests.getaccount;

public class GetAccountResponse {

    private String hash;
    private String publicKey;
    private String name;
    private boolean kyc;
    private Account[] accounts;

    public GetAccountResponse(String hash, String publicKey, String name, boolean kyc, Account[] accounts) {
        this.hash = hash;
        this.publicKey = publicKey;
        this.name = name;
        this.kyc = kyc;
        this.accounts = accounts;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isKyc() {
        return kyc;
    }

    public void setKyc(boolean kyc) {
        this.kyc = kyc;
    }

    public Account[] getAccounts() {
        return accounts;
    }

    public void setAccounts(Account[] accounts) {
        this.accounts = accounts;
    }
}

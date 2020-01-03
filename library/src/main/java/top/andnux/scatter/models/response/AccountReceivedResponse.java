package top.andnux.scatter.models.response;

import java.io.Serializable;

/**
 * created on 2020/1/3
 */
public class AccountReceivedResponse implements Serializable {

    private String accountName;
    private String authority;
    private String publicKey;

    public AccountReceivedResponse(String accountName, String authority, String publicKey) {
        this.accountName = accountName;
        this.authority = authority;
        this.publicKey = publicKey;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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
}

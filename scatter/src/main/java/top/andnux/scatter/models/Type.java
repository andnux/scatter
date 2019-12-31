package top.andnux.scatter.models;


import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
public class Type {

    public static final String GET_VERSION = "getVersion";
    public static final String IDENTITY_FROM_PERMISSIONS = "identityFromPermissions";
    public static final String GET_OR_REQUEST_IDENTITY = "getOrRequestIdentity";
    public static final String AUTHENTICATE = "authenticate";
    public static final String FORGET_IDENTITY = "forgetIdentity";
    public static final String GET_IDENTITY = "getIdentity";
    public static final String REQUEST_ADD_NETWORK = "requestAddNetwork";
    public static final String REQUEST_SIGNATURE = "requestSignature";
    public static final String REQUEST_ARBITRARY_SIGNATURE = "requestArbitrarySignature";
    public static final String GET_PUBLIC_KEY = "getPublicKey";
    public static final String LINK_ACCOUNT = "linkAccount";
    public static final String REQUEST_TRANSFER = "requestTransfer";
    public static final String GET_AVATAR = "getAvatar";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({GET_VERSION, IDENTITY_FROM_PERMISSIONS, GET_OR_REQUEST_IDENTITY, AUTHENTICATE,
            FORGET_IDENTITY, REQUEST_ADD_NETWORK, REQUEST_SIGNATURE, REQUEST_ARBITRARY_SIGNATURE,
            GET_PUBLIC_KEY, LINK_ACCOUNT, REQUEST_TRANSFER,GET_AVATAR,GET_IDENTITY})
    public @interface ScatterWalletTypes {
    }
}


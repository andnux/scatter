package top.andnux.scatter.socket.models;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Commands {

    public static final String PAIR = "pair";
    public static final String API = "api";
    public static final String REKEYED = "rekeyed";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({PAIR, API, REKEYED})
    public @interface ScatterCommands {
    }
}
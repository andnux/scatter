package top.andnux.scatter.js.models;

import com.google.gson.annotations.JsonAdapter;
import top.andnux.scatter.util.RawJsonGsonAdapter;

public class ScatterResponse {
    private String message;
    @JsonAdapter(RawJsonGsonAdapter.class)
    private String data;
    private int code;

    public ScatterResponse(String message, String data, int code) {
        this.message = message;
        this.data = data;
        this.code = code;
    }
}

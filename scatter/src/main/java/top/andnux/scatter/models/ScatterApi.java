package top.andnux.scatter.models;

public enum  ScatterApi {
    GET_VERSION("getVersion"),
    GET_IDENTITY("getIdentity"),



    ;

    ScatterApi(String name) {
        this.name = name;
    }

    private String name;
}

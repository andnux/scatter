package top.andnux.scatter.models.response;

public enum ResultCode {
    SUCCESS(0),
    UNKNOWN_ERROR(1),
    NO_SIGNATURE(402),
    FORBIDDEN(403),
    TIMED_OUT(408),
    LOCKED(423),
    UPGRADE_REQUIRED(426),
    TOO_MANY_REQUESTS(429);

    private int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

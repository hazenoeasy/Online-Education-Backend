package plus.yuhaozhang.commonUtils;

/**
 * @author Yuh Z
 * @date 1/17/22
 */
public enum StatusCode {
    // 成功状态码
    SUCCESS(200, "success"),

    //失败状态码
    FAIL(400, "fail");
    private int code;
    private String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }
    public String getMessage(){
        return this.message;
    }
}

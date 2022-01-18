package plus.yuhaozhang.servicebase.handler.exceptionHandler;

import lombok.Data;
import plus.yuhaozhang.commonUtils.StatusCode;

/**
 * @author Yuh Z
 * @date 1/18/22
 */
@Data
public class PurPoseException extends RuntimeException {
    private StatusCode statusCode;
    private String msg;

    public PurPoseException(StatusCode statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }


    public PurPoseException(StatusCode statusCode) {
        this.statusCode = statusCode;
        this.msg = statusCode.getMessage();
    }
}

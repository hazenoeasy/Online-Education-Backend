package plus.yuhaozhang.commonUtils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Result {

    @ApiModelProperty(value = "成功与否")
    private boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")

    private Object data;

    //成功静态方法
    public static Result success() {
        Result response = new Result();
        response.setSuccess(true);
        response.setCode(StatusCode.SUCCESS.getCode());
        response.setMessage(StatusCode.SUCCESS.getMessage());
        return response;
    }
    public static Result success(Object data) {
        Result success = Result.success();
        success.setData(data);
        return success;
    }

    /**
     *
     * @param code 状态码
     * @return 返回状态吗
     */
    public static Result error(StatusCode code) {
        Result response = new Result();
        response.setSuccess(false);
        response.setCode(code.getCode());
        response.setMessage(code.getMessage());
        return response;
    }

    /**
     *  多态 用户可以自己定义 返回信息 但是必须采用状态码
     * @param code
     * @param message
     * @return
     */
    public static Result error(StatusCode code, String message) {
        Result response = error(code);
        response.setMessage(message);
        return response;
    }
}

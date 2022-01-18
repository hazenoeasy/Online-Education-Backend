package plus.yuhaozhang.servicebase.handler.exceptionHandler;

import org.apache.ibatis.session.ResultHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import plus.yuhaozhang.commonUtils.Result;
import plus.yuhaozhang.commonUtils.StatusCode;

/**
 * @author Yuh Z
 * @date 1/18/22
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result globalException(Exception e){
        e.printStackTrace();
        return Result.error(StatusCode.SYSTEM_FAULT);
    }

    @ExceptionHandler(PurPoseException.class)
    @ResponseBody
    public Result purposeException(PurPoseException purPoseException){
        purPoseException.printStackTrace();
        return Result.error(purPoseException.getStatusCode(),purPoseException.getMsg());

    }
}

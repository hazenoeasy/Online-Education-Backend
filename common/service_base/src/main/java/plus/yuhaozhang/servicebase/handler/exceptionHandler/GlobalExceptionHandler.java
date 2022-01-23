package plus.yuhaozhang.servicebase.handler.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result globalException(Exception e){
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.error(StatusCode.SYSTEM_FAULT);
    }

    @ExceptionHandler(PurPoseException.class)
    @ResponseBody
    public Result purposeException(PurPoseException purPoseException){
        log.error("PurposeException -- "+purPoseException.getStatusCode()+": "+purPoseException.getMsg());
        return Result.error(purPoseException.getStatusCode(),purPoseException.getMsg());

    }
}

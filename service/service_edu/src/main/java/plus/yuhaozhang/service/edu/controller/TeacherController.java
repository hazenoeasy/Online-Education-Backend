package plus.yuhaozhang.service.edu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import plus.yuhaozhang.commonUtils.Result;
import plus.yuhaozhang.commonUtils.StatusCode;
import plus.yuhaozhang.service.edu.entity.Teacher;
import plus.yuhaozhang.service.edu.service.TeacherService;
import plus.yuhaozhang.service.edu.vo.PageParams;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Yuh Z
 * @since 2022-01-17
 */
@Api(value = "讲师管理")
@RestController
@RequestMapping("/edu/teacher")
public class TeacherController {

    @Resource
    TeacherService teacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("getAll")
    public Result getAllTeacher() {
        List<Teacher> list = teacherService.list();
        return Result.success(list);
    }

    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("delete/{id}")
    public Result deleteTeacher(@PathVariable String id) {
        boolean isSuccess = teacherService.removeById(id);
        if (isSuccess) {
            return Result.success(isSuccess);
        } else {
            return Result.error(StatusCode.FAIL);
        }
    }

    @ApiOperation(value = "根据页面查数据")
    @PostMapping("getByPage")
    public Result getByPage(@RequestBody PageParams pageParams) {
        List<Teacher> teacherServiceByPage = teacherService.getByPage(pageParams);
        return Result.success(teacherServiceByPage);
    }
}


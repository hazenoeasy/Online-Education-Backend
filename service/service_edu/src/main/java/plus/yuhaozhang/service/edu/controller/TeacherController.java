package plus.yuhaozhang.service.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import plus.yuhaozhang.commonUtils.Result;
import plus.yuhaozhang.commonUtils.StatusCode;
import plus.yuhaozhang.service.edu.entity.Teacher;
import plus.yuhaozhang.service.edu.mapper.TeacherMapper;
import plus.yuhaozhang.service.edu.service.TeacherService;
import plus.yuhaozhang.service.edu.params.TeacherQueryParams;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    TeacherMapper teacherMapper;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("getAll")
    public Result getAllTeacher() {
        List<Teacher> list = teacherService.list();
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",list);
        return Result.success(map);
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
    public Result getByPage(@RequestBody TeacherQueryParams teacherQueryParams) {
        List<Teacher> teacherServiceByPage = teacherService.getByPage(teacherQueryParams);
        Map<String, Object> result = new HashMap<>();
        //result.put("total", teacherServiceByPage.size());
        result.put("total", teacherService.count());
        result.put("rows", teacherServiceByPage);
        return Result.success(result);
    }

    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public Result addTeacher(@RequestBody Teacher teacher) {

        Teacher teacherExist = teacherService.getOne(new QueryWrapper<Teacher>().eq("name", teacher.getName()));
        if (teacherExist != null) {
            return Result.error(StatusCode.INVALID_PARAM);
        }
        teacherService.save(teacher);
        return Result.success();

    }

    @ApiOperation(value = "根据讲师id查讲师")
    @GetMapping("get/{id}")
    public Result selectTeacherById(@PathVariable String id) {
        Teacher teacherServiceById = teacherService.getById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("teacher", teacherServiceById);
        return Result.success(map);
    }

    @ApiOperation(value = "修改讲师信息")
    @PutMapping("update")
    public Result updateTeacherById(@RequestBody Teacher teacher) {
        Teacher teacherServiceById = teacherService.getById(teacher.getId());
        if (teacherServiceById == null) {
            return Result.error(StatusCode.WRONG_PARAM);
        }
        Teacher teacherExist = teacherService.getOne(new QueryWrapper<Teacher>().eq("name", teacher.getName()));
        if (teacherExist == null) {
            return Result.error(StatusCode.INVALID_PARAM);
        }
        teacherService.updateById(teacher);
        return Result.success();
    }
}


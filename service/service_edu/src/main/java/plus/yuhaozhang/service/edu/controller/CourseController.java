package plus.yuhaozhang.service.edu.controller;


import com.alibaba.excel.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import plus.yuhaozhang.commonUtils.Result;
import plus.yuhaozhang.commonUtils.StatusCode;
import plus.yuhaozhang.service.edu.entity.Course;
import plus.yuhaozhang.service.edu.entity.CourseDescription;
import plus.yuhaozhang.service.edu.params.CourseQueryParams;
import plus.yuhaozhang.service.edu.vo.CourseInfoVo;
import plus.yuhaozhang.service.edu.service.CourseDescriptionService;
import plus.yuhaozhang.service.edu.service.CourseService;
import plus.yuhaozhang.service.edu.vo.CourseOverViewVo;
import plus.yuhaozhang.service.edu.vo.CourseVo;
import plus.yuhaozhang.servicebase.handler.exceptionHandler.PurPoseException;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Yuh Z
 * @since 2022-01-21
 */
@RestController
@RequestMapping("/edu/course")
public class CourseController {

    @Resource
    private CourseService courseService;

    @Resource
    private CourseDescriptionService courseDescriptionService;

    @ApiOperation(value = "添加课程")
    @PostMapping("addCourse")
    public Result addCourse(@RequestBody CourseInfoVo courseInfoVo) {
        Course course = new Course(courseInfoVo);
        courseService.save(course);
        String courseId = course.getId();
        CourseDescription courseDescription = new CourseDescription(courseId,courseInfoVo.getDescription());
        courseDescriptionService.save(courseDescription);
        HashMap<String, Object> map = new HashMap<>();
        map.put("courseId",courseId);
        return Result.success(map);
    }

    @ApiOperation(value = "根据Id查询课程")
    @GetMapping("getCourseInfo/{id}")
    public Result findCourseById(@PathVariable String id) {
        Course course = courseService.getById(id);
        if(course==null){
            throw new PurPoseException(StatusCode.INVALID_PARAM,"id错误");
        }
        CourseDescription courseDescription = courseDescriptionService.getById(id);
        CourseInfoVo courseInfoVo = new CourseInfoVo(course);
        if(courseDescription!=null && !StringUtils.isEmpty(courseDescription.getDescription())){
            courseInfoVo.setDescription(courseDescription.getDescription());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("courseInfoVo", courseInfoVo);
        return Result.success(map);
    }

    @ApiOperation(value = "根据Id修改课程")
    @PutMapping("updateCourse/{id}")
    public Result updateCourse(@PathVariable String id,@RequestBody CourseInfoVo courseInfoVo){
        Course course = new Course(courseInfoVo);
        course.setId(id);
        CourseDescription courseDescription = new CourseDescription(id, courseInfoVo.getDescription());
        courseService.updateById(course);
        courseDescriptionService.updateById(courseDescription);
        return Result.success();
    }

    @ApiOperation(value = "根据Id删除课程")
    @DeleteMapping("deleteById/{id}")
    public Result deleteCourseById(@PathVariable String id){
        courseService.removeById(id);
        return Result.success();
    }

    @ApiOperation(value = "根据查询条件 返回页数")
    @PostMapping("getCourseListPage")
    public Result getCourseListPage(@RequestBody CourseQueryParams courseQueryParams){
        List<CourseVo> courseVoList = courseService.getByPage(courseQueryParams);
        Integer count = courseService.size(courseQueryParams);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows",courseVoList);
        map.put("total",count);
        return Result.success(map);
    }

    @ApiOperation(value = "获取课程总览")
    @GetMapping("getCourseOverview/{id}")
    public Result getCourseOverview(@PathVariable String id){
        CourseOverViewVo courseOverViewVo = courseService.getCourseOverview(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("couseOverview",courseOverViewVo);
        return Result.success(map);
    }

    //@ApiOperation(value = "发布课程")
    //@PutMapping("publish")
    //public Result publish(@RequestBody String id){
    //    courseService.publish(id);
    //}

}


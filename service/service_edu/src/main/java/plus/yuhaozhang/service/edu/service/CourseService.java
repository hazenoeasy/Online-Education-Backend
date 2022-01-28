package plus.yuhaozhang.service.edu.service;

import plus.yuhaozhang.service.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import plus.yuhaozhang.service.edu.params.CourseQueryParams;
import plus.yuhaozhang.service.edu.vo.CourseInfoVo;
import plus.yuhaozhang.service.edu.vo.CourseOverViewVo;
import plus.yuhaozhang.service.edu.vo.CourseVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Yuh Z
 * @since 2022-01-21
 */
public interface CourseService extends IService<Course> {

    void addCourse(CourseInfoVo courseInfoVo);

    List<CourseVo> getByPage(CourseQueryParams courseQueryParams);

    Integer size(CourseQueryParams courseQueryParams);

    void publish(String id);

    CourseOverViewVo getCourseOverview(String id);
}

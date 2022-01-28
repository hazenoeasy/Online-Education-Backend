package plus.yuhaozhang.service.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import plus.yuhaozhang.service.edu.entity.Course;
import plus.yuhaozhang.service.edu.vo.CourseOverViewVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Yuh Z
 * @since 2022-01-21
 */
public interface CourseMapper extends BaseMapper<Course> {
    // 只有一个参数的时候 xml文件里#{}可以随便写
    public CourseOverViewVo getCourseOverview(String id);
}

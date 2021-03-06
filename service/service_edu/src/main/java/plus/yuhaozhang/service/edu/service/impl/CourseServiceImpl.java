package plus.yuhaozhang.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import plus.yuhaozhang.service.edu.entity.Course;
import plus.yuhaozhang.service.edu.mapper.CourseMapper;
import plus.yuhaozhang.service.edu.params.CourseQueryParams;
import plus.yuhaozhang.service.edu.vo.CourseInfoVo;
import plus.yuhaozhang.service.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import plus.yuhaozhang.service.edu.vo.CourseOverViewVo;
import plus.yuhaozhang.service.edu.vo.CourseVo;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Yuh Z
 * @since 2022-01-21
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    private CourseMapper courseMapper;
    @Override
    public void addCourse(CourseInfoVo courseInfoVo) {

    }

    @Override
    public List<CourseVo> getByPage(CourseQueryParams courseQueryParams) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_modified");
        if(!StringUtils.isEmpty(courseQueryParams.getTitle())){
            queryWrapper.like("title",courseQueryParams.getTitle());
        }
        if(!StringUtils.isEmpty(courseQueryParams.getStatus())){
            queryWrapper.eq("status",courseQueryParams.getStatus());
        }
        Page<Course> coursePage = new Page<>(courseQueryParams.getPage(),courseQueryParams.getLimit());
        this.page(coursePage,queryWrapper);
        List<Course> records = coursePage.getRecords();
        List<CourseVo> courseVoList =
                records.stream().map(course -> new CourseVo(course)).collect(Collectors.toList());
        return courseVoList;
    }

    @Override
    public Integer size(CourseQueryParams courseQueryParams) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(courseQueryParams.getTitle())){
            queryWrapper.like("title",courseQueryParams.getTitle());
        }
        if(!StringUtils.isEmpty(courseQueryParams.getStatus())){
            queryWrapper.eq("status",courseQueryParams.getStatus());
        }
        return this.count(queryWrapper);
    }

    @Override
    public void publish(String id) {
        Course course = new Course();
        course.setId(id);
        course.setStatus("Normal");
        this.updateById(course);
    }

    @Override
    public CourseOverViewVo getCourseOverview(String id) {
        CourseOverViewVo courseOverViewVo = courseMapper.getCourseOverview(id);
        return courseOverViewVo;
    }
}

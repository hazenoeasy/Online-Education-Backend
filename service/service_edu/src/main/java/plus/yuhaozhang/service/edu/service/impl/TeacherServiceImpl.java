package plus.yuhaozhang.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import plus.yuhaozhang.service.edu.entity.Teacher;
import plus.yuhaozhang.service.edu.mapper.TeacherMapper;
import plus.yuhaozhang.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import plus.yuhaozhang.service.edu.params.TeacherQueryParams;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Yuh Z
 * @since 2022-01-17
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Resource
    TeacherService teacherService;
    @Override
    public List<Teacher> getByPage(TeacherQueryParams teacherQueryParams) {
        Page<Teacher> teacherPage = new Page<>(teacherQueryParams.getPage(), teacherQueryParams.getPageSize());
        String begin = teacherQueryParams.getBegin();
        String end = teacherQueryParams.getEnd();
        Integer level = teacherQueryParams.getLevel();
        String name = teacherQueryParams.getName();
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(name)){
            teacherQueryWrapper.like("name",name);
        }
        if(level!=null){
          teacherQueryWrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            teacherQueryWrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            teacherQueryWrapper.le("gmt_create",end);
        }
        teacherQueryWrapper.orderByDesc("gmt_create");
        teacherService.page(teacherPage,teacherQueryWrapper);
        List<Teacher> result = teacherPage.getRecords();
        return result;
    }
}

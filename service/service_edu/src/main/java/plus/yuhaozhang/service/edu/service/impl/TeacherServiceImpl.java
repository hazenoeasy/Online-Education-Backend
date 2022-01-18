package plus.yuhaozhang.service.edu.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import plus.yuhaozhang.service.edu.entity.Teacher;
import plus.yuhaozhang.service.edu.mapper.TeacherMapper;
import plus.yuhaozhang.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import plus.yuhaozhang.service.edu.vo.PageParams;

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
    public List<Teacher> getByPage(PageParams pageParams) {
        Page<Teacher> objectPage = new Page<>(pageParams.getPage(), pageParams.getPageSize());
        teacherService.page(objectPage,null);
        List<Teacher> result = objectPage.getRecords();
        return result;
    }
}

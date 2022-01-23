package plus.yuhaozhang.service.edu.service;

import plus.yuhaozhang.service.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import plus.yuhaozhang.service.edu.params.TeacherQueryParams;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Yuh Z
 * @since 2022-01-17
 */
public interface TeacherService extends IService<Teacher> {
    /**
     * query data by page and name and other params
     * @param teacherQueryParams select params
     * @return list Teacher
     */
    public List<Teacher> getByPage(TeacherQueryParams teacherQueryParams);

}

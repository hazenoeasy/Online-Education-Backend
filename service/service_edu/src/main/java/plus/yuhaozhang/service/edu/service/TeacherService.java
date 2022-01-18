package plus.yuhaozhang.service.edu.service;

import plus.yuhaozhang.service.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import plus.yuhaozhang.service.edu.vo.PageParams;

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
    public List<Teacher> getByPage(PageParams pageParams);
}

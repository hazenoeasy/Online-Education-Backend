package plus.yuhaozhang.service.edu.service;

import plus.yuhaozhang.service.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import plus.yuhaozhang.service.edu.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Yuh Z
 * @since 2022-01-21
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getListByCourseId(String id);
}

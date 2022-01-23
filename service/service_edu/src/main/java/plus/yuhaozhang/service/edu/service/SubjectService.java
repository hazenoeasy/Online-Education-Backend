package plus.yuhaozhang.service.edu.service;

import org.springframework.web.multipart.MultipartFile;
import plus.yuhaozhang.service.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import plus.yuhaozhang.service.edu.vo.SubjectTree;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Yuh Z
 * @since 2022-01-20
 */
public interface SubjectService extends IService<Subject> {

    void addSubject(MultipartFile file) throws IOException;

    List<SubjectTree> getAllByTree();
}

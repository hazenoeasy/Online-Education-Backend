package plus.yuhaozhang.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import plus.yuhaozhang.commonUtils.StatusCode;
import plus.yuhaozhang.service.edu.entity.Chapter;
import plus.yuhaozhang.service.edu.entity.Course;
import plus.yuhaozhang.service.edu.entity.Video;
import plus.yuhaozhang.service.edu.mapper.ChapterMapper;
import plus.yuhaozhang.service.edu.params.ChapterParams;
import plus.yuhaozhang.service.edu.service.ChapterService;
import plus.yuhaozhang.service.edu.service.CourseService;
import plus.yuhaozhang.service.edu.service.VideoService;
import plus.yuhaozhang.service.edu.vo.ChapterVo;
import plus.yuhaozhang.service.edu.vo.VideoVo;
import plus.yuhaozhang.servicebase.handler.exceptionHandler.PurPoseException;

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
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Resource
    private VideoService videoService;

    @Resource
    private CourseService courseService;

    @Override
    public List<ChapterVo> getListByCourseId(String id) {
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", id);
        List<Chapter> chapterList = this.list(chapterQueryWrapper);
        // 查到了chapter数据
        if (chapterList != null && chapterList.size() != 0) {
            //构建chapterVo对象
            List<ChapterVo> chapterVoList = chapterList.stream().map(chapter -> getChapterVo(chapter)).collect(Collectors.toList());
            return chapterVoList;
        } else {
            return null;
        }
    }

    @Override
    public void addChapter(Chapter chapter) {
        if (StringUtils.isEmpty(chapter.getCourseId())) {
            throw new PurPoseException(StatusCode.WRONG_PARAM);
        }
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", chapter.getCourseId());
        Course course = courseService.getOne(queryWrapper);
        if (course == null) {
            throw new PurPoseException(StatusCode.INVALID_PARAM, "invalid course id");
        }
        // 课程id正确 插入数据
        this.save(chapter);
    }

    @Override
    public ChapterParams getChapterInfo(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new PurPoseException(StatusCode.WRONG_PARAM);
        }
        Chapter chapter = this.getById(id);
        if (chapter == null) {
            throw new PurPoseException(StatusCode.INVALID_PARAM);
        }
        ChapterParams chapterParams = new ChapterParams(chapter);
        return chapterParams;
    }

    @Override
    public void updateChapter(Chapter chapter) {
        if (chapter == null) {
            throw new PurPoseException(StatusCode.WRONG_PARAM);
        }
        if(StringUtils.isEmpty(chapter.getId())){
            throw new PurPoseException(StatusCode.INVALID_PARAM);
        }
        Chapter isExist = this.getById(chapter.getId());
        if(isExist==null){
            throw new PurPoseException(StatusCode.INVALID_PARAM);
        }
        this.updateById(chapter);
    }

    /**
     * 这里循环查询sql了，速度太慢，应该一次查完，然后放到内存中处理
     *
     * @param chapter
     * @return
     */
    private ChapterVo getChapterVo(Chapter chapter) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id", chapter.getId());
        queryWrapper.eq("course_id", chapter.getCourseId());
        List<Video> videoList = videoService.list(queryWrapper);
        List<VideoVo> videoVoList = null;
        if (videoList != null) {
            videoVoList = videoList.stream().map(video -> new VideoVo(video)).collect(Collectors.toList());
        }
        ChapterVo chapterVo = new ChapterVo(chapter.getId(), chapter.getTitle(), videoVoList);
        return chapterVo;
    }
}

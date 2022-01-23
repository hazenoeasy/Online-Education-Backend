package plus.yuhaozhang.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import plus.yuhaozhang.commonUtils.Result;
import plus.yuhaozhang.commonUtils.StatusCode;
import plus.yuhaozhang.service.edu.entity.Chapter;
import plus.yuhaozhang.service.edu.entity.Video;
import plus.yuhaozhang.service.edu.mapper.ChapterMapper;
import plus.yuhaozhang.service.edu.service.ChapterService;
import plus.yuhaozhang.service.edu.service.VideoService;
import plus.yuhaozhang.service.edu.vo.ChapterVo;
import plus.yuhaozhang.service.edu.vo.VideoVo;

import javax.annotation.Resource;
import java.util.HashMap;
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

    /**
     * 这里循环查询sql了，速度太慢，应该一次查完，然后放到内存中处理
     * @param chapter
     * @return
     */
    private ChapterVo getChapterVo(Chapter chapter){
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id",chapter.getId());
        queryWrapper.eq("course_id",chapter.getCourseId());
        List<Video> videoList = videoService.list(queryWrapper);
        List<VideoVo> videoVoList = null;
        if (videoList != null) {
            videoVoList = videoList.stream().map(video -> new VideoVo(video)).collect(Collectors.toList());
        }
        ChapterVo chapterVo = new ChapterVo(chapter.getId(), chapter.getTitle(), videoVoList);
        return chapterVo;
    }
}

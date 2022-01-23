package plus.yuhaozhang.service.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.yuhaozhang.commonUtils.Result;
import plus.yuhaozhang.commonUtils.StatusCode;
import plus.yuhaozhang.service.edu.entity.Chapter;
import plus.yuhaozhang.service.edu.entity.Video;
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
 * 课程 前端控制器
 * </p>
 *
 * @author Yuh Z
 * @since 2022-01-21
 */
@RestController
@RequestMapping("/edu/chapter")
public class ChapterController {

    @Resource
    private ChapterService chapterService;
    @Resource
    private VideoService videoService;

    @ApiOperation(value = "根据课程id返回课程大纲列表")
    @GetMapping("getList/{id}")
    public Result getListByCourseId(@PathVariable String id) {
        List<ChapterVo> chapterVoList = chapterService.getListByCourseId(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("allChapterVideo",chapterVoList);
        return Result.success(map);
    }

}


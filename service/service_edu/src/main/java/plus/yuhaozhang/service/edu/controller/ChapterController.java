package plus.yuhaozhang.service.edu.controller;


import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import plus.yuhaozhang.commonUtils.Result;
import plus.yuhaozhang.commonUtils.StatusCode;
import plus.yuhaozhang.service.edu.entity.Chapter;
import plus.yuhaozhang.service.edu.params.ChapterParams;
import plus.yuhaozhang.service.edu.service.ChapterService;
import plus.yuhaozhang.service.edu.service.VideoService;
import plus.yuhaozhang.service.edu.vo.ChapterVo;
import plus.yuhaozhang.servicebase.handler.exceptionHandler.PurPoseException;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

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
        map.put("allChapterVideo", chapterVoList);
        return Result.success(map);
    }

    @ApiOperation(value = "添加chapter")
    @PostMapping("addChapter")
    public Result addChapter(@RequestBody ChapterParams chapterParams) {
        Chapter chapter = new Chapter(chapterParams);
        chapterService.addChapter(chapter);
        HashMap<String, Object> map = new HashMap<>();
        map.put("chapterId", chapter.getId());
        return Result.success(map);
    }

    @ApiOperation(value = "根据chapterId返回信息")
    @GetMapping("getChapterInfo/{id}")
    public Result getChapterInfo(@PathVariable String id) {
        ChapterParams chapterParams = chapterService.getChapterInfo(id);
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("chapter", chapterParams);
        return Result.success(objectObjectHashMap);
    }

    @ApiOperation(value = "根据chapterId删除chapter")
    @DeleteMapping("delete/{id}")
    public Result deleteById(@PathVariable String id) {
        if (StringUtils.isEmpty(id)) {
            throw new PurPoseException(StatusCode.WRONG_PARAM);
        }
        boolean isDeleted = chapterService.removeById(id);
        if (isDeleted) {
            return Result.success();
        } else {
            throw new PurPoseException(StatusCode.INVALID_PARAM);
        }
    }

    @ApiOperation(value = "更新chapter对象")
    @PutMapping("update")
    public Result updateChapter(@RequestBody Chapter chapter) {
        chapterService.updateChapter(chapter);
        return Result.success();
    }
}


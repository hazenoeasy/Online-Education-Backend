package plus.yuhaozhang.service.edu.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import plus.yuhaozhang.commonUtils.Result;
import plus.yuhaozhang.service.edu.entity.Video;
import plus.yuhaozhang.service.edu.service.VideoService;

import javax.annotation.Resource;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Yuh Z
 * @since 2022-01-21
 */
@RestController
@RequestMapping("/edu/video")
public class VideoController {

    @Resource
    private VideoService videoService;

    // 添加小节
    @ApiOperation("添加小节")
    @PostMapping("addVideo")
    public Result addVideo(@RequestBody Video video) {
        boolean save = videoService.save(video);
        return Result.success();
    }

    // TODO: 删除小节 也要删除视频
    @ApiOperation(value = "删除小节")
    @DeleteMapping("delete/{id}")
    public Result deleteVideo(@PathVariable String id){
        videoService.removeById(id);
        return  Result.success();
    }
    // 删除小节

    // 修改小节
}


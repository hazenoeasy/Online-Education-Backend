package plus.yuhaozhang.service.vod.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import plus.yuhaozhang.commonUtils.Result;
import plus.yuhaozhang.service.vod.service.VodService;

import javax.annotation.Resource;
import java.net.URL;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yuh Z
 * @date 1/28/22
 */
@RestController
@RequestMapping("edu/vod")
public class VodController {

    @Resource
    private VodService vodService;

    @ApiOperation(value = "上传视频")
    @PostMapping("upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws Exception {
        String vid = vodService.uploadFile(file);
        //Map<String, URL> map = new HashMap<>();
        System.out.println(vid);
        Map<String,String> result = new HashMap<>();
        result.put("videoId",vid);
        return Result.success(result);
    }

    //@ApiOperation(value = "根据vid获取播放链接")
    //@GetMapping("getVideoUrl/{vid}")
    //public Result getVideoUrl(@PathVariable String vid) {
    //    String url = vodService.getVideoUrl(vid);
    //}
}

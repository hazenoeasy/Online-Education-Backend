package plus.yuhaozhang.service.cos.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import plus.yuhaozhang.commonUtils.Result;
import plus.yuhaozhang.service.cos.handler.CosConnectHandler;
import plus.yuhaozhang.service.cos.service.CosService;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Yuh Z
 * @date 1/19/22
 */
@RestController
@RequestMapping("edu/cos")
public class  CosController{
    @Autowired
    private CosService cosService;
    @PostMapping("upload")
    public Result uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        URL url = cosService.uploadFile(file);
        Map<String, URL> map = new HashMap<>();
        System.out.println(url);
        return Result.success(url);
    }
}
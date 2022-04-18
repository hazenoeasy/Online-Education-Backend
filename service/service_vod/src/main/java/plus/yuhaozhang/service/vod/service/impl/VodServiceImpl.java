package plus.yuhaozhang.service.vod.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import plus.yuhaozhang.service.vod.handler.VodConnectHandler;
import plus.yuhaozhang.service.vod.service.VodService;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Yuh Z
 * @date 1/28/22
 */
@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        VodConnectHandler vodConnectHandler = new VodConnectHandler();
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");
        String path = System.getProperty("java.io.tmpdir") + "/" + fileName;
        File convFile = new File(path);
        file.transferTo(convFile);
        return vodConnectHandler.save(fileName, path);
    }

    @Override
    public String getVideoUrl(String vid) throws IOException {
        VodConnectHandler vodConnectHandler = new VodConnectHandler();
        //vodConnectHandler
        return null;
    }
}

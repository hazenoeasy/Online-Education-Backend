package plus.yuhaozhang.service.cos.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import plus.yuhaozhang.commonUtils.Result;
import plus.yuhaozhang.service.cos.handler.CosConnectHandler;
import plus.yuhaozhang.service.cos.service.CosService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

/**
 * @author Yuh Z
 * @date 1/19/22
 */
@Service
public class CosServiceImpl implements CosService {
    @Override
    public URL uploadFile(MultipartFile file) throws IOException {
        CosConnectHandler cosConnectHandler = new CosConnectHandler();
        cosConnectHandler.open();

        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString()+"."+ StringUtils.substringAfterLast(originalFilename,".");
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+fileName);
        file.transferTo(convFile);
        URL url = cosConnectHandler.save(fileName,convFile);
        cosConnectHandler.close();
        return url;
    }
}

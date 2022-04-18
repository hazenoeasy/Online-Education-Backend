package plus.yuhaozhang.service.vod.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Yuh Z
 * @date 1/28/22
 */
public interface VodService {
    String uploadFile(MultipartFile file) throws Exception;

    String getVideoUrl(String vid) throws IOException;
}

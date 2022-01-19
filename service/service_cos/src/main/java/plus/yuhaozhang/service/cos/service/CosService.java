package plus.yuhaozhang.service.cos.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

/**
 * @author Yuh Z
 * @date 1/19/22
 */
public interface CosService {
    public URL uploadFile(MultipartFile file) throws IOException;
}

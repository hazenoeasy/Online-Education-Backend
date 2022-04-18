package plus.yuhaozhang.service.vod.handler;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import com.qcloud.vod.VodUploadClient;
import com.qcloud.vod.model.VodUploadRequest;
import com.qcloud.vod.model.VodUploadResponse;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author Yuh Z
 * @date 1/28/22
 */
@Data
public class VodConnectHandler {
    private VodUploadClient vodUploadClient;
    private String secretId;
    private String secretKey;
    private String region;
    private String bucketName;
    private String fold;
    private String properties = "vod.properties";
    private VodUploadRequest request;

    public VodConnectHandler() throws IOException {
        System.out.println(this.properties);
        Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource(this.properties));
        System.out.println(properties);
        this.secretId = properties.getProperty("secretId");
        this.secretKey = properties.getProperty("secretKey");
        this.region = properties.getProperty("region");
        this.bucketName = properties.getProperty("bucketName");
        this.fold = properties.getProperty("fold");
        this.request = new VodUploadRequest();
        this.vodUploadClient = new VodUploadClient(this.secretId, this.secretKey);
    }

    public VodConnectHandler(String propFile) throws IOException {
        Properties properties = PropertiesLoaderUtils.loadAllProperties(propFile);
        System.out.println(properties);
        this.secretId = properties.getProperty("secretId");
        this.secretKey = properties.getProperty("secretKey");
        this.region = properties.getProperty("region");
        this.bucketName = properties.getProperty("bucketName");
        this.fold = properties.getProperty("fold");
        this.request = new VodUploadRequest();
        this.vodUploadClient = new VodUploadClient(this.secretId, this.secretKey);
    }

    public String save(String fileName, String filepath) throws Exception {
        String date = new DateTime().toString("yyy/MM/");
        String path = this.fold + date + fileName;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date expirationDate = new Date(System.currentTimeMillis() + 1000L * 24L * 60L * 60L * 1000L);
        System.out.println(df.format(expirationDate));
        this.request.setMediaName(path);
        this.request.setMediaFilePath(filepath);
        this.request.setExpireTime(df.format(expirationDate));
        VodUploadResponse response = this.vodUploadClient.upload("ap-guangzhou", request);
        String mediaUrl = response.getMediaUrl();
        return response.getFileId();
    }
    //public String getUrl(String fid){
    //    this.vodUploadClient.
    //}
}

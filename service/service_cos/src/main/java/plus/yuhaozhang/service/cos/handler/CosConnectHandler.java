package plus.yuhaozhang.service.cos.handler;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

/**
 * @author Yuh Z
 * @date 12/15/21
 */
@Data
public class CosConnectHandler {
    private COSClient cosClient;
    private String secretId;
    private String secretKey;
    private String region;
    private String bucketName;
    private String fold;
    private String properties = "cos.properties";

    public CosConnectHandler() throws IOException {
        System.out.println(this.properties);
        Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource(this.properties));
        System.out.println(properties);
        this.secretId = properties.getProperty("secretId");
        this.secretKey = properties.getProperty("secretKey");
        this.region = properties.getProperty("region");
        this.bucketName = properties.getProperty("bucketName");
        this.fold = properties.getProperty("fold");
    }

    public CosConnectHandler(String propFile) throws IOException {
        Properties properties = PropertiesLoaderUtils.loadAllProperties(propFile);
        System.out.println(properties);
        this.secretId = properties.getProperty("secretId");
        this.secretKey = properties.getProperty("secretKey");
        this.region = properties.getProperty("region");
        this.bucketName = properties.getProperty("bucketName");
        this.fold = properties.getProperty("fold");
    }

    public COSClient open() throws IOException {
        // 1 初始化用户身份信息（secretId, secretKey）。


        COSCredentials cred = new BasicCOSCredentials(this.secretId, this.secretKey);
        Region region = new Region(this.region);
        ClientConfig clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        clientConfig.setSocketTimeout(30 * 1000);
        clientConfig.setConnectionTimeout(30 * 1000);
        // 3 生成 cos 客户端。
        cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }

    public void close() {
        cosClient.shutdown();
    }

    public URL save(String fileName, File file) throws IOException {
        String date = new DateTime().toString("yyy/MM/");
        String path = this.fold + date + fileName;
        PutObjectRequest putObjectRequest = new PutObjectRequest(this.bucketName, path, file);
        cosClient.putObject(putObjectRequest);
        Date expirationDate = new Date(System.currentTimeMillis() + 1000L * 24L * 60L * 60L * 1000L);
        URL url = cosClient.generatePresignedUrl(this.bucketName, path, expirationDate,
                HttpMethodName.GET);
        return url;
    }

}

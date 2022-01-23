package plus.yuhaozhang.service.edu.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import plus.yuhaozhang.service.edu.entity.Video;

/**
 * @author Yuh Z
 * @date 1/23/22
 */
@NoArgsConstructor
@ApiModel(value = "小结")
@Data
public class VideoVo {
    private String id;

    private String title;

    public VideoVo(Video video) {
        this.id = video.getId();
        this.title = video.getTitle();
    }
}

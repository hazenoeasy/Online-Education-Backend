package plus.yuhaozhang.service.edu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuh Z
 * @date 1/23/22
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChapterVo {
    private String id;
    private String title;
    private List<VideoVo> children = new ArrayList<>();
}

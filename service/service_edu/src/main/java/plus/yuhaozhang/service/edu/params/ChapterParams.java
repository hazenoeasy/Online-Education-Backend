package plus.yuhaozhang.service.edu.params;

import lombok.Data;
import lombok.NoArgsConstructor;
import plus.yuhaozhang.service.edu.entity.Chapter;

/**
 * @author Yuh Z
 * @date 1/23/22
 */
@NoArgsConstructor
@Data
public class ChapterParams {

    private String courseId;

    private String title;

    private int sort;

    public ChapterParams(Chapter chapter){
        this.courseId = chapter.getCourseId();
        this.title = chapter.getTitle();
        this.sort = chapter.getSort();
    }
}

package plus.yuhaozhang.service.edu.vo;

import lombok.Data;

/**
 * @author Yuh Z
 * @date 1/25/22
 */
@Data
public class CourseOverViewVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevel1;
    private String subjectLevel2;
    private String teacherName;
    private String price;
}

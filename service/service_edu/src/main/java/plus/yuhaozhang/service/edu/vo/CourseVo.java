package plus.yuhaozhang.service.edu.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import plus.yuhaozhang.service.edu.entity.Course;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Yuh Z
 * @date 1/22/22
 */
@NoArgsConstructor
@Data
public class CourseVo {

    @ApiModelProperty(value = "课程ID")
    private String id;

    @ApiModelProperty(value = "课程讲师ID")
    private String teacherId;

    @ApiModelProperty(value = "课程专业ID")
    private String subjectId;

    private String subjectParentId;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "销售数量")
    private Long buyCount;

    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;

    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布")
    private String status;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;

    public CourseVo(Course course) {
        this.id = course.getId();
        this.teacherId = course.getTeacherId();
        this.subjectId = course.getSubjectId();
        this.subjectParentId = course.getSubjectParentId();
        this.title = course.getTitle();
        this.price = course.getPrice();
        this.lessonNum = course.getLessonNum();
        this.cover = course.getCover();
        this.buyCount = course.getBuyCount();
        this.viewCount = course.getViewCount();
        this.status = course.getStatus();
        this.gmtCreate = course.getGmtCreate();
        this.gmtModified = course.getGmtModified();
    }
}

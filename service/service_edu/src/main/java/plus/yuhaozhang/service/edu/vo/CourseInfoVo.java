package plus.yuhaozhang.service.edu.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import plus.yuhaozhang.service.edu.entity.Course;
import plus.yuhaozhang.service.edu.entity.CourseDescription;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Yuh Z
 * @date 1/21/22
 */
@NoArgsConstructor
@Data
public class CourseInfoVo {

    /**
     *         title: '',
     *         subjectId: '', // 二级分类id
     *         subjectParentId: '', // 一级分类id
     *         teacherId: '',
     *         lessonNum: 0,
     *         description: '',
     *         cover: '/static/java.jpg',
     *         price: 0
     */
    @ApiModelProperty(value = "课程讲师ID")
    private String teacherId;

    @ApiModelProperty(value = "课程专业ID")
    private String subjectId;

    private String subjectParentId;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "课程时长")
    private Integer lessonNum;

    @ApiModelProperty(value = "课程简介")
    private String description;

    public CourseInfoVo(Course course) {
        this.title = course.getTitle();
        this.subjectId = course.getSubjectId();
        this.teacherId = course.getTeacherId();
        this.lessonNum = course.getLessonNum();
        this.cover = course.getCover();
        this.price = course.getPrice();
        this.subjectId = course.getSubjectId();
        this.subjectParentId = course.getSubjectParentId();
    }
}

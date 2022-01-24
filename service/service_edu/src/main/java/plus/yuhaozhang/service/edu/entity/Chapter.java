package plus.yuhaozhang.service.edu.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import plus.yuhaozhang.service.edu.params.ChapterParams;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author Yuh Z
 * @since 2022-01-21
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("edu_chapter")
@ApiModel(value="Chapter对象", description="课程")
public class Chapter implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "章节ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "课程ID")
    private String courseId;

    @ApiModelProperty(value = "章节名称")
    private String title;

    @ApiModelProperty(value = "显示排序")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    public Chapter(ChapterParams chapterParams) {
        this.courseId=chapterParams.getCourseId();
        this.title = chapterParams.getTitle();
        this.sort = chapterParams.getSort();
    }
}

package plus.yuhaozhang.service.edu.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Yuh Z
 * @date 1/22/22
 */
@Data
public class CourseQueryParams {
    @ApiModelProperty(value = "页数")
    private Integer page = 1;
    @ApiModelProperty(value = "行数")
    private Integer limit = 10;
    @ApiModelProperty("状态")
    private String status;
    @ApiModelProperty("名称")
    private String title;

}

package plus.yuhaozhang.service.edu.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Yuh Z
 * @date 1/20/22
 */
@Data
public class SubjectDTO {

    @ExcelProperty(index = 0)
    private String HeadSubject;

    @ExcelProperty(index = 1)
    private String SecondSubject;
}

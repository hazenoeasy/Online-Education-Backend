package plus.yuhaozhang.service.edu.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yuh Z
 * @date 1/17/22
 */
@NoArgsConstructor
@Data
public class PageParams {
    private Integer page = 1;
    private Integer pageSize = 10;
}

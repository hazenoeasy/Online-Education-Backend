package plus.yuhaozhang.service.edu.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuh Z
 * @date 1/21/22
 */
@Data
public class SubjectTree {
    private String id;
    private String label;
    private List<SubjectTree> children;

    public SubjectTree(String id, String label) {
        this.id = id;
        this.label = label;
    }
}

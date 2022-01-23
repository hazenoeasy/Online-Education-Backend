package plus.yuhaozhang.service.edu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import plus.yuhaozhang.commonUtils.Result;
import plus.yuhaozhang.service.edu.entity.Subject;
import plus.yuhaozhang.service.edu.service.SubjectService;
import plus.yuhaozhang.service.edu.vo.SubjectTree;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Yuh Z
 * @since 2022-01-20
 */
@RestController
@RequestMapping("/edu/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("addSubject")
    public Result addSubject(@RequestParam("file")MultipartFile file) throws IOException {
     subjectService.addSubject(file);
     return Result.success();
    }
    @GetMapping("getAll")
    public Result getAll(){
        List<SubjectTree> subjectList = subjectService.getAllByTree();
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",subjectList);
        return Result.success(map);
    }

}


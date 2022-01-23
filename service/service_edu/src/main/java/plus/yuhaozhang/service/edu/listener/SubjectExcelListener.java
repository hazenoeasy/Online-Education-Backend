package plus.yuhaozhang.service.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import plus.yuhaozhang.commonUtils.StatusCode;
import plus.yuhaozhang.service.edu.dto.SubjectDTO;
import plus.yuhaozhang.service.edu.entity.Subject;
import plus.yuhaozhang.service.edu.service.SubjectService;
import plus.yuhaozhang.service.edu.service.impl.SubjectServiceImpl;
import plus.yuhaozhang.servicebase.handler.exceptionHandler.PurPoseException;

/**
 * @author Yuh Z
 * @date 1/20/22
 * 不能交给spring进行管理，需要自己new，
 */
public class SubjectExcelListener implements ReadListener<SubjectDTO> {
    private SubjectService subjectService;
    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectDTO subjectDTO, AnalysisContext analysisContext) {
        if(subjectDTO == null && subjectDTO.getHeadSubject()== null){
            //throw new PurPoseException(StatusCode.INVALID_PARAM,"数据为空");
            return;
        }
        // 一行一行读取
        String headSubjectId = checkHeadSubject(subjectDTO);
        checkSecondSubject(subjectDTO,headSubjectId);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
    private String checkHeadSubject(SubjectDTO subjectDTO){
        QueryWrapper<Subject> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("title",subjectDTO.getHeadSubject());
        queryWrapper.eq("parent_id","0");
        Subject subject = this.subjectService.getOne(queryWrapper);
        // 代表没有一级列表 需要插入数据
        if(subject == null){
            Subject subject1 = new Subject();
            subject1.setTitle(subjectDTO.getHeadSubject());
            this.subjectService.save(subject1);
            return checkHeadSubject(subjectDTO);
        }else{
            return subject.getId();
        }
    }
    private void checkSecondSubject(SubjectDTO subjectDTO, String id){
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",subjectDTO.getSecondSubject()).eq("parent_id",id);
        Subject subject = this.subjectService.getOne(queryWrapper);
        if(subject == null ){
            Subject subject1 = new Subject();
            subject1.setParentId(id);
            subject1.setTitle(subjectDTO.getSecondSubject());
            this.subjectService.save(subject1);
        }
    }
}

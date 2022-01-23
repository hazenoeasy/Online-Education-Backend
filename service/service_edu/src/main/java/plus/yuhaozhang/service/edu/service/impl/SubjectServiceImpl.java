package plus.yuhaozhang.service.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import plus.yuhaozhang.commonUtils.StatusCode;
import plus.yuhaozhang.service.edu.dto.SubjectDTO;
import plus.yuhaozhang.service.edu.entity.Subject;
import plus.yuhaozhang.service.edu.listener.SubjectExcelListener;
import plus.yuhaozhang.service.edu.mapper.SubjectMapper;
import plus.yuhaozhang.service.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import plus.yuhaozhang.service.edu.vo.SubjectTree;
import plus.yuhaozhang.servicebase.handler.exceptionHandler.GlobalExceptionHandler;
import plus.yuhaozhang.servicebase.handler.exceptionHandler.PurPoseException;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Yuh Z
 * @since 2022-01-20
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    /**
     * add subject by excel file
     * @param file excel file
     */
    @Override
    public void addSubject(MultipartFile file) throws IOException {
        try {
             InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectDTO.class,new SubjectExcelListener(this)).sheet().doRead();
        } catch (Exception e){
            throw e;
        }
    }

    /**
     * 以树状图的结构返回结果
     * 这里采用哈希表来实现
     * 先排序 按照parent_id顺序
     * 要是parent_id 为 0 就说明是一级目录, 插入map
     * 要是parent_id 不为0 就说明是二级目录, 在map中按parent_id查到父亲，
     * 要是没有 就说明数据有误。
     * 要是有，插入父亲的children中
     * @return
     */
    @Override
    public List<SubjectTree> getAllByTree() {
        // 获得数据list
        List<Subject> subjectLists = this.list();
        // 排序
        Collections.sort(subjectLists,(a,b)->{return a.getParentId().compareTo(b.getParentId());});
        // 创建结果对象
        List<SubjectTree> subjectTrees = new ArrayList<SubjectTree>();
        // 创建查询的map
        Map<String,SubjectTree> map= new HashMap<>();
        for(Subject subject: subjectLists){
            // 判断parent_id 是否为空
            if(StringUtils.isEmpty(subject.getParentId())){
                throw new PurPoseException(StatusCode.SYSTEM_FAULT,"系统数据库有误");
            }
            // 判断parent_id 是否为 "0"
            if(StringUtils.equals(subject.getParentId(),"0")){
                // 插入父亲
                SubjectTree subjectTree = new SubjectTree(subject.getId(), subject.getTitle());
                subjectTree.setChildren(new ArrayList<SubjectTree>());
                map.put(subject.getId(),subjectTree);
                subjectTrees.add(subjectTree);
                continue;
            }
            // parent_id 不为空  不为 0 判断是否有父类
            if(!map.containsKey(subject.getParentId())){
                throw new PurPoseException(StatusCode.SYSTEM_FAULT,"数据没有一级parent");
            }
            // 插入父亲的children
            SubjectTree subjectTree = new SubjectTree(subject.getId(), subject.getTitle());
            map.get(subject.getParentId()).getChildren().add(subjectTree);
        }
        return subjectTrees;
    }
}

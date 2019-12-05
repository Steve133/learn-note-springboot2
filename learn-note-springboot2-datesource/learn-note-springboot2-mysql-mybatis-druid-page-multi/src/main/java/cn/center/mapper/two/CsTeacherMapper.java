package cn.center.mapper.two;

import cn.center.pojo.CsTeacher;
import cn.center.pojo.CsTeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CsTeacherMapper {
    long countByExample(CsTeacherExample example);

    int deleteByExample(CsTeacherExample example);

    int deleteByPrimaryKey(String id);

    int insert(CsTeacher record);

    int insertSelective(CsTeacher record);

    List<CsTeacher> selectByExample(CsTeacherExample example);

    CsTeacher selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CsTeacher record, @Param("example") CsTeacherExample example);

    int updateByExample(@Param("record") CsTeacher record, @Param("example") CsTeacherExample example);

    int updateByPrimaryKeySelective(CsTeacher record);

    int updateByPrimaryKey(CsTeacher record);
}
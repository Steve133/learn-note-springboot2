package cn.center.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.center.pojo.CsTest;
import cn.center.pojo.CsTestExample;

public interface CsTestMapper {
    long countByExample(CsTestExample example);

    int deleteByExample(CsTestExample example);

    int deleteByPrimaryKey(String id);

    int insert(CsTest record);

    int insertSelective(CsTest record);

    List<CsTest> selectByExample(CsTestExample example);

    CsTest selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CsTest record, @Param("example") CsTestExample example);

    int updateByExample(@Param("record") CsTest record, @Param("example") CsTestExample example);

    int updateByPrimaryKeySelective(CsTest record);

    int updateByPrimaryKey(CsTest record);
}
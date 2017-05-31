package tj.pwv.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import tj.pwv.pojo.MwrZenit;
import tj.pwv.pojo.MwrZenitExample;

public interface MwrZenitMapper {
    int countByExample(MwrZenitExample example);

    int deleteByExample(MwrZenitExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MwrZenit record);

    int insertSelective(MwrZenit record);

    List<MwrZenit> selectByExample(MwrZenitExample example);

    MwrZenit selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MwrZenit record, @Param("example") MwrZenitExample example);

    int updateByExample(@Param("record") MwrZenit record, @Param("example") MwrZenitExample example);

    int updateByPrimaryKeySelective(MwrZenit record);

    int updateByPrimaryKey(MwrZenit record);
}
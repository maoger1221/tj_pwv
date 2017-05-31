package tj.pwv.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import tj.pwv.pojo.MwrZenit30min;
import tj.pwv.pojo.MwrZenit30minExample;

public interface MwrZenit30minMapper {
    int countByExample(MwrZenit30minExample example);

    int deleteByExample(MwrZenit30minExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MwrZenit30min record);

    int insertSelective(MwrZenit30min record);

    List<MwrZenit30min> selectByExample(MwrZenit30minExample example);

    MwrZenit30min selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MwrZenit30min record, @Param("example") MwrZenit30minExample example);

    int updateByExample(@Param("record") MwrZenit30min record, @Param("example") MwrZenit30minExample example);

    int updateByPrimaryKeySelective(MwrZenit30min record);

    int updateByPrimaryKey(MwrZenit30min record);
}
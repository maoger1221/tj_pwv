package tj.pwv.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import tj.pwv.pojo.Mwr2d;
import tj.pwv.pojo.Mwr2dExample;

public interface Mwr2dMapper {
    int countByExample(Mwr2dExample example);

    int deleteByExample(Mwr2dExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Mwr2d record);

    int insertSelective(Mwr2d record);

    List<Mwr2d> selectByExample(Mwr2dExample example);

    Mwr2d selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Mwr2d record, @Param("example") Mwr2dExample example);

    int updateByExample(@Param("record") Mwr2d record, @Param("example") Mwr2dExample example);

    int updateByPrimaryKeySelective(Mwr2d record);

    int updateByPrimaryKey(Mwr2d record);
}
package tj.pwv.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tj.pwv.pojo.Pwv;
import tj.pwv.pojo.PwvExample;

public interface PwvMapper {
    int countByExample(PwvExample example);

    int deleteByExample(PwvExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Pwv record);

    int insertSelective(Pwv record);

    List<Pwv> selectByExample(PwvExample example);

    Pwv selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Pwv record, @Param("example") PwvExample example);

    int updateByExample(@Param("record") Pwv record, @Param("example") PwvExample example);

    int updateByPrimaryKeySelective(Pwv record);

    int updateByPrimaryKey(Pwv record);

    @Select({"select * from pwv order by id desc limit 0,1"})
    Pwv selectLatest();
}
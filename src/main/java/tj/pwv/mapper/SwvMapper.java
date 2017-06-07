package tj.pwv.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tj.pwv.pojo.Swv;
import tj.pwv.pojo.SwvExample;

public interface SwvMapper {
    int countByExample(SwvExample example);

    int deleteByExample(SwvExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Swv record);

    int insertSelective(Swv record);

    List<Swv> selectByExample(SwvExample example);

    Swv selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Swv record, @Param("example") SwvExample example);

    int updateByExample(@Param("record") Swv record, @Param("example") SwvExample example);

    int updateByPrimaryKeySelective(Swv record);

    int updateByPrimaryKey(Swv record);

    @Select({"select * from swv where prn=#{prn} order by id desc limit 0,1"})
    Swv selectLatest(int prn);
}
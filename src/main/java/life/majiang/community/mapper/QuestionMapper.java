package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title, description, creator, gmt_create, gmt_modified, tag) values " +
            "(#{title}, #{description}, #{creator}, #{gmtCreate}, #{gmtModified}, #{tag})")
    void insert(Question question);

    @Select("select * from question")
    List<Question> list();
}

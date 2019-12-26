package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title, description, creator, gmt_create, gmt_modified, tag) values " +
            "(#{title}, #{description}, #{creator}, #{gmtCreate}, #{gmtModified}, #{tag})")
    void insert(Question question);

    @Select("select * from question limit #{offset}, #{size}")
    List<Question> list(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question, user where question.creator = user.id and user.account_id = #{accountId} limit #{offset}, #{size}")
    List<Question> listByUser(@Param("accountId") String accountId, @Param("offset") int offset, @Param("size") Integer size);

    @Select("select count(1) from question, user where question.creator = user.id and user.account_id = #{accountId}")
    Integer countByAccountId(@Param("accountId") String accountId);

    @Select("select * from question where question.id = #{id}")
    Question findById(@Param("id") Integer id);

    @Update("Update question set title = #{title}, description = #{description}, gmt_modified = #{gmtModified}," +
            " tag = #{tag} where id = #{id}")
    void update(Question question);
}

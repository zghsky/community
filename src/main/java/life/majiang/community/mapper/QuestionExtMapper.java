package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionExtMapper {

    int incView(@Param("record") Question record);
    int incCommentCount(@Param("record") Question record);
    List<Question> selectRelated(Question question);
}
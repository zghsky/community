package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.Param;

public interface QuestionExtMapper {

    int incView(@Param("record") Question record);
    int incCommentCount(@Param("record") Question record);
}
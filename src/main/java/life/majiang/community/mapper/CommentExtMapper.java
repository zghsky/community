package life.majiang.community.mapper;

import life.majiang.community.model.Comment;
import org.apache.ibatis.annotations.Param;

public interface CommentExtMapper {

    int incCommentCount(@Param("record") Comment record);
}
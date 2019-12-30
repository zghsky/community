package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {

    private Integer id;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private String title;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private String description;

    private User user;
}

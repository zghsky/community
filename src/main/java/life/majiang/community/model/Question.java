package life.majiang.community.model;

import lombok.Data;

@Data
public class Question {

    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Integer creator;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
}

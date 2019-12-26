package life.majiang.community.service;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalPage;
        Integer count = questionMapper.count();

        if (count % size == 0) {
            totalPage = count / size;
        } else {
            totalPage = count / size + 1;
        }
        page = page < 1 ? 1 : page;
        page = page > totalPage ? totalPage : page;

        paginationDTO.setPagination(totalPage, page);

        int offset = size * (page - 1);
        List<Question> list = questionMapper.list(offset, size);
        List<QuestionDTO> res = new ArrayList<>();

        for (Question question : list) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            res.add(questionDTO);
        }
        paginationDTO.setQuestions(res);

        return paginationDTO;
    }

    public PaginationDTO list(String accountId, Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalPage;
        Integer count = questionMapper.countByAccountId(accountId);

        if (count % size == 0) {
            totalPage = count / size;
        } else {
            totalPage = count / size + 1;
        }
        page = page < 1 ? 1 : page;
        page = page > totalPage ? totalPage : page;

        paginationDTO.setPagination(totalPage, page);

        int offset = size * (page - 1);
        List<Question> list = questionMapper.listByUser(accountId, offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Question question : list) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);

        return paginationDTO;
    }

    public QuestionDTO findById(Integer id) {
        Question question = questionMapper.findById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);

        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }
        else {
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.update(question);
        }
    }
}

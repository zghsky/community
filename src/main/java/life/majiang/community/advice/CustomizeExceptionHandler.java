package life.majiang.community.advice;

import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.exception.CustomizerErrorCode;
import life.majiang.community.exception.CustomizerException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable ex, Model model, HttpServletResponse response) {
        String content = request.getContentType();
        ResultDTO resultDTO;

        if ("application/json".equals(content)) {
            // 返回 json
            if (ex instanceof CustomizerException)
                resultDTO = ResultDTO.errorOf((CustomizerException) ex);
            else
                resultDTO =  ResultDTO.errorOf(CustomizerErrorCode.SYS_ERROR);

            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

            return null;
        } else {
            // 错误跳转页面
            if (ex instanceof CustomizerException)
                model.addAttribute("message", ex.getMessage());
            else
                model.addAttribute("message", CustomizerErrorCode.SYS_ERROR.getMessage());
        }
        return new ModelAndView("error");
    }
}
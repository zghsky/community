package life.majiang.community.controller;

import life.majiang.community.Mapper.UserMapper;
import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GitHubUser;
import life.majiang.community.model.User;
import life.majiang.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    public String clientId;

    @Value("${github.client.secret}")
    public String clientSecret;

    @Value("${github.redirect.uri}")
    public String redirectUrl;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUrl);
        accessTokenDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUser gitHubUser = gitHubProvider.getUser(accessToken);
        System.out.println(gitHubUser.getLogin());
        System.out.println(gitHubUser.getId().toString());

        if (gitHubUser != null) {
            // 登陆成功 写cookie和session
            User user = new User();
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setName(gitHubUser.getLogin());
            user.setToken(UUID.randomUUID().toString());
            userMapper.insert(user);

            request.getSession().setAttribute("user", gitHubUser);
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }
}

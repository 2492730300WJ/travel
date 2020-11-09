package travel.api.user.controller;

import travel.api.config.response.CommonReturnController;
import travel.api.config.response.WorkResponse;
import travel.api.config.response.WorkStatus;
import travel.api.user.entity.User;
import travel.api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("api")
public class UserController extends CommonReturnController {

    @Autowired
    UserService userService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public void login(HttpServletResponse response, @RequestBody User user) {
        this.commonResponse(response, new WorkResponse(WorkStatus.SUCCESS, userService.login(user)));
    }

}
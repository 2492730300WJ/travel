package travel.api.user.controller;

import travel.api.config.jwt.UserLoginToken;
import travel.api.config.response.CommonReturnController;
import travel.api.config.response.WorkResponse;
import travel.api.config.response.WorkStatus;
import travel.api.dto.request.user.UserRequestDTO;
import travel.api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("api/user")
public class UserController extends CommonReturnController {

    @Autowired
    UserService userService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public void login(HttpServletResponse response, @RequestBody UserRequestDTO userRequestDTO) {
        this.commonResponse(response, new WorkResponse(WorkStatus.SUCCESS, userService.login(userRequestDTO)));
    }

    /**
     * 用户信息
     */
    @PostMapping("/info")
    @UserLoginToken
    public void info(HttpServletResponse response, @RequestBody UserRequestDTO userRequestDTO) {
        this.commonResponse(response, new WorkResponse(WorkStatus.SUCCESS, userService.info(userRequestDTO)));
    }

}
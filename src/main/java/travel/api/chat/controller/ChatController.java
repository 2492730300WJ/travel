package travel.api.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travel.api.chat.service.ChatService;
import travel.api.config.jwt.UserLoginToken;
import travel.api.config.response.CommonReturnController;
import travel.api.config.response.WorkResponse;
import travel.api.config.response.WorkStatus;
import travel.api.dto.request.chat.ChatRequestDTO;
import travel.api.dto.request.user.UserRequestDTO;
import travel.api.user.service.UserService;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("api/chat")
public class ChatController extends CommonReturnController {

    @Autowired
    ChatService chatService;

    /**
     * 好友
     */
    @PostMapping("/friend")
    public void friend(HttpServletResponse response, @RequestBody UserRequestDTO userRequestDTO) {
        this.commonResponse(response, new WorkResponse(WorkStatus.SUCCESS, chatService.friendList(userRequestDTO)));
    }

    /**
     * 私聊
     */
    @PostMapping("/private-msg")
    public void privateMsg(HttpServletResponse response, @RequestBody ChatRequestDTO requestDTO) {
        this.commonResponse(response, new WorkResponse(WorkStatus.SUCCESS, chatService.privateMsg(requestDTO)));
    }

}
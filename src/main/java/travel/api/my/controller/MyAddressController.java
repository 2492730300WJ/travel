package travel.api.my.controller;

import travel.api.config.jwt.UserLoginToken;
import travel.api.config.response.CommonReturnController;
import travel.api.config.response.WorkResponse;
import travel.api.config.response.WorkStatus;
import travel.api.my.service.MyAddressService;
import travel.api.user.entity.User;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: dd
 * 我的-我的地址相关接口
 */
@RestController
@RequestMapping("api/address")
public class MyAddressController extends CommonReturnController {

    @Autowired
    private MyAddressService myAddressService;

    /**
     * 获取我的地址
     * @param response
     * @param user
     */
    @PostMapping("/get")
    @UserLoginToken
    public void getAddress(HttpServletResponse response, @RequestBody User user) {
        JSONObject json = myAddressService.getAddress(user);
        this.commonResponse(response, new WorkResponse(WorkStatus.SUCCESS, json));
    }

    /**
     * 增加地址
     * @param response
     * @param user
     */
    @PostMapping("/insert")
    @UserLoginToken
    public void insertAddress(HttpServletResponse response, @RequestBody User user) {
        JSONObject json = myAddressService.getAddress(user);
        this.commonResponse(response, new WorkResponse(WorkStatus.SUCCESS, json));
    }

}

package travel.api.user.controller;

import travel.api.config.jwt.UserLoginToken;
import travel.api.user.entity.User;
import travel.api.error.WorkException;
import travel.api.error.WorkResponse;
import travel.api.error.WorkStatus;
import travel.api.user.service.UserService;
import lombok.extern.log4j.Log4j2;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 */
@Log4j2
@RestController
@RequestMapping("api")
public class UserController {
    @Autowired
    UserService userService;
    /**
     *     登录
     */
    @PostMapping("/login")
    public WorkResponse login(@RequestBody User user){
        log.info("接受到访问登录按钮的请求");
        WorkStatus workStatus = null;
        WorkResponse workResponse;
        JSONObject json = null;
        try {
            json = userService.login(user);
            log.info("访问登录按钮请求成功，生成token："+json.get("token")+"，refreshToken："+json.get("refreshToken"));
        }catch (WorkException work){
            workStatus = work.getWorkStatus();
            log.info("访问登录按钮请求失败，失败原因：" + work.getExceptionMsg());
        }catch (Exception e) {
            workStatus = WorkStatus.FAIL;
            e.printStackTrace();
            log.info("访问登录按钮请求失败，失败原因：系统出现异常");
        } finally {
            workResponse = new WorkResponse(workStatus,json);
        }
        return workResponse;
    }


    @UserLoginToken
    @PostMapping("/getUserInfo")
    public WorkResponse getMessage(String token,String refreshToken){
        log.info("接受到访问获取用户信息的请求");
        WorkStatus workStatus = null;
        WorkResponse workResponse;
        JSONObject json = null;
        try {
//            json = userService.login(user);
            log.info("访问获取用户信息请求成功，生成token："+json.get("token")+"，refreshToken："+json.get("refreshToken"));
        }catch (WorkException work){
            workStatus = work.getWorkStatus();
            log.info("访问获取用户信息请求失败，失败原因：" + work.getExceptionMsg());
        }catch (Exception e) {
            workStatus = WorkStatus.FAIL;
            e.printStackTrace();
            log.info("访问获取用户信息请求失败，失败原因：系统出现异常");
        } finally {
            workResponse = new WorkResponse(workStatus,json);
        }
        return workResponse;
    }

    @RequestMapping("/get")
    public WorkResponse getM(){
        log.info("接受到访问获取用户信息的请求");
        WorkStatus workStatus = null;
        WorkResponse workResponse;
        JSONObject json = null;
        try {
//            json = userService.login(user);
            log.info("访问获取用户信息请求成功，生成token："+json.get("token")+"，refreshToken："+json.get("refreshToken"));
        }catch (WorkException work){
            workStatus = work.getWorkStatus();
            log.info("访问获取用户信息请求失败，失败原因：" + work.getExceptionMsg());
        }catch (Exception e) {
            workStatus = WorkStatus.FAIL;
            e.printStackTrace();
            log.info("访问获取用户信息请求失败，失败原因：系统出现异常");
        } finally {
            workResponse = new WorkResponse(workStatus,json);
        }
        return workResponse;
    }
}
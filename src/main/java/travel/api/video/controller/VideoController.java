package travel.api.video.controller;

import travel.api.config.jwt.UserLoginToken;
import travel.api.error.WorkException;
import travel.api.error.WorkResponse;
import travel.api.error.WorkStatus;
import travel.api.my.service.MyAddressService;
import travel.api.mybatisplus.table.entity.UserAddress;
import travel.api.user.entity.User;
import lombok.extern.log4j.Log4j2;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travel.api.video.service.VideoService;

/**
 * @Author: dd
 * 我的-我的地址相关接口
 */
@Log4j2
@RestController
@RequestMapping("api/video")
public class VideoController {

    @Autowired
    private VideoService videoService;


    @PostMapping("/get")
    public WorkResponse getVideo() {
        log.info("接收到访问视频页面请求");
        WorkStatus workStatus = null;
        WorkResponse workResponse;
        JSONObject json = null;
        try {
            json = videoService.getVideo();
            log.info("访问视频页面请求成功");
        } catch (WorkException work) {
            workStatus = work.getWorkStatus();
            log.info("访问视频页面请求失败，失败原因：" + work.getExceptionMsg());
        } catch (Exception e) {
            workStatus = WorkStatus.FAIL;
            e.printStackTrace();
            log.info("访问视频页面请求失败，失败原因：系统出现异常");
        } finally {
            workResponse = new WorkResponse(workStatus, json);
        }
        return workResponse;
    }

}

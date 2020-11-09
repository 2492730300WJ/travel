package travel.api.video.controller;

import travel.api.config.response.CommonReturnController;
import travel.api.config.response.WorkResponse;
import travel.api.config.response.WorkStatus;
import travel.api.mybatisplus.table.entity.VideoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travel.api.video.service.VideoService;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: dd
 * 我的-我的地址相关接口
 */
@RestController
@RequestMapping("api/video")
public class VideoController extends CommonReturnController {

    @Autowired
    private VideoService videoService;

    /**
     * 获取视频列表
     * @param response
     */
    @PostMapping("/list")
    public void getVideoList(HttpServletResponse response) {
        this.commonResponse(response, new WorkResponse(WorkStatus.SUCCESS, videoService.getVideoList()));
    }

    /**
     * 获取视频详情
     * @param response
     */
    @PostMapping("/get")
    public void getVideoDetail(HttpServletResponse response, @RequestBody VideoInfo videoInfo) {
        this.commonResponse(response, new WorkResponse(WorkStatus.SUCCESS, videoService.getVideoDetail(videoInfo)));
    }

}

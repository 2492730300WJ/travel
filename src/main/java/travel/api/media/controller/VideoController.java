package travel.api.media.controller;

import travel.api.config.response.CommonReturnController;
import travel.api.config.response.WorkResponse;
import travel.api.config.response.WorkStatus;
import travel.api.media.dto.MediaRequestDTO;
import travel.api.table.entity.MediaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travel.api.media.service.VideoService;

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
    public void getVideoList(HttpServletResponse response, @RequestBody MediaRequestDTO requestDTO) {
        this.commonResponse(response, new WorkResponse(WorkStatus.SUCCESS, videoService.getVideoList(requestDTO)));
    }

    /**
     * 获取视频详情
     * @param response
     */
    @PostMapping("/get")
    public void getVideoDetail(HttpServletResponse response, @RequestBody MediaInfo mediaInfo) {
        this.commonResponse(response, new WorkResponse(WorkStatus.SUCCESS, videoService.getVideoDetail(mediaInfo)));
    }

}

package travel.api.video.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.api.mybatisplus.table.entity.UserAddress;
import travel.api.mybatisplus.table.entity.VideoDanmaku;
import travel.api.mybatisplus.table.entity.VideoInfo;
import travel.api.mybatisplus.table.mapper.VideoDanmakuMapper;
import travel.api.mybatisplus.table.mapper.VideoInfoMapper;

import java.util.List;

/**
 * @Author: dd
 */
@Service
public class VideoService {
    @Autowired
    private VideoInfoMapper videoInfoMapper;

    @Autowired
    private VideoDanmakuMapper videoDanmakuMapper;

    public JSONObject getVideoList() {
        JSONObject result = new JSONObject();
        LambdaQueryWrapper<VideoInfo> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(VideoInfo::getStatus, 0);
        List<VideoInfo> list = videoInfoMapper.selectList(queryWrapper);
        result.put("videoList",list);
        return result;
    }

    public JSONObject getVideoDetail(VideoInfo videoInfo) {
        JSONObject result = new JSONObject();
        // 获取视频信息
        LambdaQueryWrapper<VideoInfo> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(VideoInfo::getVideoId, videoInfo.getVideoId());
        VideoInfo video = videoInfoMapper.selectOne(queryWrapper);
        // 获取弹幕信息
        LambdaQueryWrapper<VideoDanmaku> danmakuQueryWrapper = new LambdaQueryWrapper();
        danmakuQueryWrapper.eq(VideoDanmaku::getVideoId, videoInfo.getVideoId());
        List<VideoDanmaku> danmakuList = videoDanmakuMapper.selectList(danmakuQueryWrapper);

        result.put("video",video);
        result.put("danmakuList",danmakuList);
        return result;
    }
}

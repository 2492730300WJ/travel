package travel.api.media.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.api.media.dto.MediaRequestDTO;
import travel.api.table.entity.VideoDanmaku;
import travel.api.table.entity.MediaInfo;
import travel.api.table.mapper.VideoDanmakuMapper;
import travel.api.table.mapper.MediaInfoMapper;

import java.util.List;

/**
 * @Author: dd
 */
@Service
public class VideoService {
    @Autowired
    private MediaInfoMapper mediaInfoMapper;

    @Autowired
    private VideoDanmakuMapper videoDanmakuMapper;

    public JSONObject getVideoList(MediaRequestDTO requestDTO) {
        JSONObject result = new JSONObject();
        LambdaQueryWrapper<MediaInfo> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(MediaInfo::getStatus, 0);
        queryWrapper.eq(MediaInfo::getType, requestDTO.getType());
        queryWrapper.orderByDesc(MediaInfo::getCreateTime);
        Page<MediaInfo> page = new Page<>(requestDTO.getPageNo(),requestDTO.getPageSize());
        IPage<MediaInfo> page1 = mediaInfoMapper.selectPage(page,queryWrapper);
        result.put("videoList",page1.getRecords());
        return result;
    }

    public JSONObject getVideoDetail(MediaInfo mediaInfo) {
        JSONObject result = new JSONObject();
        // 获取视频信息
        LambdaQueryWrapper<MediaInfo> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(MediaInfo::getMediaId, mediaInfo.getMediaId());
        MediaInfo video = mediaInfoMapper.selectOne(queryWrapper);
        // 获取弹幕信息
        LambdaQueryWrapper<VideoDanmaku> danmakuQueryWrapper = new LambdaQueryWrapper();
        danmakuQueryWrapper.eq(VideoDanmaku::getMediaId, mediaInfo.getMediaId());
        List<VideoDanmaku> danmakuList = videoDanmakuMapper.selectList(danmakuQueryWrapper);
        result.put("video",video);
        result.put("danmakuList",danmakuList);
        return result;
    }
}

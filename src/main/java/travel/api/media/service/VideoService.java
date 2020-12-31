package travel.api.media.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public JSONObject getVideoList() {
        JSONObject result = new JSONObject();
        LambdaQueryWrapper<MediaInfo> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(MediaInfo::getStatus, 0);
        List<MediaInfo> list = mediaInfoMapper.selectList(queryWrapper);
        JSONArray jsonArray = new JSONArray();
        com.alibaba.fastjson.JSONObject jsonObject;
        for (int i = 0; i < list.size(); i++) {
            jsonObject = (com.alibaba.fastjson.JSONObject) com.alibaba.fastjson.JSONObject.toJSON(list.get(i));
            if( i == 0){
                jsonObject.put("flag",true);
            }else {
                jsonObject.put("flag",false);
            }
            jsonArray.add(jsonObject);
        }
        result.put("videoList",jsonArray);
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

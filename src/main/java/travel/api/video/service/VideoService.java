package travel.api.video.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.api.mybatisplus.table.entity.UserAddress;
import travel.api.mybatisplus.table.entity.VideoInfo;
import travel.api.mybatisplus.table.mapper.VideoInfoMapper;

import java.util.List;

/**
 * @Author: dd
 */
@Service
public class VideoService {
    @Autowired
    private VideoInfoMapper videoInfoMapper;

    public JSONObject getVideo() {
        JSONObject result = new JSONObject();
        LambdaQueryWrapper<VideoInfo> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(VideoInfo::getStatus, 0);
        List<VideoInfo> list = videoInfoMapper.selectList(queryWrapper);
        result.put("videoList",list);
        return result;
    }
}

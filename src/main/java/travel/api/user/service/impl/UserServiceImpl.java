package travel.api.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang.StringUtils;
import travel.api.config.jwt.TokenService;
import travel.api.config.session.SessionInfo;
import travel.api.config.response.WorkException;
import travel.api.config.response.WorkStatus;
import travel.api.dto.request.user.UserRequestDTO;
import travel.api.dto.response.user.UserInfoResponseDTO;
import travel.api.table.entity.User;
import travel.api.table.mapper.UserMapper;
import travel.api.user.service.UserService;
import travel.api.util.RedisUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author: dd
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    TokenService tokenService;

    @Override
    public JSONObject login(UserRequestDTO userRequestDTO) {
        JSONObject jsonObject = new JSONObject();
        User userInfo = null;
        // 手机号登录
        if (null != userRequestDTO.getPhone()) {
            userInfo = userMapper.selectOne(new QueryWrapper<User>().eq("phone", userRequestDTO.getPhone()));
            if (userInfo == null) {
                throw new WorkException(WorkStatus.PASSWORD_IS_ERROR);
            }
        }
        if (StringUtils.isNotBlank(userRequestDTO.getUserCard())) {
            userInfo = userMapper.selectOne(new QueryWrapper<User>().eq("user_card", userRequestDTO.getUserCard()).eq("user_password", userRequestDTO.getPassword()));
            if (userInfo == null) {
                throw new WorkException(WorkStatus.PASSWORD_IS_ERROR);
            }
        }

        // 存入session
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setUserId(userInfo.getUserId());
        sessionInfo.setUserName(userInfo.getUserName());
        sessionInfo.setUserCard(userInfo.getUserCard());
        sessionInfo.setAvatar(userInfo.getAvatar());

        // 登陆时获取长Token和短Token
        String token = tokenService.getToken(sessionInfo);
        String refreshToken = tokenService.getRefreshToken(sessionInfo);
        jsonObject.put("token", token);
        jsonObject.put("refreshToken", refreshToken);
        // 存入redis
        redisUtil.set("token" + userInfo.getUserId(), token);
        redisUtil.set("refreshToken" + userInfo.getUserId(), refreshToken);
        jsonObject.put("user", sessionInfo);
        return jsonObject;
    }

    @Override
    public JSONObject info(UserRequestDTO userRequestDTO) {
        if (null == userRequestDTO.getUserId()) {
            throw new WorkException(WorkStatus.CHECK_PARAM);
        }
        UserInfoResponseDTO userInfo = userMapper.userInfo(userRequestDTO.getUserId());
        JSONObject jsonObject = new JSONObject();
        // 计算成长值百分比
        int progress = Math.round(userInfo.getGrow() / userInfo.getLevel());
        userInfo.setProgressWidth(progress + "%");
        jsonObject.put("user", userInfo);
        return jsonObject;
    }
}

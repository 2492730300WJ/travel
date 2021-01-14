package travel.api.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang.StringUtils;
import travel.api.config.jwt.TokenService;
import travel.api.config.session.SessionInfo;
import travel.api.config.response.WorkException;
import travel.api.config.response.WorkStatus;
import travel.api.table.entity.SysUserInfo;
import travel.api.table.mapper.SysUserInfoMapper;
import travel.api.user.entity.User;
import travel.api.util.RedisUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author: dd
 */
@Service
public class UserService {

    @Autowired
    private SysUserInfoMapper sysUserInfoMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    TokenService tokenService;

    public JSONObject login(User user) {
        JSONObject jsonObject = new JSONObject();
        SysUserInfo userInfo = null;
        // 手机号登录
        if (null != user.getPhone()) {
            userInfo = sysUserInfoMapper.selectOne(new QueryWrapper<SysUserInfo>().eq("phone", user.getPhone()));
            if (userInfo == null) {
                throw new WorkException(WorkStatus.PASSWORD_IS_ERROR);
            }
        }
        if (StringUtils.isNotBlank(user.getUserCard())) {
            userInfo = sysUserInfoMapper.selectOne(new QueryWrapper<SysUserInfo>().eq("user_card", user.getUserCard()).eq("user_password", user.getPassword()));
            if (userInfo == null) {
                throw new WorkException(WorkStatus.PASSWORD_IS_ERROR);
            }
        }

        // 存入session
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setUserId(userInfo.getUserId());
        sessionInfo.setUserName(userInfo.getUserName());
        sessionInfo.setUserCard(userInfo.getUserCard());

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

    public JSONObject info(User user) {
        if (null == user.getUserId()) {
            throw new WorkException(WorkStatus.CHECK_PARAM);
        }
        SysUserInfo userInfo = sysUserInfoMapper.selectOne(new QueryWrapper<SysUserInfo>().eq("user_id", user.getUserId()));
        JSONObject jsonObject = new JSONObject();
        user.setPassword(null);
        jsonObject.put("user",userInfo);
        return jsonObject;
    }
}

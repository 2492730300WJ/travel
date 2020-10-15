package travel.api.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import travel.api.config.jwt.TokenService;
import travel.api.config.session.SessionInfo;
import travel.api.error.WorkException;
import travel.api.error.WorkStatus;
import travel.api.mybatisplus.table.entity.SysUserInfo;
import travel.api.mybatisplus.table.mapper.SysUserInfoMapper;
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
        SysUserInfo userInfo = sysUserInfoMapper.selectOne(new QueryWrapper<SysUserInfo>().eq("user_card", user.getUserCard()).eq("user_password", user.getPassword()));
        if (userInfo == null) {
            throw new WorkException(WorkStatus.PASSWORD_IS_ERROR);
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
}

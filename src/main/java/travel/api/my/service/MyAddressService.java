package travel.api.my.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import travel.api.mybatisplus.table.entity.UserAddress;
import travel.api.mybatisplus.table.mapper.UserAddressMapper;
import travel.api.user.entity.User;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: dd
 */
@Service
public class MyAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;
    /**
     * 我的地址列表
     * @return
     */
    public JSONObject getAddress(User user) {
        JSONObject result = new JSONObject();
        LambdaQueryWrapper<UserAddress> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(UserAddress::getUserId,user.getUserId());
        List<UserAddress> list = userAddressMapper.selectList(queryWrapper);
        result.put("addressList",list);
        return result;
    }
}

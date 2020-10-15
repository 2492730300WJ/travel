package travel.api.mybatisplus.table.service.impl;

import travel.api.mybatisplus.table.entity.UserAddress;
import travel.api.mybatisplus.table.mapper.UserAddressMapper;
import travel.api.mybatisplus.table.service.IUserAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wj
 * @since 2020-07-29
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements IUserAddressService {

}

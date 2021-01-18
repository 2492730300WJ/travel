package travel.api.table.mapper;

import travel.api.dto.response.user.UserInfoResponseDTO;
import travel.api.table.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author dd
 * @since 2020-07-27
 */
public interface UserMapper extends BaseMapper<User> {
    UserInfoResponseDTO userInfo(Long userId);
}

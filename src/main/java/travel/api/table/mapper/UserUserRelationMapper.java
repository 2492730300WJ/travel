package travel.api.table.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import travel.api.dto.response.chat.FriendResponseDTO;

import java.util.List;

/**
 * @Author: dd
 */
public interface UserUserRelationMapper extends BaseMapper<UserUserRelationMapper> {
    List<FriendResponseDTO> friendList(Long userId);
}

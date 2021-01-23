package travel.api.table.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import travel.api.dto.request.chat.ChatRequestDTO;
import travel.api.dto.response.chat.PrivateMsgResponseDTO;
import travel.api.table.entity.PrivateMsg;

import java.util.List;

/**
 * @Author: dd
 */
public interface PrivateMsgMapper extends BaseMapper<PrivateMsg> {

    List<PrivateMsgResponseDTO> privateMsg(ChatRequestDTO requestDTO);
}

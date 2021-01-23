package travel.api.chat.service.impl;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.api.chat.service.ChatService;
import travel.api.config.response.WorkException;
import travel.api.config.response.WorkStatus;
import travel.api.dto.request.chat.ChatRequestDTO;
import travel.api.dto.request.user.UserRequestDTO;
import travel.api.dto.response.chat.FriendResponseDTO;
import travel.api.dto.response.chat.PrivateMsgResponseDTO;
import travel.api.table.mapper.PrivateMsgMapper;
import travel.api.table.mapper.UserUserRelationMapper;

import java.util.List;


/**
 * @Author: dd
 */
@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private UserUserRelationMapper userUserRelationMapper;

    @Autowired
    private PrivateMsgMapper privateMsgMapper;


    @Override
    public JSONObject friendList(UserRequestDTO userRequestDTO) {
        if (null == userRequestDTO.getUserId()) {
            throw new WorkException(WorkStatus.CHECK_PARAM);
        }
        List<FriendResponseDTO> friendList = userUserRelationMapper.friendList(userRequestDTO.getUserId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("friends", friendList);
        return jsonObject;
    }

    @Override
    public JSONObject privateMsg(ChatRequestDTO requestDTO) {
        if (null == requestDTO.getFromUser() || null == requestDTO.getToUser()) {
            throw new WorkException(WorkStatus.CHECK_PARAM);
        }
        List<PrivateMsgResponseDTO> list = privateMsgMapper.privateMsg(requestDTO);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", list);
        return jsonObject;
    }
}

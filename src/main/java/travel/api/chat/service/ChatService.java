package travel.api.chat.service;

import net.sf.json.JSONObject;
import travel.api.dto.request.chat.ChatRequestDTO;
import travel.api.dto.request.user.UserRequestDTO;

/**
 * @author Administrator
 */
public interface ChatService {

    public JSONObject friendList(UserRequestDTO userRequestDTO);

    JSONObject privateMsg(ChatRequestDTO requestDTO);
}

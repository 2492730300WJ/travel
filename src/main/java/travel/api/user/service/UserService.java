package travel.api.user.service;

import travel.api.dto.request.user.UserRequestDTO;
import net.sf.json.JSONObject;

/**
 * @author Administrator
 */
public interface UserService {

    public JSONObject login(UserRequestDTO userRequestDTO);

    public JSONObject info(UserRequestDTO userRequestDTO);
}

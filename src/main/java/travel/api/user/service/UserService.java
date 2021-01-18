package travel.api.user.service;

import travel.api.user.entity.User;
import net.sf.json.JSONObject;

/**
 * @author Administrator
 */
public interface UserService {

    public JSONObject login(User user);

    public JSONObject info(User user);
}

package travel.api.app.service;

import net.sf.json.JSONObject;
import travel.api.dto.request.user.AppUpdateRequestDTO;

/**
 * @Author: dd
 */
public interface AppService {
    JSONObject appUpdate(AppUpdateRequestDTO requestDTO);
}

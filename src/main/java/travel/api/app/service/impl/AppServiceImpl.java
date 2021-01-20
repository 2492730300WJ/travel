package travel.api.app.service.impl;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.api.app.service.AppService;
import travel.api.dto.request.user.AppUpdateRequestDTO;
import travel.api.dto.response.app.AppUpdateResponseDTO;
import travel.api.table.mapper.AppVersionMapper;

import java.util.List;

/**
 * @Author: dd
 */
@Service
public class AppServiceImpl implements AppService {

    @Autowired
    AppVersionMapper appVersionMapper;

    @Override
    public JSONObject appUpdate(AppUpdateRequestDTO requestDTO) {
        String version = requestDTO.getVersion().replace(".", "");
        List<AppUpdateResponseDTO> list = appVersionMapper.checkVersion(version);
        AppUpdateResponseDTO responseDTO = new AppUpdateResponseDTO();
        responseDTO.setStatus("0");
        if (null != list && list.size() == 1) {
            responseDTO.setStatus("1");
            responseDTO.setWgtUrl(list.get(0).getWgtUrl());
            responseDTO.setVersionName(list.get(0).getVersionName());
            responseDTO.setTips(list.get(0).getTips());
        }
        if (null != list && list.size() > 1) {
            responseDTO.setStatus("2");
            responseDTO.setPkgUrl(list.get(0).getPkgUrl());
            responseDTO.setVersionName(list.get(0).getVersionName());
            responseDTO.setTips(list.get(0).getTips());
        }
        JSONObject result = new JSONObject();
        result.put("versionStatus",responseDTO);
        return result;
    }
}

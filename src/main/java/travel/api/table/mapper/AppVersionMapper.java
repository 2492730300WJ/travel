package travel.api.table.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import travel.api.dto.response.app.AppUpdateResponseDTO;
import travel.api.table.entity.AppVersion;

import java.util.List;

/**
 * @Author: dd
 */
public interface AppVersionMapper extends BaseMapper<AppVersion> {
    List<AppUpdateResponseDTO> checkVersion(String version);
}

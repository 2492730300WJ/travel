package travel.api.table.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * @author dd
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AppVersion implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "app_version_id", type = IdType.AUTO)
    private Long appVersionId;

    private String version;

    private String wgtUrl;

    private String pkgUrl;

    private String versionName;

    private String tips;

}

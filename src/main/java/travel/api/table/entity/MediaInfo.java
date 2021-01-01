package travel.api.table.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author wj
 * @since 2020-10-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MediaInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "media_id", type = IdType.AUTO)
    private Integer mediaId;

    private Long userId;

    private String title;

    private String cover;

    private String url;

    private String description;

    private Integer loveNum;

    private Integer commNum;

    private Integer redoNum;

    private Integer status;

    private String createTime;

    private String updateTime;
}

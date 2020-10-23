package travel.api.mybatisplus.table.entity;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.time.LocalDateTime;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 
    * </p>
*
* @author wj
* @since 2020-10-23
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class VideoInfo implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "video_id", type = IdType.AUTO)
    private Integer videoId;

    private String cover;

    private String url;

    private String description;

    private String time;

    private Integer status;


}

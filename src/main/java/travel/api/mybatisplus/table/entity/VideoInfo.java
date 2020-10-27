package travel.api.mybatisplus.table.entity;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
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
* @since 2020-10-27
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class VideoInfo implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "video_id", type = IdType.AUTO)
    private Integer videoId;

    private String author;

    private String cover;

    private String url;

    private String description;

    private String time;

    private Integer loveNum;

    private Integer commNum;

    private Integer redoNum;

    private Integer status;


}

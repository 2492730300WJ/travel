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
* @since 2020-07-27
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class SysUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

            /**
            * 用户账号
            */
    private String userCard;

            /**
            * 用户密码
            */
    private String userPassword;

            /**
            * 用户名
            */
    private String userName;

            /**
            * 状态（0.正常1.封禁2.注销）
            */
    private Integer status;


}

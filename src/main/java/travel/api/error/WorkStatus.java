package travel.api.error;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: dd
 */
@Getter
public enum WorkStatus {
    SUCCESS(200, "请求成功"),
    FAIL(1,"系统出现异常"),
    PASSWORD_IS_ERROR(2,"用户名或密码错误"),
    THIS_ROLE_DO_NOT_LOGIN(3,"该角色无法登录此系统"),
    LOGIN_TIME_OUT(4,"登录已过期，请重新登录"),
    PLEASE_LOGIN(5,"请先登录您的账号")
    ;

    /**
     * 标准响应状态码
     */
    private Integer workCode;

    /**
     * 响应状态描述
     */
    private String workMsg;

    WorkStatus(int workCode, String workMsg) {
        this.workCode = workCode;
        this.workMsg = workMsg;
    }


}

package travel.api.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.sf.json.JSONObject;

/**
 * @Author: dd
 */
@Getter
@Setter
@NoArgsConstructor
public class WorkResponse {
    /**
     * 异常Code
     */
    private Integer exceptionCode;

    /**
     * 异常信息
     */
    private String exceptionMsg;

    /**
     * JSONObject
     */
    private JSONObject result;
    /**
     * enum
     */
    @JsonIgnore
    private WorkStatus workStatus;

    public WorkResponse(WorkStatus workStatus,JSONObject result){
        this.result = result;
        this.workStatus = workStatus;
        if(workStatus ==null){
            workStatus = WorkStatus.SUCCESS;
        }
        this.exceptionCode = workStatus.getWorkCode();
        this.exceptionMsg = workStatus.getWorkMsg();
    }

    public WorkResponse(WorkStatus workStatus){
        this.workStatus = workStatus;
        if(workStatus ==null){
            workStatus = WorkStatus.SUCCESS;
        }
        this.exceptionCode = workStatus.getWorkCode();
        this.exceptionMsg = workStatus.getWorkMsg();
    }

}

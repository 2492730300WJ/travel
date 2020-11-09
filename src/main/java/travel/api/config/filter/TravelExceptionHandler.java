package travel.api.config.filter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import travel.api.config.response.WorkException;
import travel.api.config.response.WorkResponse;
import travel.api.config.response.WorkStatus;

/**
 * 异常统一处理类
 * @author Administrator
 */
@ControllerAdvice
public class TravelExceptionHandler {

    /**
     * WorkException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = WorkException.class)
    @ResponseBody
    public WorkResponse bizExceptionHandle(WorkException e) {
        e.printStackTrace();
        WorkStatus workStatus = e.getWorkStatus();
        WorkResponse response = new WorkResponse(workStatus);
        return response;
    }


    /**
     * 所有异常处理处理类
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public WorkResponse ExceptionHandle(Exception e) {
        e.printStackTrace();
        WorkStatus workStatus = WorkStatus.FAIL;
        WorkResponse response = new WorkResponse(workStatus);
        return response;
    }


}

package travel.api.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import travel.api.app.service.AppService;
import travel.api.config.response.CommonReturnController;
import travel.api.config.response.WorkResponse;
import travel.api.config.response.WorkStatus;
import travel.api.dto.request.user.AppUpdateRequestDTO;
import travel.api.dto.request.user.UserRequestDTO;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: dd
 */
@RestController
@RequestMapping("api/app")
public class AppController extends CommonReturnController {

    @Autowired
    private AppService appService;

    /**
     * 校验app更新
     */
    @PostMapping("/update")
    public void appUpdate(HttpServletResponse response, @RequestBody AppUpdateRequestDTO requestDTO) {
        this.commonResponse(response, new WorkResponse(WorkStatus.SUCCESS, appService.appUpdate(requestDTO)));
    }
}

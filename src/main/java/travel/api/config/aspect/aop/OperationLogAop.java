package travel.api.config.aspect.aop;

import travel.api.config.aspect.annotation.OperationLog;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Aspect
@Component
public class OperationLogAop implements Ordered {

    @Autowired
    private TransactionTemplate txTemplate;
    @Autowired
    private HttpServletRequest request;


    @AfterThrowing(pointcut = "@annotation(operationLog)")
    public void doAfterThrowing(OperationLog operationLog) {
//        SessionUser user = SessionUser.getInstance();
//        try {
//            boolean flag = filterAdmin(user);
//            if(!flag){
//                BusOperationLogEntity entity = new BusOperationLogEntity();
//                entity.setBusinessNo(user.getStoreId());
//                entity.setUserId(user.getUserId());
//                if(operationLog.operationMenu().equals(StoreBackEnum.STORE_BACK_EMPLOYEE_LIST+","+ StoreAppEnum.STORE_APP_EMPLOYEE_MANAGER+",")||operationLog.operationMenu().equals(StoreBackEnum.STORE_BACK_RELATION_LIST+","+ StoreAppEnum.STORE_APP_RELATION_MANAGER +",")
//                        || operationLog.operationMenu().equals(StoreBackEnum.STORE_BACK_HOME+","+""+","+ UserAppEnum.USER_APP_HOME)
//                ){
//                    String[] list = operationLog.operationMenu().split(",");
//                    int index = Integer.parseInt(request.getSession().getAttribute("opStatus").toString()) - 1;
//                    entity.setOperationMenu(list[index]);
//                }else {
//                    entity.setOperationMenu(operationLog.operationMenu());
//                }
//                if(operationLog.operationType().equals(StoreAppEnum.STORE_BACK_OP_RELATION_IS_OUT+","+StoreAppEnum.STORE_BACK_OP_RELATION_IS_OUT_CANCEL)){
//                    String[] list = operationLog.operationType().split(",");
//                    int index = Integer.parseInt(request.getSession().getAttribute("freeAndOut").toString());
//                    entity.setOperationType(list[index]);
//                }else if(operationLog.operationType().equals(StoreAppEnum.STORE_BACK_OP_RELATION_IS_FREE+","+StoreAppEnum.STORE_BACK_OP_RELATION_IS_FREE_CANCEL)){
//                    String[] list = operationLog.operationType().split(",");
//                    int index = Integer.parseInt(request.getSession().getAttribute("freeAndOut").toString()) - 1 ;
//                    entity.setOperationType(list[index]);
//                }else if(operationLog.operationType().equals(StoreAppEnum.STORE_APP_OP_UPDATE_TICKET_STATUS)){
//                    String[] list ={StoreAppEnum.STORE_APP_OP_REFUND_TICKET,StoreAppEnum.STORE_APP_OP_AGREE_WITHDRAWAL_FROM_PLATFORM,StoreAppEnum.STORE_APP_OP_REFUSE_WITHDRAWAL_FROM_PLATFORM
//                            ,StoreAppEnum.STORE_APP_OP_AGREE_CHANGE,StoreAppEnum.STORE_APP_OP_REFUSE_CHANGE};
//                    int index = Integer.parseInt(request.getSession().getAttribute("opStatus").toString()) - 1;
//                    entity.setOperationType(list[index]);
//                }else {
//                    entity.setOperationType(operationLog.operationType());
//                }
//                entity.setOperationResult(e.getMessage());
//                entity.setOperationStatus(BusOperationLogDao.FAIL);
//                entity.setIpAddress(IPAddressUtil.request(request));
//                entity.setGmtCreate(TimeUtil.getTime14());
//                entity.setGmtModified(TimeUtil.getTime14());
//                entity.setStatus(Const.STATUS_NORMAL);
//                //保存数据库
//                if(StoreBackEnum.STORE_BACK_AND_STORE_APP.equals(operationLog.operationType()) && Integer.parseInt(request.getSession().getAttribute("opStatus").toString())  == 2 ){
//                    return;
//                }
//                busOperationLogDao.save(entity);
//            }
//        } catch (InterfaceException e1) {
//            throw new InterfaceException(e1.getStatus());
//        } catch (Exception e2){
//            e2.printStackTrace();
//        }
    }

    @AfterReturning(value = "@annotation(operationLog)")
    public void doBefore(OperationLog operationLog) {
//        SessionUser user = SessionUser.getInstance();
//        boolean flag = filterAdmin(user);
//        if(!flag) {
//            BusOperationLogEntity entity = new BusOperationLogEntity();
//            entity.setBusinessNo(user.getStoreId());
//            entity.setUserId(user.getUserId());
//            if (operationLog.operationMenu().equals(StoreBackEnum.STORE_BACK_EMPLOYEE_LIST + "," + StoreAppEnum.STORE_APP_EMPLOYEE_MANAGER + ",") || operationLog.operationMenu().equals(StoreBackEnum.STORE_BACK_RELATION_LIST + "," + StoreAppEnum.STORE_APP_RELATION_MANAGER + ",")
//                    || operationLog.operationMenu().equals(StoreBackEnum.STORE_BACK_HOME + "," + "" + "," + UserAppEnum.USER_APP_HOME)
//            ) {
//                String[] list = operationLog.operationMenu().split(",");
//                int index = Integer.parseInt(request.getSession().getAttribute("opStatus").toString()) - 1;
//                entity.setOperationMenu(list[index]);
//            } else {
//                entity.setOperationMenu(operationLog.operationMenu());
//            }
//            if (operationLog.operationType().equals(StoreAppEnum.STORE_BACK_OP_RELATION_IS_OUT + "," + StoreAppEnum.STORE_BACK_OP_RELATION_IS_OUT_CANCEL)) {
//                String[] list = operationLog.operationType().split(",");
//                int index = Integer.parseInt(request.getSession().getAttribute("freeAndOut").toString());
//                entity.setOperationType(list[index]);
//            } else if (operationLog.operationType().equals(StoreAppEnum.STORE_BACK_OP_RELATION_IS_FREE + "," + StoreAppEnum.STORE_BACK_OP_RELATION_IS_FREE_CANCEL)) {
//                String[] list = operationLog.operationType().split(",");
//                int index = Integer.parseInt(request.getSession().getAttribute("freeAndOut").toString()) - 1;
//                entity.setOperationType(list[index]);
//            } else if (operationLog.operationType().equals(StoreAppEnum.STORE_APP_OP_UPDATE_TICKET_STATUS)) {
//                String[] list = {StoreAppEnum.STORE_APP_OP_REFUND_TICKET, StoreAppEnum.STORE_APP_OP_AGREE_WITHDRAWAL_FROM_PLATFORM, StoreAppEnum.STORE_APP_OP_REFUSE_WITHDRAWAL_FROM_PLATFORM
//                        , StoreAppEnum.STORE_APP_OP_AGREE_CHANGE, StoreAppEnum.STORE_APP_OP_REFUSE_CHANGE};
//                int index = Integer.parseInt(request.getSession().getAttribute("opStatus").toString()) - 1;
//                entity.setOperationType(list[index]);
//            } else {
//                entity.setOperationType(operationLog.operationType());
//            }
//            entity.setOperationResult(operationLog.operationResult());
//            entity.setOperationStatus(BusOperationLogDao.SUCCESS);
//            entity.setIpAddress(IPAddressUtil.request(request));
//            entity.setGmtCreate(TimeUtil.getTime14());
//            entity.setGmtModified(TimeUtil.getTime14());
//            entity.setStatus(Const.STATUS_NORMAL);
//            if (StoreBackEnum.STORE_BACK_AND_STORE_APP.equals(operationLog.operationType()) && Integer.parseInt(request.getSession().getAttribute("opStatus").toString()) == 2) {
//                return;
//            }
//            busOperationLogDao.save(entity);
//        }
    }


    /**
     * 获取当前网络ip
     *
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        //"***.***.***.***".length() = 15
        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    @Override
    public int getOrder() {
        return 20;
    }

}

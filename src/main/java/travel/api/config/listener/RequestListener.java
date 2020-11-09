package travel.api.config.listener;


import org.slf4j.MDC;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import java.util.UUID;

/**
 * @author Administrator
 */
@WebListener
public class RequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent arg0) {
        MDC.put("operatingId", String.valueOf(UUID.randomUUID()));
    }

    @Override
    public void requestDestroyed(ServletRequestEvent arg0) {
        MDC.clear();
    }
}
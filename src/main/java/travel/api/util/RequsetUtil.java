package travel.api.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Book;
import java.util.Enumeration;
import java.util.Map;

@Slf4j
@Component
public class RequsetUtil {

    public static <T> T request2Bean(HttpServletRequest request, Class<T> clazz) {
        try {
            T bean = clazz.newInstance();
            Enumeration e = request.getParameterNames();
            while (e.hasMoreElements()) {
                String name = (String) e.nextElement();
                String value = request.getParameter(name);
                BeanUtils.setProperty(bean, name, value);
            }
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

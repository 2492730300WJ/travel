package travel.api.config.aspect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog {
    /** 操作模块(菜单) */
    String operationMenu();
    /** 操作类型 */
    String operationType();
    /** 操作结果 */
    String operationResult();
}

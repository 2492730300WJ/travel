package travel.api;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.annotation.PostConstruct;
import java.util.TimeZone;


/**
 * @author Administrator
 */
@SpringBootApplication
@ServletComponentScan(basePackages = {"travel.api.config"})
@MapperScan(basePackages = {"travel.api.table.mapper"})
@NacosPropertySource(dataId = "example", autoRefreshed = true)
@EnableNacosDiscovery(globalProperties = @NacosProperties(serverAddr = "47.102.121.70:8848"))
public class ApiApplication{

    @PostConstruct
    void setDefaultTimezone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    @NacosInjected
    private NamingService namingService;

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(ApiApplication.class, args);
    }

    @PostConstruct
    public void registerInstance() throws NacosException {
        namingService.registerInstance("api", "47.102.121.70", 9999);
    }

}

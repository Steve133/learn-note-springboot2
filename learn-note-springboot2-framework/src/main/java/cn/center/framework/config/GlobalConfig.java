package cn.center.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

/**
 * @ClassName:  GlobalConfig   
 * @Description: 应用信息配置   
 * @author: chenjinsong
 * @date:   2019年11月15日 下午3:07:56      
 * @Copyright:
 */
@Data
@ToString
@Component
@ConfigurationProperties(prefix = "demo")
public class GlobalConfig {

    private String id;
    private String version;
    private String releaseDate;
}

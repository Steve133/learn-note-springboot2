package cn.center.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName:  GlobalConfig   
 * @Description: 应用信息配置   
 * @author: chenjinsong
 * @date:   2019年11月15日 下午3:07:56      
 * @Copyright:
 */
@Component
@ConfigurationProperties(prefix = "demo")
public class GlobalConfig {

    private String id;
    private String version;
    private String releaseDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}

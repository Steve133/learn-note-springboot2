package cn.center.framework.controller;

import com.alibaba.fastjson.JSON;
import cn.center.framework.config.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author song
 * @title 获取应用版本信息的Controlller
 * @projectName demo
 * @description TODO
 * @date 2019年11月15日 下午3:42:52
 */
@RestController
public class VersionController {

    @Autowired
    GlobalConfig config;

    @GetMapping("/version")
    public String version() {
        return JSON.toJSONString(config);
    }
}

package cn.center.consume.service;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.center.api.DubboService;
import cn.center.api.UserEntity;

import org.springframework.stereotype.Service;

@Service
public class ConsumeService implements DubboService {

    @Reference
    private DubboService dubboService ;

    @Override
    public String getInfo(String param) {
        return dubboService.getInfo(param);
    }

    @Override
    public UserEntity getUserInfo(UserEntity userEntity) {
        return dubboService.getUserInfo(userEntity);
    }

    @Override
    public String timeOut(Integer time) {
        return dubboService.timeOut(time);
    }
}

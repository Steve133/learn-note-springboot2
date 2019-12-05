package cn.center.api;

public interface DubboService {
    String getInfo (String param) ;

    UserEntity getUserInfo (UserEntity userEntity) ;

    String timeOut (Integer time) ;
}

package cn.center.framework.enums;

/**
 * @author song
 * @title 用户操作日志-操作类型枚举类
 * @projectName demo
 * @description TODO
 * @date 2019年11月15日 下午3:42:43
 */
public enum OperateType {

    // 登录
    LOGIN(0),
    // 查询
    QUERY(1),
    // 新增
    ADD(2),
    // 修改
    EDIT(3),
    // 删除
    DELETE(4),
    // 注销
    LOGOUT(5),
    // 下线
    OFFLINE(6),
    // 创建
    CREATE(7),
    // 上传
    UPLOAD(8),
    // 下载
    DOWNLOAD(9),
    // 其它
    OTHER(10);

    private int value;

    private OperateType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

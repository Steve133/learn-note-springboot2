package cn.center.common.utils;

import java.io.Serializable;

public class RestResult implements Serializable{
	private static final long serialVersionUID = 1L;

	// 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public static RestResult build(Integer status, String msg, Object data) {
        return new RestResult(status, msg, data);
    }

    public static RestResult ok(Object data) {
        return new RestResult(data);
    }

    public static RestResult ok() {
        return new RestResult(null);
    }

    public RestResult() {

    }

    public static RestResult build(Integer status, String msg) {
        return new RestResult(status, msg, null);
    }

    public RestResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public RestResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

	public boolean isOK() {
		return this.status == 200;
	}

}

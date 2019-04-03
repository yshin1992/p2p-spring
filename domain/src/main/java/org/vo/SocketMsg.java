package org.vo;

import java.net.Socket;

/**
 * WebSocket的消息传输用
 */
public class SocketMsg {

    //登录
    public static final Integer DATA_TYPE_LOGIN = 1;

    //站内信息
    public static final Integer DATA_TYPE_MAIL = 2;

    //产品发布
    public static final Integer DATA_TYPE_PRODUCT = 3;

    /**
     * 消息类型
     */
    private Integer dataType;

    /**
     * 消息实体
     */
    private String msg = "";

    /**
     * 附加信息
     */
    private String extra = "";

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SocketMsg(){

    }

    public SocketMsg(Integer dataType,String msg){
        this.dataType = dataType;
        this.msg = msg;
    }

    public SocketMsg(Integer dataType,String msg,String extra){
        this(dataType,msg);
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "SocketMsg{" +
                "dataType=" + dataType +
                ", msg='" + msg + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}

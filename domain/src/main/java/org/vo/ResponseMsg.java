package org.vo;

public class ResponseMsg<T> {

	public static final Integer SUCCESS_CODE=200;
	public static final Integer FAILURE_CODE=500;
	
	private T data;
	
	private String msg;
	
	private Integer code = 200;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	public void failure(String msg){
		this.msg = msg;
		this.code = FAILURE_CODE;
	}
	
	public void success(String msg){
		this.msg = msg;
		this.code = SUCCESS_CODE;
	}
	
	public void failure(String msg,T data){
		this.msg = msg;
		this.code = FAILURE_CODE;
		this.data = data;
	}
	
	public void success(String msg,T data){
		this.msg = msg;
		this.code = SUCCESS_CODE;
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseMsg [data=" + data + ", msg=" + msg + ", code=" + code
				+ "]";
	}
	
	
}

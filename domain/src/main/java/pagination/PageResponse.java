package pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息响应工具类
 * @author yshin1992
 *
 * @param <T>
 */
public class PageResponse<T> {

	private List<T> data = new ArrayList<T>();
	
	private String msg = "";
	
	private Long count = 0L;
	
	private Integer code = 0;

	private final PageRequest request;
	
	public PageResponse(PageRequest request){
		this.request =request;
	}
	
	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	public Integer getPage(){
		return this.request.getPage();
	}
	
	public Integer getFirstResultNo(){
		return this.request.getFirstResultNo();
	}
	
	public Integer getLimit(){
		return this.request.getLimit();
	}
}

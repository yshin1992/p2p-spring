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

	private List<T> resultList= new ArrayList<T>();
	
	private Long totalCount = 0L;
	
	private final PageRequest pageRequest;
	
	public PageResponse(PageRequest pageRequest){
		this.pageRequest = pageRequest;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
	public Long getTotalPage(){
		return this.totalCount%this.pageRequest.getPageSize() == 0 ? this.totalCount/this.pageRequest.getPageSize()
				: (this.totalCount/this.pageRequest.getPageSize()+1);
	}
	
	public Integer getPageSize(){
		return this.pageRequest.getPageSize();
	}
	
	public Integer getCurrentPage(){
		return this.pageRequest.getCurrentPage();
	}
	
}

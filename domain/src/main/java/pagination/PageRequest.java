package pagination;

/**
 * 分页请求的工具类
 * @author yshin1992
 *
 */
public class PageRequest {

	
	public static final Integer CURRENT_PAGE = 1;
	
	public static final Integer DEFAULT_PAGESIZE = 10;
	
	private Integer currentPage = CURRENT_PAGE;
	
	private Integer pageSize = DEFAULT_PAGESIZE;
	
	/**
	 * 根据某个域进行排序
	 */
	private String orderField;

	public PageRequest(){
	}
	
	public PageRequest(Integer currentPage,Integer pageSize){
		this.currentPage= currentPage;
		this.pageSize = pageSize;
	}
	
	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage == null || currentPage < 0? CURRENT_PAGE : currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize == null || pageSize < 0 ? DEFAULT_PAGESIZE : pageSize;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	
	/**
	 * 返回第一个结果的序号
	 * @return
	 */
	public Integer getFirstResultNo(){
		return (this.currentPage -1) * this.pageSize;
	}
	
	
}

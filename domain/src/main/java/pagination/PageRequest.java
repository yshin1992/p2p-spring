package pagination;

/**
 * 分页请求的工具类
 * @author yshin1992
 *
 */
public class PageRequest {

	
	public static final Integer CURRENT_PAGE = 1;
	
	public static final Integer DEFAULT_PAGESIZE = 10;
	
	private Integer page = CURRENT_PAGE;
	
	private Integer limit = DEFAULT_PAGESIZE;
	
	/**
	 * 根据某个域进行排序
	 */
	private String orderField;

	public PageRequest(){
	}
	
	public PageRequest(Integer page,Integer limit){
		this.page= page;
		this.limit = limit;
	}
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page == null || page < 0? CURRENT_PAGE : page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit == null || limit < 0 ? DEFAULT_PAGESIZE : limit;
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
		return (this.page -1) * this.limit;
	}

	@Override
	public String toString() {
		return "PageRequest [page=" + page + ", limit=" + limit
				+ ", orderField=" + orderField + "]";
	}
	
}

package cn.smbms.util;


public class Page {
	@Override
	public String toString() {
		return "Page [count=" + count + ", pageSize=" + pageSize
				+ ", indexPage=" + indexPage + ", limit=" + limit
				+ ", pageCount=" + pageCount + "]";
	}
	private int count;//总数量
	private int pageSize;//每页显示数据量
	private int indexPage;//当前页面
	private int limit;//数据偏移量
	private int pageCount;//总页数
	
	public int getPageCount(){
		if(count%pageSize==0)
			pageCount=count/pageSize;
		else 
			pageCount=count/pageSize+1;
		return this.pageCount;
	}
	public int getLimit() {
		this.limit=(this.indexPage-1)*this.pageSize;
		if(limit<0)
			limit=0;
		return limit;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getIndexPage() {
		return indexPage;
	}
	public void setIndexPage(int indexPage) {
		if(indexPage<=0)
			this.indexPage = 1;
		else
			this.indexPage = indexPage;
	}

	
}

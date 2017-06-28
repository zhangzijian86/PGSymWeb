package com.sym.bean;

public class Pg_news {
	private String NewsID;//新闻ID
	private String NewsCode;//新闻编码
	private String title;//新闻标题
	private String Content;//新闻内容
	private String Remark;//新闻备注
	private String Status;//新闻状态(0,有效，-1,删除)
	private String CreatedBy;//创建人
	private String CreatedDate;//创建时间
	private String ModifiedBy;//修改人
	private String ModifiedDate;//修改时间
	public String getNewsID() {
		return NewsID;
	}
	public void setNewsID(String newsID) {
		NewsID = newsID;
	}
	public String getNewsCode() {
		return NewsCode;
	}
	public void setNewsCode(String newsCode) {
		NewsCode = newsCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getCreatedBy() {
		return CreatedBy;
	}
	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}
	public String getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(String createdDate) {
		CreatedDate = createdDate;
	}
	public String getModifiedBy() {
		return ModifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		ModifiedBy = modifiedBy;
	}
	public String getModifiedDate() {
		return ModifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		ModifiedDate = modifiedDate;
	}
}

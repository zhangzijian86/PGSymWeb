package com.sym.bean;

public class Pg_homeinfo {
	private String InfoID;//ID
	private String Status;//-1,删除 0,有效',
	private String ComponyName;//公司名称
	private String VsrsionName;//版本名称	
	private String Vsrsionnumber;//版本号
	private String Image;//图片
	private String UpdateLink;//下载链接
	private String CreatedBy;//创建人
	private String CreatedDate;//创建时间
	private String ModifiedBy;//修改人
	private String ModifiedDate;//修改时间
	public String getInfoID() {
		return InfoID;
	}
	public void setInfoID(String infoID) {
		InfoID = infoID;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getComponyName() {
		return ComponyName;
	}
	public void setComponyName(String componyName) {
		ComponyName = componyName;
	}
	public String getVsrsionName() {
		return VsrsionName;
	}
	public void setVsrsionName(String vsrsionName) {
		VsrsionName = vsrsionName;
	}
	public String getVsrsionnumber() {
		return Vsrsionnumber;
	}
	public void setVsrsionnumber(String vsrsionnumber) {
		Vsrsionnumber = vsrsionnumber;
	}
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	public String getUpdateLink() {
		return UpdateLink;
	}
	public void setUpdateLink(String updateLink) {
		UpdateLink = updateLink;
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

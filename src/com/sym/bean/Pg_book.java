package com.sym.bean;

public class Pg_book {
	
	private String BOOK_ID;
	private String BOOK_Code;
	private String USER_ID;
	private String STORE_ID;
	private String BOOK_Date;
	private String BOOK_Status;
	private String BOOK_Remarks;
	
	public String getBOOK_ID() {
		return BOOK_ID;
	}
	public void setBOOK_ID(String bOOK_ID) {
		BOOK_ID = bOOK_ID;
	}
	public String getBOOK_Code() {
		return BOOK_Code;
	}
	public void setBOOK_Code(String bOOK_Code) {
		BOOK_Code = bOOK_Code;
	}
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getSTORE_ID() {
		return STORE_ID;
	}
	public void setSTORE_ID(String sTORE_ID) {
		STORE_ID = sTORE_ID;
	}
	public String getBOOK_Date() {
		return BOOK_Date;
	}
	public void setBOOK_Date(String bOOK_Date) {
		BOOK_Date = bOOK_Date;
	}
	public String getBOOK_Status() {
		return BOOK_Status;
	}
	public void setBOOK_Status(String bOOK_Status) {
		BOOK_Status = bOOK_Status;
	}
	public String getBOOK_Remarks() {
		return BOOK_Remarks;
	}
	public void setBOOK_Remarks(String bOOK_Remarks) {
		BOOK_Remarks = bOOK_Remarks;
	}	
}

package com.sym.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.sym.bean.Pg_homeinfo;
import com.sym.bean.Pg_store;
import com.sym.bean.Pg_user;
import com.sym.db.GetConn;

public class DaoImpl 
{
	static final String COMPANYNAME = "sym"; 
	
	public boolean check(String user_mobile) 
	{
		boolean b = false;
		GetConn getConn=new GetConn();
		ResultSet rs = null;
		Connection conn=getConn.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement(
					" select * from PG_USER where USER_Mobile = ? and USER_Status !=-1;");
			ps.setString(1,user_mobile);
			rs=ps.executeQuery();
			if (rs.next())
			{
				b=true;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getConn.closeconn(conn);
		return b;
	}
	
	public Pg_user register(String user_wxid,String user_mobile,
			String user_name,String store_id)
	{
		int i = 0;
		GetConn getConn=new GetConn();		
		Pg_user user = null;
		Connection conn=getConn.getConnection();
		UUID uuid = UUID.randomUUID();  
        String struuid = uuid.toString(); 
		try {
			PreparedStatement ps=conn.prepareStatement(
					"insert into PG_USER"+
					"(USER_ID,USER_WXID,USER_Code,USER_Name,"+ 
					"USER_ISDN,USER_Mobile,USER_RegisterDate,"+ 
					"USER_Status,USER_VipLevel,USER_Blance,"+ 
					"STORE_ID,USER_Spend,USER_Score)"+
					"value (?,?,'',?,'',"+
					"?,now(),'0','0','0','0',"
					+ "'0',?)");
			ps.setString(1,struuid);
			ps.setString(2,user_wxid);
			ps.setString(3,user_name);
			ps.setString(4,user_mobile);	
			ps.setString(5,store_id);	
			i=ps.executeUpdate();	
			if (i>0)
			{	
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				user = new Pg_user();	
				user.setUSER_ID(struuid);
				user.setUSER_Code("");
				user.setUSER_Name(user_name);
				user.setUSER_ISDN("");
				user.setUSER_Mobile(user_mobile);
				user.setUSER_RegisterDate(df.format(new Date()).toString());
				user.setUSER_Status("0");
				user.setUSER_VipLevel("0");
				user.setUSER_Blance("0");
				user.setSTORE_ID(store_id);
				user.setUSER_Spend("0");
				user.setUSER_Score("0");
				user.setUSER_WXID(user_wxid);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getConn.closeconn(conn);	
		return user;
	}
	
	public Pg_user login(String user_wxid) 
	{
		GetConn getConn=new GetConn();
		ResultSet rs = null;
		Connection conn=getConn.getConnection();
		Pg_user user = null;
		try {
			PreparedStatement ps=conn.prepareStatement(
					"select USER_ID,USER_Code,USER_Name,"+ 
					"USER_ISDN,USER_Mobile,USER_RegisterDate,"+
					"USER_Status,USER_VipLevel,USER_Blance,STORE_ID,"+ 
					"USER_Spend,USER_Score,USER_WXID "+ 
					"from PG_USER where USER_WXID = ? and USER_Status!=-1");
			ps.setString(1,user_wxid);
			rs=ps.executeQuery();
			if (rs.next())
			{
				user = new Pg_user();	
				user.setUSER_ID(rs.getString("USER_ID"));
				user.setUSER_Code(rs.getString("USER_Code"));
				user.setUSER_Name(rs.getString("USER_Name"));
				user.setUSER_ISDN(rs.getString("USER_ISDN"));
				user.setUSER_Mobile(rs.getString("USER_Mobile"));
				user.setUSER_RegisterDate(rs.getString("USER_RegisterDate"));
				user.setUSER_Status(rs.getString("USER_Status"));
				user.setUSER_VipLevel(rs.getString("USER_VipLevel"));
				user.setUSER_Blance(rs.getString("USER_Blance"));
				user.setSTORE_ID(rs.getString("STORE_ID"));
				user.setUSER_Spend(rs.getString("USER_Spend"));
				user.setUSER_Score(rs.getString("USER_Score"));
				user.setUSER_WXID(rs.getString("USER_WXID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public Pg_store getmystore(String store_id) 
	{
		GetConn getConn=new GetConn();
		ResultSet rs = null;
		Connection conn=getConn.getConnection();
		Pg_store store = null;
		try {
			PreparedStatement ps=conn.prepareStatement(
					"select STORE_ID,STORE_Code,STORE_Name,"+ 
					"STORE_Phone,STORE_Position,"+
					"STORE_OpeningDate,STORE_Status,STORE_BookNumber "+ 
					"from PG_STORE where STORE_ID = ? and STORE_Status!=-1");
			ps.setString(1,store_id);
			rs=ps.executeQuery();
			if (rs.next())
			{
				store = new Pg_store();	
				store.setSTORE_ID(rs.getString("STORE_ID"));
				store.setSTORE_Code(rs.getString("STORE_Code"));
				store.setSTORE_Name(rs.getString("STORE_Name"));
				store.setSTORE_Phone(rs.getString("STORE_Phone"));
				store.setSTORE_Position(rs.getString("STORE_Position"));
				store.setSTORE_OpeningDate(rs.getString("STORE_OpeningDate"));
				store.setSTORE_Status(rs.getString("STORE_Status"));
				store.setSTORE_BookNumber(rs.getString("STORE_BookNumber"));		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return store;
	}
	
	public List<Pg_store> getLimitsstore(String user_id) 
	{
		List<Pg_store> list=new ArrayList<Pg_store>();
		GetConn getConn=new GetConn();
		ResultSet rs = null;
		Connection conn=getConn.getConnection();		
		try {
			PreparedStatement ps=conn.prepareStatement(
					"select STORE_ID,STORE_Code,STORE_Name,"+ 
					"STORE_Phone,STORE_Position,"+
					"STORE_OpeningDate,STORE_Status,STORE_BookNumber "+ 
					"from PG_STORE where STORE_ID in"+ 
					"(select STORE_ID from PG_Book_Limits where USER_ID = ?)"+
					" and STORE_Status!=-1");
			ps.setString(1,user_id);
			System.out.println("==getLimitsstore=="+ps.toString());
			rs=ps.executeQuery();
			while (rs.next())
			{
				Pg_store store = new Pg_store();
				store.setSTORE_ID(rs.getString("STORE_ID"));
				store.setSTORE_Code(rs.getString("STORE_Code"));
				store.setSTORE_Name(rs.getString("STORE_Name"));
				store.setSTORE_Phone(rs.getString("STORE_Phone"));
				store.setSTORE_Position(rs.getString("STORE_Position"));
				store.setSTORE_OpeningDate(rs.getString("STORE_OpeningDate"));
				store.setSTORE_Status(rs.getString("STORE_Status"));
				store.setSTORE_BookNumber(rs.getString("STORE_BookNumber"));	
				list.add(store);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}

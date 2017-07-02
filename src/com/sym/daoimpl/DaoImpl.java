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

import com.sym.bean.Pg_book;
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
	
	public Pg_user register(String user_password,String user_mobile,
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
					"USER_Score,USER_Spend,STORE_ID,USER_Password,USER_HonorScore)"+
					"value (?,'','',?,'',"+
					"?,now(),'0','0','0','100',"
					+ "'0',?,?,'100')");
			ps.setString(1,struuid);			
			ps.setString(2,user_name);
			ps.setString(3,user_mobile);	
			ps.setString(4,store_id);	
			ps.setString(5,user_password);
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
				user.setUSER_Score("100");
				user.setUSER_WXID("");
				user.setUSER_Password(user_password);
				user.setUSER_HonorScore("100");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getConn.closeconn(conn);	
		return user;
	}
	
	public Pg_user login(String user_mobile,String user_password) 
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
					"USER_Spend,USER_Score,USER_WXID,USER_Password,USER_HonorScore "+ 
					"from PG_USER where USER_Mobile = ? and USER_Password=? and USER_Status!=-1");
			ps.setString(1,user_mobile);
			ps.setString(2,user_password);
			System.out.println("==login=="+ps.toString());
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
				user.setUSER_Password(rs.getString("USER_Password"));
				user.setUSER_HonorScore(rs.getString("USER_HonorScore"));				
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
	
	public List<Pg_store> getlimitsstore(String user_id) 
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
			System.out.println("==getlimitsstore=="+ps.toString());
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
	
	public List<Pg_store> getareastore(String area_id) 
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
					"from PG_STORE where STORE_Code like ? "+
					" and STORE_Status!=-1");
			ps.setString(1,"%"+area_id+"%");
			System.out.println("==getareastore=="+ps.toString());
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
	
	public int sign(String user_id) 
	{
		int b = 0;
		int i = 0;
		GetConn getConn=new GetConn();
		ResultSet rs = null;
		Connection conn=getConn.getConnection();		
		try {
			PreparedStatement ps=conn.prepareStatement(
					"select Sign_ID from PG_Sign where Sign_Status!=-1 "+
					"and date_format(Sign_Date,'%Y-%m-%d') = date_format(now(),'%Y-%m-%d') "+
					"and USER_ID = ?");
			ps.setString(1,user_id);
			System.out.println("==getareastore=="+ps.toString());
			rs=ps.executeQuery();
			if (rs.next())
			{
				b = 1;
				return b;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		UUID uuid = UUID.randomUUID();  
        String struuid = uuid.toString(); 
		try {
			PreparedStatement ps=conn.prepareStatement(""+ 
					"insert into PG_Sign (Sign_ID,Sign_Code,USER_ID,Sign_Status,Sign_Date) "+
					"value (?,'',?,'0',now());");
			ps.setString(1,struuid);	
			ps.setString(2,user_id);
			i=ps.executeUpdate();	
			if (i>0)
			{		
				b = 2;
			}
			else
			{
				b = 0;
				return b;	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		try {
			PreparedStatement ps=conn.prepareStatement( 
					"update PG_USER set USER_Score = USER_Score +10 where USER_ID = ?");	
			ps.setString(1,user_id);
			i=ps.executeUpdate();	
			if (i>0)
			{		
				b = 2;
				return b;	
			}
			else
			{
				b = 0;
				return b;	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getConn.closeconn(conn);
		return b;
	}
	
	public int book(String user_id,String store_id,String book_time) 
	{
		int b = 0;
		int i = 0;
		GetConn getConn=new GetConn();
		ResultSet rs = null;
		Connection conn=getConn.getConnection();		
		try {
			PreparedStatement ps=conn.prepareStatement(
					"select count(BOOK_ID) as dayhas from PG_Book "+ 
					"where USER_ID = ? and STORE_ID = ? and "+
					"date_format(BOOK_Date,'%Y-%m-%d') = date_format(?,'%Y-%m-%d') "+ 
					"and BOOK_Status=0");
			ps.setString(1,user_id);
			ps.setString(2,store_id);
			ps.setString(3,book_time.substring(0, 10));
			System.out.println("==book=0="+ps.toString());
			rs=ps.executeQuery();
			if (rs.next())
			{
				int dayhas = rs.getInt("dayhas");
				if(dayhas==3){
					b=1;
					return b;
				}				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		try {
			PreparedStatement ps=conn.prepareStatement(
					"select count(BOOK_ID) as daycancelhas from PG_Book "+ 
					"where USER_ID = ? and STORE_ID = ? and "+
					"date_format(BOOK_Date,'%Y-%m-%d') = date_format(?,'%Y-%m-%d') "+ 
					"and BOOK_Status=1");
			ps.setString(1,user_id);
			ps.setString(2,store_id);
			ps.setString(3,book_time.substring(0, 10));
			System.out.println("==book=1="+ps.toString());
			rs=ps.executeQuery();
			if (rs.next())
			{
				int daycancelhas = rs.getInt("daycancelhas");
				if(daycancelhas==3){
					b=2;
					return b;
				}				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			PreparedStatement ps=conn.prepareStatement(
					"select count(BOOK_ID) as hascount from PG_Book "+ 
					"where STORE_ID = ? and "+
					"BOOK_Date = ?"+ 
					"and BOOK_Status=0");
			ps.setString(1,store_id);
			ps.setString(2,book_time);
			System.out.println("==book=2="+ps.toString());
			rs=ps.executeQuery();
			if (rs.next())
			{
				int hascount = rs.getInt("hascount");
				if(hascount>=4){
					b=3;
					return b;
				}				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		UUID uuid = UUID.randomUUID();  
        String struuid = uuid.toString(); 
		try {
			PreparedStatement ps=conn.prepareStatement(""+ 
					"insert into PG_Book (BOOK_ID,BOOK_Code,USER_ID,STORE_ID,"+ 
					"BOOK_Date,BOOK_Status,BOOK_Remarks) "+
					"value (?,'',?,?,?,'0','');");
			ps.setString(1,struuid);	
			ps.setString(2,user_id);
			ps.setString(3,store_id);
			ps.setString(4,book_time);
			System.out.println("==book=3="+ps.toString());
			i=ps.executeUpdate();	
			if (i>0)
			{		
				b = 0;
			}
			else
			{
				b = 4;
				return b;	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		getConn.closeconn(conn);
		return b;
	}
	
	public int cancelbook(String book_id) 
	{
		int b = 0;
		int i = 0;
		GetConn getConn=new GetConn();
		Connection conn=getConn.getConnection();	
		try {
			PreparedStatement ps=conn.prepareStatement(""+ 
					"update PG_Book set BOOK_Status = 1 where BOOK_ID = ?");
			ps.setString(1,book_id);			
			System.out.println("==book=3="+ps.toString());
			i=ps.executeUpdate();	
			if (i>0)
			{		
				b = 0;
			}
			else
			{
				b = 1;
				return b;	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		getConn.closeconn(conn);
		return b;
	}
	
	public List<Pg_book> getmybooks(String user_id,String store_id) 
	{
		List<Pg_book> list=new ArrayList<Pg_book>();
		GetConn getConn=new GetConn();
		ResultSet rs = null;
		Connection conn=getConn.getConnection();		
		try {
			PreparedStatement ps=conn.prepareStatement(
					"select BOOK_ID,BOOK_Code,USER_ID,STORE_ID,"+ 
					"BOOK_Date,BOOK_Status,BOOK_Remarks from PG_Book where "+
					"USER_ID = ? and STORE_ID = ? and BOOK_Status=0");
			ps.setString(1,user_id);
			ps.setString(2,store_id);
			System.out.println("==getmybooks=="+ps.toString());
			rs=ps.executeQuery();
			while (rs.next())
			{
				Pg_book book = new Pg_book();
				book.setBOOK_ID(rs.getString("BOOK_ID"));
				book.setBOOK_Code(rs.getString("BOOK_Code"));
				book.setUSER_ID(rs.getString("USER_ID"));
				book.setSTORE_ID(rs.getString("STORE_ID"));
				book.setBOOK_Date(rs.getString("BOOK_Date"));
				book.setBOOK_Status(rs.getString("BOOK_Status"));
				book.setBOOK_Remarks(rs.getString("BOOK_Remarks"));			
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}

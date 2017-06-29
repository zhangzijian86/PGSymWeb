package com.sym.daoimpl;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.sym.bean.Pg_homeinfo;
import com.sym.bean.Pg_news;
import com.sym.bean.Pg_order;
import com.sym.bean.Pg_user;
import com.sym.db.GetConn;



public class DaoImpl 
{
	static final String COMPANYNAME = "yq"; 
	public Pg_user login(String UserCode,String Password) 
	{
		GetConn getConn=new GetConn();
		ResultSet rs = null;
		Connection conn=getConn.getConnection();
		Pg_user user = null;
		try {
			PreparedStatement ps=conn.prepareStatement(
					"select UserID,UserCode,UserName,Password,PasswordKey,PasswordIV,UserClass,Gender,"+
					"ISDN,Telephone,Mobile,Fax,Email,Address,Photo,Age,Birthday,Remark,Status,"+
					"CreatedBy,CreatedDate,ModifiedBy,ModifiedDate "+
					"from pg_user where UserCode=? and Password=? and Status=1");
			ps.setString(1,UserCode);
			ps.setString(2,Password);
			rs=ps.executeQuery();
			if (rs.next())
			{
				user = new Pg_user();
				System.out.println("==UserID=="+rs.getString("UserID"));
				System.out.println("==UserCode=="+rs.getString("UserCode"));
				System.out.println("==UserName=="+rs.getString("UserName"));
//				user.setUserID(rs.getString("UserID"));
//				user.setUserCode(rs.getString("UserCode"));
//				user.setUserName(rs.getString("UserName"));
//				user.setPassword(rs.getString("Password"));
//				user.setPasswordKey(rs.getString("PasswordKey"));
//				user.setPasswordIV(rs.getString("PasswordIV"));
//				user.setUserClass(rs.getString("UserClass"));
//				user.setGender(rs.getString("Gender"));
//				user.setISDN(rs.getString("ISDN"));
//				user.setTelephone(rs.getString("Telephone"));
//				user.setMobile(rs.getString("Mobile"));
//				user.setFax(rs.getString("Fax"));
//				user.setEmail(rs.getString("Email"));
//				user.setAddress(rs.getString("Address"));
//				user.setPhoto(rs.getString("Photo"));
//				user.setAge(rs.getString("Age"));
//				user.setBirthday(rs.getString("Birthday"));
//				user.setRemark(rs.getString("Remark"));
//				user.setStatus(rs.getString("Status"));
//				user.setCreatedBy(rs.getString("CreatedBy"));
//				user.setCreatedDate(rs.getString("CreatedDate"));
//				user.setModifiedBy(rs.getString("ModifiedBy"));
//				user.setModifiedDate(rs.getString("ModifiedDate"));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	//查询所总条数   
    public int getCount(String name,String Condition){   
    	String sql="select count(*) as pageCount from "+name+" "+Condition;   
    	System.out.println(sql);
    	int i=-1;   
    	GetConn getConn=new GetConn();
    	ResultSet rs = null;
    	Connection conn=getConn.getConnection();
    	try {
    		PreparedStatement ps=conn.prepareStatement(sql);
    		rs=ps.executeQuery();
    		if(rs!=null){    					
    			rs.next();  
    			i=rs.getInt("pageCount");  
    		}
    	} catch (SQLException e) {   
    		e.printStackTrace();   
    	}   
    	return i;   
    } 
    
    public List<Pg_user> GetAllUsers(String currentPage,String eachPage) 
	{
		int rows;
		GetConn getConn=new GetConn();
		ResultSet rs = null;
		Connection conn=getConn.getConnection();
		List<Pg_user> list=new ArrayList<Pg_user>();
		Pg_user puser = null;
		try {
			PreparedStatement ps=conn.prepareStatement("select UserID,UserCode,UserName,Telephone,Mobile,Email"
					+ " from pg_user where status !=-1 order by ModifiedDate desc  limit ?, ?");
			int intcurrentPage = Integer.parseInt(currentPage);
			int inteachPage = Integer.parseInt(eachPage);
			if(currentPage.equals("0")){
				ps.setInt(1, 0);
			}else{
				ps.setInt(1, (intcurrentPage-1)*inteachPage);
			}
			ps.setInt(2, inteachPage);
			rs=ps.executeQuery();
			if(rs!=null){    		
	    		rs.last();
	    		rows = rs.getRow();
	    		rs.beforeFirst();
	    		for(int i=0;i<rows;i++)
		    	{	    			
		    		rs.next();
		    		puser = new Pg_user();
//		    		puser.setUserID(rs.getString("UserID"));
//		    		puser.setUserCode(rs.getString("UserCode"));
//		    		puser.setUserName(rs.getString("UserName"));
//		    		puser.setTelephone(rs.getString("Telephone"));
//		    		puser.setMobile(rs.getString("Mobile"));
//		    		puser.setEmail(rs.getString("Email"));
		    		list.add(puser);
		    	}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
    
    public int SubmittedMaterial(String OrderCode,String Remark,String CreatedBy){
    	GetConn getConn=new GetConn();
		int i = 0;
		Connection conn=getConn.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement(""
					+ "insert into pg_order "
					+ "("
					+ "OrderID,OrderCode,Status,Remark,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate"
					+ ")values(?,?,'0',?,?,now(),?,now());"
					);		
			System.out.println("===UUID="+UUID.randomUUID().toString());
			ps.setString(1, UUID.randomUUID().toString());	
			ps.setString(2,OrderCode);
			ps.setString(3,Remark);			
			ps.setString(4,CreatedBy);	
			ps.setString(5,CreatedBy);
			System.out.println("=SubmittedMaterial=sql="+ps.toString());
			i=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getConn.closeconn(conn);
    	return i;
    }
    
    public int LaunchOrder(String OrderCode,String CreatedBy){
    	GetConn getConn=new GetConn();
		int i = 0;
		Connection conn=getConn.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement("update pg_order "
													+ "set Status = 2, "		
													+ "ModifiedBy = ?, "
													+ "ModifiedDate = now() "								
													+ "where OrderCode = ? "
													);
			
			ps.setString(1,CreatedBy);	
			ps.setString(2,OrderCode);				
			System.out.println("=updateOneUser=sql="+ps.toString());
			i=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getConn.closeconn(conn);
    	return i;
    }
    
    public List<Pg_order> GetAllOrder(String Model,String Type,String Condition,String CreatedBy,String CurrentPage,String EachPage) 
	{
    	int rows;
		GetConn getConn=new GetConn();
		ResultSet rs = null;
		Connection conn=getConn.getConnection();
		List<Pg_order> list=new ArrayList<Pg_order>();
		Pg_order porder = null;
		try {
			PreparedStatement ps = null;
			if(Model.equals("1")){
				if(Type.equals("0")){
					ps=conn.prepareStatement(
							"select OrderID,OrderCode,Status,Remark,FlowRemark,Price,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate"
							+ " from pg_order where Status!=-1 and Status <= 2 "+Condition+" "
							+ " and CreatedBy = '"+CreatedBy+"' "
						    + " order by Status,ModifiedDate desc  limit ?, ?");
				}else if(Type.equals("1")){
					ps=conn.prepareStatement(
							"select OrderID,OrderCode,Status,Remark,FlowRemark,Price,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate"
							+ " from pg_order where Status > 2 and  Status < 100 "+Condition+"  "
							+ "  and CreatedBy = '"+CreatedBy+"' "
							+ " order by Status,ModifiedDate desc  limit ?, ?");
				}else if(Type.equals("2")){
					ps=conn.prepareStatement(
							"select OrderID,OrderCode,Status,Remark,FlowRemark,Price,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate"
							+ " from pg_order where Status = 100 "+Condition+"  "
							+ " and CreatedBy = '"+CreatedBy+"' "
							+ " order by Status,ModifiedDate desc  limit ?, ?");
				}				
				int intcurrentPage = Integer.parseInt(CurrentPage);
				int inteachPage = Integer.parseInt(EachPage);
				if(CurrentPage.equals("0")){
					ps.setInt(1, 0);
				}else{
					ps.setInt(1, (intcurrentPage-1)*inteachPage);
				}
				ps.setInt(2, inteachPage);
			}else{
				if(Type.equals("0")){
					ps=conn.prepareStatement(
							"select OrderID,OrderCode,Status,Remark,FlowRemark,Price,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate"
							+ " from pg_order where Status!=-1 and Status <= 2 and (OrderCode like '%"+Condition+"%'    "
							+ " or Remark like '%"+Condition+"%' )"
							+ " and CreatedBy = '"+CreatedBy+"' "							
							+ " order by Status,ModifiedDate desc");
				}else if(Type.equals("1")){
					ps=conn.prepareStatement(
							"select OrderID,OrderCode,Status,Remark,FlowRemark,Price,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate"
							+ " from pg_order where Status > 2 and  Status < 100 and (OrderCode like '%"+Condition+"%'  "
							+ " or Remark like '%"+Condition+"%' )"
							+ " and CreatedBy = '"+CreatedBy+"' "
							+ " order by Status,ModifiedDate desc");
				}else if(Type.equals("2")){
					ps=conn.prepareStatement(
							"select OrderID,OrderCode,Status,Remark,FlowRemark,Price,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate"
							+ " from pg_order where Status = 100 and (OrderCode like '%"+Condition+"%'   "
							+ " or Remark like '%"+Condition+"%' )"
						    + " and CreatedBy = '"+CreatedBy+"' "
						    + " order by Status,ModifiedDate desc");
				}	
			}
			System.out.println("=GetAllOrder=sql="+ps.toString());
			rs=ps.executeQuery();
			if(rs!=null){    		
	    		rs.last();
	    		rows = rs.getRow();
	    		rs.beforeFirst();
	    		for(int i=0;i<rows;i++)
		    	{	    			
		    		rs.next();
		    		porder = new Pg_order();
		    		porder.setOrderID(rs.getString("OrderID"));
		    		porder.setOrderCode(rs.getString("OrderCode"));
		    		porder.setStatus(rs.getString("Status"));
		    		porder.setRemark(rs.getString("Remark"));
		    		porder.setFlowRemark(rs.getString("FlowRemark"));
		    		porder.setPrice(rs.getString("Price"));
		    		porder.setCreatedBy(rs.getString("CreatedBy"));
		    		porder.setCreatedDate(rs.getString("CreatedDate"));
		    		porder.setModifiedBy(rs.getString("ModifiedBy"));
		    		porder.setModifiedDate(rs.getString("ModifiedDate"));
		    		list.add(porder);
		    	}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
    
    public List<Pg_user> GetOneUser(String UserCode) 
   	{
   		int rows;
   		GetConn getConn=new GetConn();
   		ResultSet rs = null;
   		Connection conn=getConn.getConnection();
   		List<Pg_user> list=new ArrayList<Pg_user>();
   		Pg_user puser = null;
   		try {
   			PreparedStatement ps=conn.prepareStatement("select UserID,UserCode,UserClass,UserName,Password,Gender,Address,ISDN,Telephone,Mobile,Email"
   					+ " from pg_user where UserCode ='"+UserCode+"'");
   			rs=ps.executeQuery();
   			if(rs!=null){    		
   	    		rs.last();
   	    		rows = rs.getRow();
   	    		rs.beforeFirst();
   	    		for(int i=0;i<rows;i++)
   		    	{	    			
   		    		rs.next();
   		    		puser = new Pg_user();
//   		    		puser.setUserID(rs.getString("UserID"));
//   		    		puser.setUserCode(rs.getString("UserCode"));
//   		    		puser.setUserName(rs.getString("UserName"));
//   		    		puser.setTelephone(rs.getString("Telephone"));
//   		    		puser.setMobile(rs.getString("Mobile"));
//   		    		puser.setEmail(rs.getString("Email"));
//   		    		puser.setAddress(rs.getString("Address"));
//   		    		puser.setISDN(rs.getString("ISDN"));
//   		    		puser.setPassword(rs.getString("Password"));
//   		    		puser.setUserClass(rs.getString("UserClass"));
   		    		list.add(puser);
   		    	}
   			}
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}
   		return list;
   	}
    
    public int UpdateUser(Pg_user puser){
    	GetConn getConn=new GetConn();
		int i = 0;
		Connection conn=getConn.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement("update pg_user "
						 + "set UserCode = ?,"
		        	     +" UserName = ?,"
		        	     +" Password = ?,"
		        	     +" Gender = ?,"
		        	     +" Address = ?,"
		        	     +" ISDN = ?,"
		        	     +" Telephone = ?,"
		        	     +" Mobile = ?,"
		        	     +" Email = ?,"
		        	     +" UserClass = ? "
		        	     + "where UserCode = ?"
		        	     );
//			ps.setString(1,puser.getUserCode());	
//			ps.setString(2,puser.getUserName());
//			ps.setString(3,puser.getPassword());
//			ps.setString(4,puser.getGender());
//			ps.setString(5,puser.getAddress());
//			ps.setString(6,puser.getISDN());
//			ps.setString(7,puser.getTelephone());
//			ps.setString(8,puser.getMobile());
//			ps.setString(9,puser.getEmail());
//			ps.setString(10,puser.getUserClass());			
//			ps.setString(11,puser.getUserCode());
			System.out.println("=UpdateUser=sql="+ps.toString());
			i=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getConn.closeconn(conn);
    	return i;
    }
    
    public List<Pg_user> GetUserCode() 
   	{
   		int rows;
   		GetConn getConn=new GetConn();
   		ResultSet rs = null;
   		Connection conn=getConn.getConnection();
   		List<Pg_user> list=new ArrayList<Pg_user>();
   		Pg_user puser = null;
   		try {
   			PreparedStatement ps=conn.prepareStatement("select max(REPLACE(UserCode,'"+COMPANYNAME+"','')+0) as Number  from pg_user order by UserCode desc;");
   			rs=ps.executeQuery();
   			if(rs!=null){    		
   	    		rs.last();
   	    		rows = rs.getRow();
   	    		rs.beforeFirst();
   	    		for(int i=0;i<rows;i++)
   		    	{	    			
   		    		rs.next();
   		    		puser = new Pg_user();   		    		
//   		    		puser.setUserCode(COMPANYNAME+(rs.getInt("Number")+1));   
//   		    		Random random = new Random();
//   		    		int x = random.nextInt(899999);
//   		    		x = x+100000;
//   		    		puser.setPassword(x+"");
//   		    		System.out.println(puser.getPassword()+"==="+puser.getUserCode());
   		    		list.add(puser);
   		    	}
   			}
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}
   		return list;
   	}
    
    public int AddUser(Pg_user puser){
//    	puser.setCreatedBy(COMPANYNAME);
//    	puser.setModifiedBy(COMPANYNAME);
//    	puser.setUserID(UUID.randomUUID().toString());
    	GetConn getConn=new GetConn();
		int i = 0;
		Connection conn=getConn.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement("insert into pg_user "
					     + "(UserID,UserCode,UserClass,UserName,Password,Gender,Address,ISDN,Telephone,Mobile,Email,Status,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate)"
					     + "value("
						 + " ?,?,?,?,?,?,?,?,?,?,?,1,?,now(),?,now()"
		        	     + ")"
		        	     );
//			ps.setString(1,puser.getUserID());
//			ps.setString(2,puser.getUserCode());	
//			ps.setString(3,puser.getUserClass());
//			ps.setString(4,puser.getUserName());
//			ps.setString(5,puser.getPassword());
//			ps.setString(6,puser.getGender());
//			ps.setString(7,puser.getAddress());
//			ps.setString(8,puser.getISDN());
//			ps.setString(9,puser.getTelephone());
//			ps.setString(10,puser.getMobile());
//			ps.setString(11,puser.getEmail());
//			ps.setString(12,puser.getCreatedBy());
//			ps.setString(13,puser.getModifiedBy());
			
			System.out.println("=AddUser=sql="+ps.toString());
			i=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getConn.closeconn(conn);
    	return i;
    }
    
    public List<Pg_order> GetAllOrders(String UserTmp,String OrderTmp,String currentPage,String eachPage) 
   	{
   		int rows;
   		GetConn getConn=new GetConn();
   		ResultSet rs = null;
   		Connection conn=getConn.getConnection();
   		List<Pg_order> list=new ArrayList<Pg_order>();
   		Pg_order porder = null;
   		String strTmp = "";
   		if(UserTmp!=null&&!UserTmp.trim().equals("")){
   			strTmp = "and ( pg_order.CreatedBy = '"+UserTmp+"' or pg_user.username ='"+UserTmp+"')";
   		}
   		if(OrderTmp!=null&&!OrderTmp.trim().equals("")){
   			strTmp = strTmp + " and (pg_order.Remark like '%"+OrderTmp+"%' or OrderCode like '%"+OrderTmp+"%')";
   		}
   		try {
   			PreparedStatement ps=conn.prepareStatement(
   					"select OrderID,OrderCode,OperationRemark,pg_order.Status,pg_order.Remark,FlowRemark,"
   					+ "Price,pg_order.CreatedBy,pg_order.CreatedDate,pg_order.ModifiedBy,"
   					+ "pg_order.ModifiedDate,pg_user.username "
   					+ "from pg_order "
   					+ "left join pg_user on pg_order.CreatedBy = pg_user.usercode "
   					+ "where  pg_order.Status!=-1 "+ strTmp  					
   					+ "order by pg_order.Status ,pg_order.ModifiedDate desc limit ?, ?");
   			int intcurrentPage = Integer.parseInt(currentPage);
   			int inteachPage = Integer.parseInt(eachPage);
   			if(currentPage.equals("0")){
   				ps.setInt(1, 0);
   			}else{
   				ps.setInt(1, (intcurrentPage-1)*inteachPage);
   			}
   			ps.setInt(2, inteachPage);
   			System.out.println("=GetAllOrders=sql="+ps.toString());
   			rs=ps.executeQuery();
   			if(rs!=null){    		
   	    		rs.last();
   	    		rows = rs.getRow();
   	    		rs.beforeFirst();
   	    		for(int i=0;i<rows;i++)
   		    	{	    			
   		    		rs.next();
   		    		porder = new Pg_order();
   		    		porder.setOrderID(rs.getString("OrderID"));
   		    		porder.setOrderCode(rs.getString("OrderCode"));
   		    		porder.setStatus(rs.getString("Status"));
   		    		porder.setRemark(rs.getString("Remark"));
   		    		porder.setFlowRemark(rs.getString("FlowRemark"));
   		    		porder.setPrice(rs.getString("Price"));
		    		porder.setCreatedBy(rs.getString("CreatedBy"));
		    		porder.setCreatedDate(rs.getString("CreatedDate"));
		    		porder.setModifiedBy(rs.getString("ModifiedBy"));
		    		porder.setModifiedDate(rs.getString("ModifiedDate"));
		    		porder.setUserName(rs.getString("UserName"));
		    		porder.setOperationRemark(rs.getString("OperationRemark"));
   		    		list.add(porder);
   		    	}
   			}
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}
   		return list;
   	}
    
    public int DeleteOrder(Pg_order puser){
    	GetConn getConn=new GetConn();
		int i = 0;
		Connection conn=getConn.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement("update pg_order "
						 + "set Status = -1 "
		        	     + "where OrderCode = ?"
		        	     );
			ps.setString(1,puser.getOrderCode());	
			System.out.println("=DeleteOrder=sql="+ps.toString());
			i=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getConn.closeconn(conn);
    	return i;
    }
    
    public int DoNextOrder(Pg_order porder){
    	GetConn getConn=new GetConn();
		int i = 0;
		Connection conn=getConn.getConnection();		
		String CreatedBy = porder.getCreatedBy();
		String strTmp = "";
		if(porder.getStatus().equals("0")){
			strTmp = "反馈价格:"+CreatedBy+"<br>";
		}else if(porder.getStatus().equals("2")){
			strTmp = "接受订单:"+CreatedBy+"<br>";
		}else if(porder.getStatus().equals("3")){
			strTmp = "开始制版:"+CreatedBy+"<br>";
		}else if(porder.getStatus().equals("4")){
			strTmp = "开始印刷:"+CreatedBy+"<br>";
		}else if(porder.getStatus().equals("5")){
			strTmp = "开始复合:"+CreatedBy+"<br>";
		}else if(porder.getStatus().equals("6")){
			strTmp = "开始出货:"+CreatedBy+"<br>";
		}else if(porder.getStatus().equals("7")){
			strTmp = "完成订单:"+CreatedBy+"<br>";
		}
		
		System.out.println("==CreatedBy=="+CreatedBy);
		System.out.println("==strTmp=="+strTmp);
		try {
			int status = Integer.parseInt(porder.getStatus());
			if(status!=7){
				status = status+1;
			}else{
				status = 100;
			}
			PreparedStatement ps=conn.prepareStatement("update pg_order "
						 + "set Status = ?, "
						 + "Price = ? , "
						 + "FlowRemark = ? ,"
						 + "OperationRemark = case when OperationRemark is null or OperationRemark='' or OperationRemark = 'NULL' then '"+strTmp+"' "
						 + "else concat(OperationRemark,'"+strTmp+"') end " 
		        	     + "where OrderCode = ? "
		        	     );
			ps.setString(1,status+"");
			ps.setString(2,porder.getPrice());
			ps.setString(3,porder.getFlowRemark());
			ps.setString(4,porder.getOrderCode());	
			System.out.println("=DoNextOrder=sql="+ps.toString());
			i=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getConn.closeconn(conn);
    	return i;
    }
    
    public int DeleteOrderPhone(Pg_order puser){
    	GetConn getConn=new GetConn();
		int i = 0;
		Connection conn=getConn.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement("update pg_order "
						 + "set Status = -1 "
		        	     + "where OrderCode = ?"
		        	     );
			ps.setString(1,puser.getOrderCode());	
			System.out.println("=DeleteOrder=sql="+ps.toString());
			i=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getConn.closeconn(conn);
    	return i;
    }
    
    public List<Pg_homeinfo> GetHomeInfo() 
   	{
   		int rows;
   		GetConn getConn=new GetConn();
   		ResultSet rs = null;
   		Connection conn=getConn.getConnection();
   		List<Pg_homeinfo> list=new ArrayList<Pg_homeinfo>();
   		Pg_homeinfo phomeinfo = null;
   		try {
   			PreparedStatement ps=conn.prepareStatement("select InfoID,Status,ComponyName,VsrsionName,Vsrsionnumber,Image,"
   					+ "UpdateLink,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate from pg_Info where Status!=-1");
   			rs=ps.executeQuery();
   			if(rs!=null){    		
   	    		rs.last();
   	    		rows = rs.getRow();
   	    		rs.beforeFirst();
   	    		for(int i=0;i<rows;i++)
   		    	{	    			
   		    		rs.next();
   		    		phomeinfo = new Pg_homeinfo();
   		    		phomeinfo.setInfoID(rs.getString("InfoID"));
   		    		phomeinfo.setStatus(rs.getString("Status"));
   		    		phomeinfo.setComponyName(rs.getString("ComponyName"));
   		    		phomeinfo.setVsrsionName(rs.getString("VsrsionName"));
   		    		phomeinfo.setVsrsionnumber(rs.getString("Vsrsionnumber"));
   		    		phomeinfo.setImage(rs.getString("Image"));
   		    		phomeinfo.setUpdateLink(rs.getString("UpdateLink"));
   		    		phomeinfo.setCreatedBy(rs.getString("CreatedBy"));
   		    		phomeinfo.setCreatedDate(rs.getString("CreatedDate"));
   		    		phomeinfo.setModifiedBy(rs.getString("ModifiedBy"));
   		    		phomeinfo.setModifiedDate(rs.getString("ModifiedDate"));
   		    		list.add(phomeinfo);
   		    	}
   			}
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}
   		return list;
   	}
    
    public int DeleteUser(Pg_user puser){
    	GetConn getConn=new GetConn();
		int i = 0;
		Connection conn=getConn.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement("update pg_user "
						 + "set Status = -1 "
		        	     + "where UserCode = ?"
		        	     );
//			ps.setString(1,puser.getUserCode());	
			System.out.println("=DeleteOrder=sql="+ps.toString());
			i=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getConn.closeconn(conn);
    	return i;
    }
    
    public List<Pg_news> GetNewsPhone(String NewsCode) 
   	{
   		int rows;
   		GetConn getConn=new GetConn();
   		ResultSet rs = null;
   		Connection conn=getConn.getConnection();
   		List<Pg_news> list=new ArrayList<Pg_news>();
   		Pg_news pnews = null;   	
   		String strTmp = "";
   		System.out.println("=GetNewsPhone=NewsCode="+NewsCode);
   		if(NewsCode!=null&&!NewsCode.trim().equals("")){
   			strTmp = "and NewsCode='"+NewsCode+"'";
   		}
   		try {
   			PreparedStatement ps=conn.prepareStatement(
   					"select NewsID,NewsCode,title,Content,Remark,Status,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate "
   					+ "from pg_news where Status !='-1' "+strTmp + " order by CreatedDate desc"
   					);
   			System.out.println("=GetNewsPhone=sql="+ps.toString());
   			rs=ps.executeQuery();
   			if(rs!=null){    		
   	    		rs.last();
   	    		rows = rs.getRow();
   	    		rs.beforeFirst();
   	    		for(int i=0;i<rows;i++)
   		    	{	    			
   		    		rs.next();
   		    		pnews = new Pg_news();
   		    		pnews.setNewsID(rs.getString("NewsID"));
   		    		pnews.setNewsCode(rs.getString("NewsCode"));
   		    		pnews.setTitle(rs.getString("Title"));
   		    		pnews.setContent(rs.getString("Content"));
   		    		pnews.setStatus(rs.getString("Status"));
   		    		pnews.setRemark(rs.getString("Remark"));   		    		
   		    		pnews.setCreatedBy(rs.getString("CreatedBy"));
   		    		pnews.setCreatedDate(rs.getString("CreatedDate"));
   		    		pnews.setModifiedBy(rs.getString("ModifiedBy"));
   		    		pnews.setModifiedDate(rs.getString("ModifiedDate"));
   		    		list.add(pnews);
   		    	}
   			}
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}
   		return list;
   	}
    
    public List<Pg_news> GetNews(String NewsCode,String currentPage,String eachPage) 
   	{
   		int rows;
   		GetConn getConn=new GetConn();
   		ResultSet rs = null;
   		Connection conn=getConn.getConnection();
   		List<Pg_news> list=new ArrayList<Pg_news>();
   		Pg_news pnews = null;   	
   		String strTmp = "";
   		System.out.println("=GetNewsPhone=NewsCode="+NewsCode);
   		if(NewsCode!=null&&!NewsCode.trim().equals("")){
   			strTmp = "and NewsCode='"+NewsCode+"'";
   		}
   		try {
   			PreparedStatement ps=conn.prepareStatement(
   					"select NewsID,NewsCode,title,Content,Remark,Status,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate "
   					+ "from pg_news where Status !='-1' "+strTmp + " order by CreatedDate desc  limit ?, ?"
   					);   			
   			int intcurrentPage = Integer.parseInt(currentPage);
			int inteachPage = Integer.parseInt(eachPage);
   			if(currentPage.equals("0")){
				ps.setInt(1, 0);
			}else{
				ps.setInt(1, (intcurrentPage-1)*inteachPage);
			}
			ps.setInt(2, inteachPage);
			System.out.println("=GetNews=sql="+ps.toString());
   			rs=ps.executeQuery();
   			if(rs!=null){    		
   	    		rs.last();
   	    		rows = rs.getRow();
   	    		rs.beforeFirst();
   	    		for(int i=0;i<rows;i++)
   		    	{	    			
   		    		rs.next();
   		    		pnews = new Pg_news();
   		    		pnews.setNewsID(rs.getString("NewsID"));
   		    		pnews.setNewsCode(rs.getString("NewsCode"));
   		    		pnews.setTitle(rs.getString("title"));
   		    		pnews.setContent(rs.getString("Content"));
   		    		pnews.setStatus(rs.getString("Status"));
   		    		pnews.setRemark(rs.getString("Remark"));   		    		
   		    		pnews.setCreatedBy(rs.getString("CreatedBy"));
   		    		pnews.setCreatedDate(rs.getString("CreatedDate"));
   		    		pnews.setModifiedBy(rs.getString("ModifiedBy"));
   		    		pnews.setModifiedDate(rs.getString("ModifiedDate"));
   		    		list.add(pnews);
   		    	}
   			}
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}
   		return list;
   	}
    
    public int DeleteNews(Pg_news pnews){
    	GetConn getConn=new GetConn();
		int i = 0;
		Connection conn=getConn.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement("update pg_news "
						 + "set Status = -1 "
		        	     + "where NewsCode = ?"
		        	     );
			ps.setString(1,pnews.getNewsCode());	
			System.out.println("=DeleteNews=sql="+ps.toString());
			i=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getConn.closeconn(conn);
    	return i;
    }
    
    public List<Pg_news> GetOneNews(String NewsCode) 
   	{
   		int rows;
   		GetConn getConn=new GetConn();
   		ResultSet rs = null;
   		Connection conn=getConn.getConnection();
   		List<Pg_news> list=new ArrayList<Pg_news>();
   		Pg_news pnews = null;   	
   		String strTmp = "";
   		System.out.println("=GetOneNews=NewsCode="+NewsCode);
   		if(NewsCode!=null&&!NewsCode.trim().equals("")){
   			strTmp = "and NewsCode='"+NewsCode+"'";
   		}
   		try {
   			PreparedStatement ps=conn.prepareStatement(
   					"select NewsID,NewsCode,title,Content,Remark,Status,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate "
   					+ "from pg_news where Status !='-1' "+strTmp + " order by CreatedDate desc  "
   					);   			
			System.out.println("=GetOneNews=sql="+ps.toString());
   			rs=ps.executeQuery();
   			if(rs!=null){    		
   	    		rs.last();
   	    		rows = rs.getRow();
   	    		rs.beforeFirst();
   	    		for(int i=0;i<rows;i++)
   		    	{	    			
   		    		rs.next();
   		    		pnews = new Pg_news();
   		    		pnews.setNewsID(rs.getString("NewsID"));
   		    		pnews.setNewsCode(rs.getString("NewsCode"));
   		    		pnews.setTitle(rs.getString("title"));
   		    		pnews.setContent(rs.getString("Content"));
   		    		pnews.setStatus(rs.getString("Status"));
   		    		pnews.setRemark(rs.getString("Remark"));   		    		
   		    		pnews.setCreatedBy(rs.getString("CreatedBy"));
   		    		pnews.setCreatedDate(rs.getString("CreatedDate"));
   		    		pnews.setModifiedBy(rs.getString("ModifiedBy"));
   		    		pnews.setModifiedDate(rs.getString("ModifiedDate"));
   		    		list.add(pnews);
   		    	}
   			}
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}
   		return list;
   	}
    
    public int UpdateNews(Pg_news pnews){
    	GetConn getConn=new GetConn();
		int i = 0;
		Connection conn=getConn.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement("update pg_news "
						 + "set NewsCode = ?,"
		        	     +" title = ?,"
		        	     +" Content = ? "
		        	     + "where NewsCode = ?"
		        	     );
			ps.setString(1,pnews.getNewsCode());	
			ps.setString(2,pnews.getTitle());
			ps.setString(3,pnews.getContent());
			ps.setString(4,pnews.getNewsCode());
			System.out.println("=UpdateNews=sql="+ps.toString());
			i=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getConn.closeconn(conn);
    	return i;
    }
    
    public int AddNews(Pg_news pnews){
    	pnews.setCreatedBy(COMPANYNAME);
    	pnews.setModifiedBy(COMPANYNAME);
    	pnews.setNewsID(UUID.randomUUID().toString());
    	GetConn getConn=new GetConn();
		int i = 0;
		Connection conn=getConn.getConnection();
		try {
			PreparedStatement ps=conn.prepareStatement("insert into pg_news "
					     + "(NewsID,NewsCode,title,Content,Remark,Status,CreatedBy,CreatedDate,ModifiedBy,ModifiedDate)"
					     + "value("
						 + " ?,?,?,?,?,1,?,now(),?,now()"
		        	     + ")"
		        	     );
			ps.setString(1,pnews.getNewsID());
			ps.setString(2,pnews.getNewsCode());	
			ps.setString(3,pnews.getTitle());
			ps.setString(4,pnews.getContent());
			ps.setString(5,pnews.getRemark());
			ps.setString(6,pnews.getCreatedBy());
			ps.setString(7,pnews.getModifiedBy());			
			System.out.println("=AddNews=sql="+ps.toString());
			i=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getConn.closeconn(conn);
    	return i;
    }
}

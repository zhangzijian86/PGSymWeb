package com.sym.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.sym.bean.Pg_user;
import com.sym.daoimpl.DaoImpl;

@SuppressWarnings("serial")
public class UpdateUser extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
	    PrintWriter out=response.getWriter();
	    Gson gson=new Gson();
		String jsonStr=request.getParameter("jsonStr");
		jsonStr = new String(jsonStr.getBytes("ISO-8859-1"), "UTF-8");
		Pg_user puser = null;
		puser = gson.fromJson(jsonStr, Pg_user.class); 
		if(puser!=null){
			DaoImpl userDaoImpl=new DaoImpl();
			int flag=userDaoImpl.UpdateUser(puser);
			if(flag<0){
				out.write("error");		
			}else{
				out.write("ok");
			}
		}else{
			out.write("error");		
		}	    
		out.flush();
		out.close();
	}
}

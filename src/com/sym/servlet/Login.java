package com.sym.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
public class Login extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
	    PrintWriter out=response.getWriter();	    
		String user_mobile=request.getParameter("user_mobile");
		String user_password=request.getParameter("user_password");
		DaoImpl userDaoImpl=new DaoImpl();
		Pg_user puser=userDaoImpl.login(user_mobile,user_password);
		if(puser!=null){			
			List<Pg_user> list1=new ArrayList<Pg_user>();
			Gson gson=new Gson();//利用google提供的gson将一个list集合写成json形式的字符串		
			list1.add(puser);
			String jsonstring=gson.toJson(list1);
			out.write(jsonstring);
		}else{
			out.write("LoginFail");
		}
		out.flush();
		out.close();
	}
}

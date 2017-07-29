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
public class UpdateUserWX extends HttpServlet {
	
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
		String user_birthday=request.getParameter("user_birthday");
		String user_skintype=request.getParameter("user_skintype");
		DaoImpl userDaoImpl=new DaoImpl();
		int flag=userDaoImpl.updateUserWX(user_mobile,user_birthday,user_skintype);
		if(flag<0){
			out.write("error");		
		}else{
			out.write("ok");
		}
		out.flush();
		out.close();
	}
}

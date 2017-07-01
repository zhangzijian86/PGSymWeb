package com.sym.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.sym.bean.Pg_user;
import com.sym.daoimpl.DaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

@SuppressWarnings("serial")
public class Register extends HttpServlet {


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
		String user_wxid=request.getParameter("user_wxid");
		String user_mobile=request.getParameter("user_mobile");
		String user_name=request.getParameter("user_name");
		String store_id=request.getParameter("store_id");
		DaoImpl userDaoImpl=new DaoImpl();
		Pg_user puser=userDaoImpl.register(user_wxid,user_mobile,user_name,store_id);
		if(puser!=null){			
			List<Pg_user> list1=new ArrayList<Pg_user>();
			Gson gson=new Gson();//利用google提供的gson将一个list集合写成json形式的字符串		
			list1.add(puser);
			String jsonstring=gson.toJson(list1);
			out.write(jsonstring);
		}else{			
			out.write("RegisterFail");
		}
		out.flush();
		out.close();
	}
}

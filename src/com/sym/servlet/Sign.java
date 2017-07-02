package com.sym.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.sym.bean.Pg_user;
import com.sym.daoimpl.DaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

@SuppressWarnings("serial")
public class Sign extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		String user_id=request.getParameter("user_id");
		DaoImpl userDaoImpl=new DaoImpl();
		int b=userDaoImpl.sign(user_id);
		if (b==1)  
		{
			String a="HasSigned";			
			out.write(a);
		}
		else  if(b==2)
		{
			 String a="SignSuccess";
			 out.write(a);
		}else{
			 String a="SignFail";
			 out.write(a);
		}
		out.flush();
		out.close();
	}
}

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

import com.google.gson.Gson;
import com.sym.bean.Pg_book;
import com.sym.bean.Pg_user;
import com.sym.daoimpl.DaoImpl;

@SuppressWarnings("serial")
public class NotDoBook extends HttpServlet {
	
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
		String USER_ID=request.getParameter("USER_ID");
		String BOOK_ID=request.getParameter("BOOK_ID");
		DaoImpl userDaoImpl=new DaoImpl();
		int flag=userDaoImpl.NotDoBook(USER_ID,BOOK_ID);
		if(flag<0){
			out.write("error");		
		}else{
			out.write("ok");
		}	    
		out.flush();
		out.close();
	}
}

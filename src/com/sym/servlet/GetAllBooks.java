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
import com.sym.bean.Pg_book;
import com.sym.daoimpl.DaoImpl;

@SuppressWarnings("serial")
public class GetAllBooks extends HttpServlet {
	
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
		String CurrentPage=request.getParameter("CurrentPage");
		String EachPage=request.getParameter("EachPage");
		String mobileTmp=request.getParameter("mobileTmp");
		mobileTmp = new String(mobileTmp.getBytes("ISO-8859-1"), "UTF-8");
		String dateTmp=request.getParameter("dateTmp");
		dateTmp = new String(dateTmp.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println("====GetAllOrders=============CurrentPage======"+CurrentPage);
		System.out.println("====GetAllOrders=============EachPage======"+EachPage);
		System.out.println("====GetAllOrders=============mobileTmp======"+mobileTmp);
		System.out.println("====GetAllOrders=============dateTmp======"+dateTmp);
		DaoImpl userDaoImpl=new DaoImpl();
		List<Pg_book> list=userDaoImpl.GetAllBooks(mobileTmp,dateTmp,CurrentPage,EachPage);
    	if(list!=null&&list.size()>=0){			
			Gson gson=new Gson();//利用google提供的gson将一个list集合写成json形式的字符串		
			String jsonstring=gson.toJson(list);
			out.write(jsonstring);
		}else{
			out.write("error");
		}
		out.flush();
		out.close();
	}
}

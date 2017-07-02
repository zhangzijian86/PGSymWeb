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
import com.sym.bean.Pg_store;
import com.sym.bean.Pg_user;
import com.sym.daoimpl.DaoImpl;


@SuppressWarnings("serial")
public class GetMyBooks extends HttpServlet {
	
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
		String user_id=request.getParameter("user_id");
		String store_id=request.getParameter("store_id");
		DaoImpl userDaoImpl=new DaoImpl();
		List<Pg_book> list=userDaoImpl.getmybooks(user_id,store_id);
		if(list!=null){					
			Gson gson=new Gson();//利用google提供的gson将一个list集合写成json形式的字符串					
			String jsonstring=gson.toJson(list);
			out.write(jsonstring);
		}else{
			Gson gson=new Gson();
			List<String> list1=new ArrayList<String>();
			list1.add("Result:GetMyBooksFail");
			String jsonstring=gson.toJson(list1);
			out.write(jsonstring);
		}
		out.flush();
		out.close();
	}
}

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
public class Book extends HttpServlet {


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
		String book_time=request.getParameter("book_time");
		DaoImpl userDaoImpl=new DaoImpl();
		int b=userDaoImpl.book(user_id,store_id,book_time);
		if(b==0){				
			Gson gson=new Gson();
			List<String> list=new ArrayList<String>();
			list.add("Result:BookSuccess");
			String jsonstring=gson.toJson(list);
			out.write(jsonstring);
		}else if(b==1){			
			Gson gson=new Gson();
			List<String> list=new ArrayList<String>();
			list.add("Result:This is the date to reach the booking line");
			String jsonstring=gson.toJson(list);
			out.write(jsonstring);
		}else if(b==2){				
			Gson gson=new Gson();
			List<String> list=new ArrayList<String>();
			list.add("Result:This is the date to reach the cancel line");
			String jsonstring=gson.toJson(list);
			out.write(jsonstring);
		}else if(b==3){				
			Gson gson=new Gson();
			List<String> list=new ArrayList<String>();
			list.add("Result:Reach the booking line");
			String jsonstring=gson.toJson(list);
			out.write(jsonstring);
		}else if(b==4){				
			Gson gson=new Gson();
			List<String> list=new ArrayList<String>();
			list.add("Result:BookFail");
			String jsonstring=gson.toJson(list);
			out.write(jsonstring);
		}
		out.flush();
		out.close();
	}
}

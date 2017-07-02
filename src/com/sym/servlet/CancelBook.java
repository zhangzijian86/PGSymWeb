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
public class CancelBook extends HttpServlet {


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
		String book_id=request.getParameter("book_id");
		DaoImpl userDaoImpl=new DaoImpl();
		int b=userDaoImpl.cancelbook(book_id);
		if(b==0){				
			Gson gson=new Gson();
			List<String> list=new ArrayList<String>();
			list.add("Result:CancelSuccess");
			String jsonstring=gson.toJson(list);
			out.write(jsonstring);
		}else if(b==1){				
			Gson gson=new Gson();
			List<String> list=new ArrayList<String>();
			list.add("Result:CancelFail");
			String jsonstring=gson.toJson(list);
			out.write(jsonstring);
		}
		out.flush();
		out.close();
	}
}

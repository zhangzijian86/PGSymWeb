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
import com.sym.bean.Pg_store;
import com.sym.bean.Pg_user;
import com.sym.daoimpl.DaoImpl;


@SuppressWarnings("serial")
public class GetLimitsStore extends HttpServlet {
	
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
		String user_id=request.getParameter("user_id");
		DaoImpl userDaoImpl=new DaoImpl();
		List<Pg_store> list=userDaoImpl.getlimitsstore(user_id);
		if(list!=null&&list.size()>0){					
			Gson gson=new Gson();//利用google提供的gson将一个list集合写成json形式的字符串					
			String jsonstring=gson.toJson(list);
			out.write(jsonstring);
		}else{
			out.write("GetLimitsStoreFail");
		}
		out.flush();
		out.close();
	}
}

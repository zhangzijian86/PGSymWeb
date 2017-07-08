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
public class GetAllUsers extends HttpServlet {
	
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
		String store_id=request.getParameter("store_id");		
		System.out.println("====GetAllUsers=============CurrentPage======"+CurrentPage);
		System.out.println("====GetAllUsers=============EachPage======"+EachPage);
		System.out.println("====GetAllUsers=============store_id======"+store_id);
		DaoImpl userDaoImpl=new DaoImpl();
		List<Pg_user> list=userDaoImpl.GetAllUsers(CurrentPage,EachPage,store_id);

    	if(list!=null&&list.size()>0){			
			Gson gson=new Gson();//利用google提供的gson将一个list集合写成json形式的字符串		
			String jsonstring=gson.toJson(list);
			out.write(jsonstring);
		}else{
			Gson gson=new Gson();
			List<String> list1=new ArrayList<String>();
			list1.add("Result:GetAllUsersFail");
			String jsonstring=gson.toJson(list1);
			out.write(jsonstring);
		}

		out.flush();
		out.close();
	}
}

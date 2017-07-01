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
public class GetMyStore extends HttpServlet {
	
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
		String store_id=request.getParameter("store_id");
		DaoImpl userDaoImpl=new DaoImpl();
		Pg_store pstore=userDaoImpl.getmystore(store_id);
		if(pstore!=null){			
			List<Pg_store> list1=new ArrayList<Pg_store>();
			Gson gson=new Gson();//利用google提供的gson将一个list集合写成json形式的字符串		
			list1.add(pstore);
			String jsonstring=gson.toJson(list1);
			out.write(jsonstring);
		}else{
			out.write("GetMyStoreFail");
		}
		out.flush();
		out.close();
	}
}

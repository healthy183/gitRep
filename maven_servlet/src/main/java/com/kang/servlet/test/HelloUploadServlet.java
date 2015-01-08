package com.kang.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(urlPatterns={"/helloUploadServlet"})
@MultipartConfig
public class HelloUploadServlet  extends HttpServlet{

	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 Part part = request.getPart("file");
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html;charset=UTF-8");
		 PrintWriter out = response.getWriter();
		 out.println("<html>");
	        out.println("<body>");
	        out.println("此文件的大小：" + part.getSize() + "<br />");
	        out.println("此文件类型：" + part.getContentType() + "<br />");
	        out.println("文本框内容：" + request.getParameter("name") + "<br />");
	        out.println(UploadUtil.getFileName(part) + "<br />");
	        out.println("</body>");
	        out.println("</html>");
		
	}
}

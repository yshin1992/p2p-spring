package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;


@WebServlet(name="formLoginServlet",urlPatterns="/formLogin")
public class FormLoginServlet extends HttpServlet{

	private static final long serialVersionUID = 4249780684684026453L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/formLogin.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String errorClassName = req.getParameter("shiroLoginFailure");
		if(UnknownAccountException.class.getName().equals(errorClassName)) {
            req.setAttribute("error", "用户名/密码错误");
        } else if(IncorrectCredentialsException.class.getName().equals(errorClassName)) {
            req.setAttribute("error", "用户名/密码错误");
        } else if(errorClassName != null) {
            req.setAttribute("error", "未知错误：" + errorClassName);
        }
		
		if(errorClassName != null){
			req.getRequestDispatcher("/WEB-INF/formLogin.jsp").forward(req, resp);
		}else{
			req.getRequestDispatcher("/WEB-INF/loginsuccess.jsp").forward(req, resp);
		}
		
	}

	
}

package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;


@WebServlet(name="loginServlet",urlPatterns="/login")
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 4249780684684026453L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		//直接获取Subject即可
		Subject subject = SecurityUtils.getSubject();
		
		token.setRememberMe(true);
		String error=null;
		try{
			subject.login(token);
		}catch (UnknownAccountException e) {
            error = "用户名/密码错误";
        } catch (IncorrectCredentialsException e) {
            error = "用户名/密码错误";
        } catch (AuthenticationException e) {
            //其他错误，比如锁定，如果想单独处理请单独catch处理
            error = "其他错误：" + e.getMessage();
        }
		
		if(error!=null){
			req.setAttribute("error", error);
			req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
		}else{
			req.getRequestDispatcher("/WEB-INF/loginsuccess.jsp").forward(req, resp);
		}
		
	}

	
}

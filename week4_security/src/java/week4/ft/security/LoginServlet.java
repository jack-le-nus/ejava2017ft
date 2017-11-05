package week4.ft.security;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("username");
		String password = req.getParameter("password");

		System.out.println(">>name = " + name);
		System.out.println(">>password = " + password);

		try {
			req.login(name, password);
		} catch (ServletException ex) {
			req.getRequestDispatcher("login.html").forward(req, resp);
			return;
		}

		System.out.println(">>>after login");

		getServletContext().getRequestDispatcher("/faces/protected/topsecret.xhtml")
				.forward(req, resp);
	}

	
	
}

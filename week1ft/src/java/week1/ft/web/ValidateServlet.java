package week1.ft.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/validate")
public class ValidateServlet extends HttpServlet {

	@Inject private RSVP rsvp;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		final String name = req.getParameter("name");
		final String email = req.getParameter("email");
		final String phone = req.getParameter("phone");

		rsvp.setEmail(email);
		rsvp.setName(name);
		rsvp.setPhone(phone);


		if ("fred".equals(name.toLowerCase())) {
			resp.setStatus(HttpServletResponse.SC_ACCEPTED);
			resp.setContentType("text/html");
			try (PrintWriter pw = resp.getWriter()) {
				pw.println("<h2>You have RSVPed. See you</h2>");
			}
			return;
		}

		req.getRequestDispatcher("rsvp").forward(req, resp);
	}

	
	
}
